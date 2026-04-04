import boto3
from boto3.dynamodb.conditions import Key
from boto3.dynamodb.conditions import Attr
import json
import uuid
import logging
import time
import statistics

#Input config
with open('work_config.json', 'r') as file:
        config = json.load(file)

# Configure logging
logging.basicConfig(
    level=logging.INFO,
    format='%(asctime)s - %(name)s - %(levelname)s - %(message)s'
)
logger = logging.getLogger(__name__)

# AWS Configuration
QUEUE_URL = config["queue_url"]  #'https://sqs.us-east-1.amazonaws.com/216990846240/CPqueue'
REGION = config["region"] #'us-east-1'

# Initialize AWS clients
sqs = boto3.client('sqs', region_name=REGION)

#creating a globle message type
message_body = ""
test_message = "no message"

def find_test_message():
    print("stating to find message")
    max_consecutive_errors = config["max_consecutive_errors"] #3
    consecutive_errors = 0
    no_message_error = 1 #needed to add one to get the right max
    max_no_message_error = config["max_no_message_error"]
    while (consecutive_errors <= max_consecutive_errors) and (no_message_error <= max_no_message_error):
        try:
            # Long polling for messages (reduces empty responses and costs)
            print("geting message")
            response = sqs.receive_message(
                QueueUrl=QUEUE_URL,
                MaxNumberOfMessages=1,  # Process one message at a time
                WaitTimeSeconds=20,      # Long polling (up to 20 seconds)
                VisibilityTimeout=60     # Hide message for 60 seconds while processing
            )
            
            if 'Messages' in response:
                print("got message")
                consecutive_errors = 0  # Reset error counter on successful receive
                #no_message_error = 0 #Reset the counter on successful receive

                for message in response['Messages']:
                    logger.info("-" * 70)
                    message_body = json.loads(message['Body'])
                    logger.info(message_body)
                    
                    type_val = message_body.get("message_type")
                    print("The message type:", type_val)

                    if type_val == 'DATA':
                        logger.info("rescived data type")
                        sqs.delete_message(
                            QueueUrl=QUEUE_URL,
                            ReceiptHandle=message['ReceiptHandle']
                        )
                        logger.info(f"✓ Message deleted from queue")
                        logger.info("-" * 70)

                        file_path = 'queue_messages.json'
                        data = message_body

                        with open(file_path, 'w') as json_file:
                            json.dump(data, json_file, indent=4)
                        data_worker()
                    elif type_val == 'CIPHER':
                        logger.info("recived cipher type")
                        sqs.delete_message(
                            QueueUrl=QUEUE_URL,
                            ReceiptHandle=message['ReceiptHandle']
                        )
                        logger.info(f"✓ Message deleted from queue")
                        logger.info("-" * 70)

                        file_path = 'queue_messages.json'
                        data = message_body

                        with open(file_path, 'w') as json_file:
                            json.dump(data, json_file, indent=4)
                        cipher_worker()
            else:
                print("no messages")
                # No messages available
                logger.info("No messages in queue, continuing to poll...")
                #no_message_error += 1 #commint out to make loop endless
                
        except KeyboardInterrupt:
            logger.info("Received shutdown signal, stopping worker...")
            break
            
        except Exception as e:
            consecutive_errors += 1
            logger.error(f"✗ Error in main loop: {e}")
            
            if consecutive_errors >= max_consecutive_errors:
                logger.critical(f"Too many consecutive errors ({consecutive_errors}), shutting down")
                break
            
            # Exponential backoff on errors
            sleep_time = min(2 ** consecutive_errors, 60)
            logger.info(f"Sleeping {sleep_time} seconds before retry...")
            time.sleep(sleep_time)
    
    logger.info("Cipher Worker stopped")
    #print(message_body)

