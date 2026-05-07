from flask import Flask, jsonify
import logging
import boto3
from boto3.dynamodb.conditions import Attr
import json
from decimal import Decimal

app = Flask(__name__)

# Configure logging
logging.basicConfig(
    level=logging.INFO,
    format='%(asctime)s - %(name)s - %(levelname)s - %(message)s'
)
logger = logging.getLogger(__name__)

class DecimalEncoder(json.JSONEncoder):
    def default(self, obj):
        if isinstance(obj, Decimal):
            return float(obj)
        return json.JSONEncoder.default(self, obj)



@app.route("/")
def hello_world():
   return (
       "<html>"
       "<h1>This is the Help.</h1> " \
       "<p></p>" \
       "<p>The messages are sent to \"cp-orc-to-work\" queue. </p>" \
       "<p>After the messages are prossed a message is sent to \"cp-work-to-orc\" to show that the worker is done or that there is an error. </p>" \
       "<p>All message are formeted like this \"message_one\":{\"the message content\"}.</p> " \
       "<p>this is the data worker example message code:</p> "
       "<p>\"task_order\": \"sequence\",</p>"
       "<p>\"message_one\":\"message_one\",</p>"
       "<p> \"message_type\": \"DATA\",</p>"
       "<p>\"puzzle_id\": \"puzzle_id\",</p>"
       "<p>\"puzzle_id_val\": \"cp-001\",</p>"
       "<p>\"table_name\": \"cp-data-table\",</p>"
       "<p>\"bucket_name\": \"s3_bucket\",</p>"
       "<p>\"object_key\": \"s3_key\"</p>"
       "<p>This is the cipher worker example message code:</p> "
       "<p>\"task_order\": \"sequence\",</p>"
       "<p>\"message_two\": \"message_two\",</p>"
       "<p>\"message_type\": \"CIPHER\",</p>"
       "<p>\"chiphertext\": \"Khoor Zruog\"</p>"
       "<p>To get the status of the workers use /status.</p>" \
       "<p>To get the details of the games use /detail</p>"
       "<p>To get HTML of status of workers use /get_games_in_html</p>"
       "<p>To get HTML of details of the games use /get_game_details_in_html</p>"
       "<p>To get text of status of workers use /get_games_in_text</p>"
       "<p>To get text of details of workers use /get_game_details_in_text</p>"
       "</html>"

   )

@app.route('/test')
def status():
   print("complete")
   complete = "yay"
   return jsonify({
      "status": "complete"
   })

@app.route('/status')
def get_status():
    try:
        logger.info("start get_status")

        REGION_NAME = "us-east-1"
        TABLE_NAME = "cp-status-table"

        logger.info("creating table")
        dynamodb = boto3.resource('dynamodb', region_name=REGION_NAME)
        table = dynamodb.Table(TABLE_NAME)
        logger.info("finished table")

        response = table.scan()
        items = response['Items']

        logger.info("end get_status")
        return jsonify({
            'status': items
        })
    except Exception as e:
        print(f"An unexpected error occurred in get_status: {e}")
        return jsonify(
            "fail"
        )
    
@app.route("/get_games_in_text")
def get_status_in_text():
    try:
        logger.info("start get_status")

        REGION_NAME = "us-east-1"
        TABLE_NAME = "cp-status-table"

        logger.info("creating table")
        dynamodb = boto3.resource('dynamodb', region_name=REGION_NAME)
        table = dynamodb.Table(TABLE_NAME)
        logger.info("finished table")

        response = table.scan()
        items = response['Items']

        string_items = json.dumps(items)
        output = []
        #output.append(f"{items.get('game_id')} | {items.get('status')} | {items.get('players')}")
        '''
        output = []

        for item in items:
            row = []
            for key, value in item.items():
                row.append(f"{key}: {value}")
            output.append(" | ".join(row))

        result = "\n".join(output)
        '''

        logger.info("end get_status")
        return (
            #f"<pre>{result}</pre>"
            output
        )
    except Exception as e:
        print(f"An unexpected error occurred in get_status: {e}")
        return jsonify(
            "fail"
        )
    
