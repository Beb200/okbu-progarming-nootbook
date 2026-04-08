import boto3
import json
import uuid
import logging
import time

#Input config
with open('orc_config.json', 'r') as file:
        config = json.load(file)

# Configure logging
logging.basicConfig(
    level=logging.INFO,
    format='%(asctime)s - %(name)s - %(levelname)s - %(message)s'
)
logger = logging.getLogger(__name__)

# AWS Configuration
QUEUE_URL = config["url"]
REGION = config["region"]
message_data = {}


# Initialize AWS clients
sqs = boto3.client('sqs', region_name=REGION)

def create_task_plan():
    logger.info("starting the task plan")

    task_plan = config["message_data"]
    for key, value in task_plan.items():
        print(key)
        print(value)
        global message_data
        message_data = value
        if value["task_order"] == "parallel":
            continue
        create_message()
        receive_message()
        
    logger.info("ending task plan")

def create_message():
    logger.info("Start first message")

    logger.info("Starting comfig.")
    firstQUEUE_URL = config["fmQUEUE_URL"]

    retries = 0
    max_retries = config['finished_message_max_retries']

    #message_data = config['message_data']
    #message_id = f"msg_{uuid.uuid4().hex[:8]}"
    #message_data["message_id"] = message_id
    logger.info("Ending config.")
    
    while retries <= max_retries:
        try:
            response = sqs.send_message(
                QueueUrl=firstQUEUE_URL,
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
    
    return None

def receive_message():
    rm = False
    logger.info("Start receive message function")
    max_consecutive_errors = config["max_consecutive_errors"]
    consecutive_errors = 0
    while (rm == False) and (consecutive_errors <= max_consecutive_errors):
        try:
            rmQUEUE_URL = config["rmQUEUE_URL"]
            # Long polling for messages (reduces empty responses and costs)
            print("geting message")
            response = sqs.receive_message(
                QueueUrl=rmQUEUE_URL,
                MaxNumberOfMessages=1,  # Process one message at a time
                WaitTimeSeconds=20,      # Long polling (up to 20 seconds)
                VisibilityTimeout=60     # Hide message for 60 seconds while processing
            )
                
            if 'Messages' in response:
                print("got message")
                rm = True
                consecutive_errors = 0  # Reset error counter on successful receive
                #no_message_error = 0 #Reset the counter on successful receive

                for message in response['Messages']:
                    logger.info("-" * 70)
                    message_body = json.loads(message['Body'])
                    logger.info(message_body)

                    logger.info("starting the deleting prosses")
                    sqs.delete_message(
                        QueueUrl=rmQUEUE_URL,
                        ReceiptHandle=message['ReceiptHandle']
                    )
                    logger.info(f"✓ Message deleted from queue")
                    logger.info("-" * 70)
                        
            else:
                #print("no messages")
                # No messages available
                logger.info("No messages in queue, continuing to poll...")
                    
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
    logger.info("End receive message function")

def main():
    """Main entry point with interactive menu."""
    logger.info("Message Producer starting...")
    logger.info(f"Queue URL: {QUEUE_URL}")
    logger.info(f"Region: {REGION}")

    
    create_task_plan()
    #create_message()


if __name__ == "__main__":
    main()