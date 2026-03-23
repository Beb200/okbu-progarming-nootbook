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
QUEUE_URL = config("url")
REGION = config("region")

# Initialize AWS clients
sqs = boto3.client('sqs', region_name=REGION)

def create_test_message():
    message_id = f"msg_{uuid.uuid4().hex[:8]}"
    message_data = {
            'message_id': f"{message_id}",
            'message_type' : f"DATA",
            'puzzle_id': f"puzzle_id",
            'puzzle_id_val': f'cp-data-001',
            'game_id': f'game_id',
            'game_id_val': f'cp-game-000001',
            'bucket_name': f's3_bucket',
            'object_key': f's3_key'
    }

    retries = 0
    max_retries = 2
    
    while retries <= max_retries:
        try:
            response = sqs.send_message(
                QueueUrl=QUEUE_URL,
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

def main():
    """Main entry point with interactive menu."""
    logger.info("Message Producer starting...")
    logger.info(f"Queue URL: {QUEUE_URL}")
    logger.info(f"Region: {REGION}")

    create_test_message()


if __name__ == "__main__":
    main()