@app.route('/get_games_in_html')
def get_status_in_html():
    try:
        logger.info("start get_status")

        REGION_NAME = "us-east-1"
        TABLE_NAME = "cp-status-table"

        logger.info("creating table")
        dynamodb = boto3.resource('dynamodb', region_name=REGION_NAME)
        table = dynamodb.Table(TABLE_NAME)
        logger.info("finished table")

        response = table.scan()
        items = response['Items']

        logger.info("end get_status")
        return (
            f"<html><h1>This is status of games in HTML</h1><p>{items}<p></html>"
        )
    except Exception as e:
        print(f"An unexpected error occurred in get_status: {e}")
        return jsonify(
            "fail"
        )
    
@app.route('/detail')
def get_game_detail():
    try:
        logger.info("start get_game_detail")

        REGION_NAME = "us-east-1"
        TABLE_NAME = "cp-game-table"

        logger.info("creating table")
        dynamodb = boto3.resource('dynamodb', region_name=REGION_NAME)
        table = dynamodb.Table(TABLE_NAME)
        logger.info("finished table")

        response = table.scan()
        items = response['Items']

        logger.info("end get_game_detail")
        return jsonify(
            items
        )
    except Exception as e:
        print(f"An unexpected error occurred in get_game_detail: {e}")
        return jsonify(
            "fail"
        )
    
@app.route('/get_game_details_in_text')
def get_game_detail_in_text():
    try:
        logger.info("start get_game_detail")

        REGION_NAME = "us-east-1"
        TABLE_NAME = "cp-game-table"

        logger.info("creating table")
        dynamodb = boto3.resource('dynamodb', region_name=REGION_NAME)
        table = dynamodb.Table(TABLE_NAME)
        logger.info("finished table")

        response = table.scan()
        items = response['Items']

        game1 = items["game_001"]

        string_items = json.dumps(items, cls=DecimalEncoder)

        logger.info("end get_game_detail")
        return (
            game1
        )
    except Exception as e:
        print(f"An unexpected error occurred in get_game_detail: {e}")
        return jsonify(
            "fail"
        )
    
@app.route('/get_game_details_in_html')
def get_game_detail_in_html():
    try:
        logger.info("start get_game_detail")

        REGION_NAME = "us-east-1"
        TABLE_NAME = "cp-game-table"

        logger.info("creating table")
        dynamodb = boto3.resource('dynamodb', region_name=REGION_NAME)
        table = dynamodb.Table(TABLE_NAME)
        logger.info("finished table")

        response = table.scan()
        items = response['Items']

        logger.info("end get_game_detail")
        return (
            f"<html><h1>This is details of games in HTML</h1><p>{items}<p></html>"
        )
    except Exception as e:
        print(f"An unexpected error occurred in get_game_detail: {e}")
        return jsonify(
            "fail"
        )


@app.route('/status_data')
def get_status_data():
    logger.info("start get_status_data")

    REGION_NAME = "us-east-1"
    TABLE_NAME = "cp-status-table"

    logger.info("creating table")
    dynamodb = boto3.resource('dynamodb', region_name=REGION_NAME)
    table = dynamodb.Table(TABLE_NAME)
    logger.info("finished table")

    worker = "data"
    logger.info("start scan")
    response2 = table.scan(
        FilterExpression = (Attr('worker_name').eq(worker))
    )

    items = response2.get('Items', [])
    print(items)
    logger.info("finished scan")

    data_status = items[0]["w_status"]

    logger.info("end get_status_data")
    return jsonify({
        "status":  data_status 
    })

@app.route('/status_cipher')
def get_status_cipher():
    logger.info("start get_status_cipher")

    REGION_NAME = "us-east-1"
    TABLE_NAME = "cp-status-table"

    logger.info("creating table")
    dynamodb = boto3.resource('dynamodb', region_name=REGION_NAME)
    table = dynamodb.Table(TABLE_NAME)
    logger.info("finished table")

    worker = "cipher"
    logger.info("start scan")
    response2 = table.scan(
        FilterExpression = (Attr('worker_name').eq(worker))
    )

    items = response2.get('Items', [])
    print(items)
    logger.info("finished scan")

    cipher_status = items[0]["w_status"]

    logger.info("end get_status_cipher")
    return jsonify({
        "status":  cipher_status 
    })