def data_worker():
    print("starting data worker")

    print("stating getting the messages")
    with open('queue_messages.json', 'r') as file:
        message = json.load(file)
    print("finished getting messages")

    print("starting config")
    TABLE_NAME = config["table_name"]
    REGION_NAME = config["region_name"]

    puzzle_id = message["puzzle_id"]
    puzzle_id_val = message["puzzle_id_val"]
    game_id = message["game_id"]
    game_id_val = message["game_id_val"]
    bucket_name = message["bucket_name"]
    object_key = message["object_key"]
    print("finished config")

    print("creating table")
    dynamodb = boto3.resource('dynamodb', region_name=REGION_NAME)
    table = dynamodb.Table(TABLE_NAME)
    print("finished table")
    
    print("start scan")
    response2 = table.scan(
        FilterExpression = (Attr(puzzle_id).eq(puzzle_id_val)) and (Attr(game_id).eq(game_id_val))
    )

    items = response2.get('Items', [])
    print(items)
    print("finished scan")

    print("start s3 client")
    s3_client = boto3.client('s3')
    print("finished s3 client")

    print("start s3 config")
    bucket_name = items[0][bucket_name]
    object_key = items[0][object_key]
    print("finished s3 config")

    print("start s3 get")
    response = s3_client.get_object(Bucket=bucket_name, Key=object_key)
    object_content = response['Body'].read().decode('utf-8')
    print("finished s3 get")
    #print("File content:")
    #print(object_content)

    #list_val = object_content.splitlines()
    list_val = [float(x)for x in object_content.split()]
    
    print("Printing the s3 values:")
    print(list_val)

    print("creating the task")
    task = items[0]['task']
    print("finished creating the task ")

    print("determining the task")
    if task ==  'find_mean':
        print("the task is find the mean")
        mean_num = sum(list_val) / len(list_val)
        print("The mean:")
        print(mean_num)
    elif task == 'find_median' :
        print("the task is find the median")
        median = statistics.median(list_val)
        print("The Median:")
        print(median)


    print("Done")
    finished_message()

def cipher_worker():
    logger.info("Starting cipher worker")

    logger.info("Stating getting the messages")
    with open('queue_messages.json', 'r') as file:
        message = json.load(file)
    logger.info("finished getting messages")

    print(message['message_type'])
    logger.info("Finished with cipher worker.")
    finished_message()

def finished_message():
    logger.info("Starting the Finished Message.")

    logger.info("Starting comfig.")
    fmQUEUE_URL = config["fmQUEUE_URL"]

    retries = 0
    max_retries = config['finished_message_max_retries']

    message_data = config['finished_message_data']
    message_id = f"msg_{uuid.uuid4().hex[:8]}"
    message_data["message_id"] = message_id
    logger.info("Ending config.")

    logger.info("Starting creating message.")
    
    while retries <= max_retries:
        try:
            response = sqs.send_message(
                QueueUrl=fmQUEUE_URL,
                MessageBody=json.dumps(message_data),
                MessageAttributes={
                    'MessageType': {
                        'StringValue': message_data.get('message_type', 'unknown'),
                        'DataType': 'String'
                    }
                }
            )
            
            message_id = response['MessageId']
            logger.info(f"✓ Message sent successfully - MessageId: {message_id}")
            return message_id
            
        except sqs.exceptions.InvalidMessageContents as e:
            logger.error(f"✗ Invalid message contents: {e}")
            logger.error(f"  Message body: {json.dumps(message_data, indent=2)}")
            # Don't retry for invalid message format
            return None
            
        except Exception as e:
            if retries < max_retries:
                retries += 1
                # Exponential backoff: 100ms, 200ms, 400ms, 800ms
                sleep_time = (2 ** retries) * 0.1
                logger.warning(f"✗ Send failed (attempt {retries}/{max_retries}): {e}")
                logger.info(f"  Retrying in {sleep_time:.1f} seconds...")
                time.sleep(sleep_time)
            else:
                logger.error(f"✗ Failed to send message after {max_retries} retries: {e}")
                return None
    
    #return None #was in base code but don't know what it does
    logger.info("Finished with the finished message.")


def main():
    """Main entry point with interactive menu."""
    logger.info("Message Producer starting...")
    logger.info(f"Queue URL: {QUEUE_URL}")
    logger.info(f"Region: {REGION}")

    find_test_message()
    


if __name__ == "__main__":
    main()