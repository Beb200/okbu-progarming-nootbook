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