@app.route('/status_logic')
def get_status_logic():
    logger.info("start get_status_logic")

    REGION_NAME = "us-east-1"
    TABLE_NAME = "cp-status-table"

    logger.info("creating table")
    dynamodb = boto3.resource('dynamodb', region_name=REGION_NAME)
    table = dynamodb.Table(TABLE_NAME)
    logger.info("finished table")

    worker = "logic"
    logger.info("start scan")
    response2 = table.scan(
        FilterExpression = (Attr('worker_name').eq(worker))
    )

    items = response2.get('Items', [])
    print(items)
    logger.info("finished scan")

    logic_status = items[0]["w_status"]

    logger.info("end get_status_logic")
    return jsonify({
        "status":  logic_status 
    })

@app.route('/status_image')
def get_status_image():
    logger.info("start get_status_image")

    REGION_NAME = "us-east-1"
    TABLE_NAME = "cp-status-table"

    logger.info("creating table")
    dynamodb = boto3.resource('dynamodb', region_name=REGION_NAME)
    table = dynamodb.Table(TABLE_NAME)
    logger.info("finished table")

    worker = "image"
    logger.info("start scan")
    response2 = table.scan(
        FilterExpression = (Attr('worker_name').eq(worker))
    )

    items = response2.get('Items', [])
    print(items)
    logger.info("finished scan")

    image_status = items[0]["w_status"]

    logger.info("end get_status_image")
    return jsonify({
        "status":  image_status 
    })


    
@app.route('/details_data')
def get_detail_data():
    logger.info("start get_detail")

    REGION_NAME = "us-east-1"
    TABLE_NAME = "cp-details-table"

    logger.info("creating table")
    dynamodb = boto3.resource('dynamodb', region_name=REGION_NAME)
    table = dynamodb.Table(TABLE_NAME)
    logger.info("finished table")

    worker = "data"
    logger.info("start scan")
    response2 = table.scan(
        FilterExpression = (Attr('worker_name').eq(worker))
    )

    items = response2.get('Items', [])
    print(items)
    logger.info("finished scan")

    details = items[0]["w_detail"]

    logger.info("end get_detail_data")
    return jsonify({
        "status":  details 
    })

@app.route('/details_cipher')
def get_detail_cipher():
    logger.info("start get_detail_cipher")

    REGION_NAME = "us-east-1"
    TABLE_NAME = "cp-details-table"

    logger.info("creating table")
    dynamodb = boto3.resource('dynamodb', region_name=REGION_NAME)
    table = dynamodb.Table(TABLE_NAME)
    logger.info("finished table")

    worker = "cipher"
    logger.info("start scan")
    response2 = table.scan(
        FilterExpression = (Attr('worker_name').eq(worker))
    )

    items = response2.get('Items', [])
    print(items)
    logger.info("finished scan")

    details = items[0]["w_detail"]

    logger.info("end get_detail_cipher")
    return jsonify({
        "status":  details 
    })

@app.route('/details_logic')
def get_detail_logic():
    logger.info("start get_detail_logic")

    REGION_NAME = "us-east-1"
    TABLE_NAME = "cp-details-table"

    logger.info("creating table")
    dynamodb = boto3.resource('dynamodb', region_name=REGION_NAME)
    table = dynamodb.Table(TABLE_NAME)
    logger.info("finished table")

    worker = "logic"
    logger.info("start scan")
    response2 = table.scan(
        FilterExpression = (Attr('worker_name').eq(worker))
    )

    items = response2.get('Items', [])
    print(items)
    logger.info("finished scan")

    details = items[0]["w_detail"]

    logger.info("end get_detail_logic")
    return jsonify({
        "status":  details 
    })

@app.route('/details_image')
def get_detail_image():
    logger.info("start get_detail_image")

    REGION_NAME = "us-east-1"
    TABLE_NAME = "cp-details-table"

    logger.info("creating table")
    dynamodb = boto3.resource('dynamodb', region_name=REGION_NAME)
    table = dynamodb.Table(TABLE_NAME)
    logger.info("finished table")

    worker = "image"
    logger.info("start scan")
    response2 = table.scan(
        FilterExpression = (Attr('worker_name').eq(worker))
    )

    items = response2.get('Items', [])
    print(items)
    logger.info("finished scan")

    details = items[0]["w_detail"]

    logger.info("end get_detail_image")
    return jsonify({
        "status":  details 
    })


if __name__ == '__main__':
    app.run(debug=True, port=3000,host="0.0.0.0")