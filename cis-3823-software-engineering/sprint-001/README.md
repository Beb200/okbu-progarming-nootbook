{
    "message_id": "msg_4886e89a",
    "message_type": "CIPHER",
    "puzzle_id": "puzzle_id",
    "puzzle_id_val": "cp-data-001",
    "game_id": "game_id",
    "game_id_val": "cp-game-000001",
    "bucket_name": "s3_bucket",
    "object_key": "s3_key"
}




        "message_one":{
            "task_order": "sequence",
            "message_one":"message_one",
            "message_type": "DATA",
            "puzzle_id": "puzzle_id",
            "puzzle_id_val": "cp-data-001",
            "game_id": "game_id",
            "game_id_val": "cp-game-000001",
            "bucket_name": "s3_bucket",
            "object_key": "s3_key"
        },

        "message_two":{
            "task_order": "sequence",
            "message_two": "message_two",
            "message_type": "CIPHER",
            "chiphertext": "Khoor Zruog"
        },


"message_three":{
            "task_order" :"sequence",
            "message_type": "IMAGE",
            "s3_bucket": "caleb-payn-bucket",
            "file_name": "image_worker_hidden_message.png"
        },

        "message_four":{
            "task_order" :"sequence",
            "message_type": "LOGIC",
            "puzzle": [["A", "B", "!C"], ["!A", "C"], ["B", "!D"]]
        }
        

        "message_three":{
            "task_order" :"sequence",
            "message_type": "IMAGE",
            "s3_bucket": "caleb-payn-bucket",
            "file_name": "image_worker_hidden_message.png"
        }

        "message_four":{
            "task_order" :"sequence",
            "message_type": "LOGIC"
        }


function temp:
def name():
    try:
        logger.info("stated name")

        logger.info("end name")
    except Exception as e:
        print(f"An unexpected error occurred in name: {e}")

updae temp:
REGION_NAME = config["region"]
TABLE_NAME = config["game_table"]
dynamodb = boto3.resource('dynamodb', region_name=REGION_NAME)
table = dynamodb.Table(TABLE_NAME)
game_id = config["game_id"]
table.update_item(
    Key = {
        'game_id': 'game_id'
    },
    UpdateExpression = 'SET age = :val1',
    ExpressionAttributeValues = {
        ':val1': 26
    }
)