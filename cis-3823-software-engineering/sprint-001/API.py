from flask import Flask, jsonify
import logging
import boto3
from boto3.dynamodb.conditions import Attr

app = Flask(__name__)

# Configure logging
logging.basicConfig(
    level=logging.INFO,
    format='%(asctime)s - %(name)s - %(levelname)s - %(message)s'
)
logger = logging.getLogger(__name__)



@app.route("/")
def hello_world():
   return jsonify("Hello, World!")

@app.route('/test')
def status():
   print("complete")
   complete = "yay"
   return jsonify({
      "status": "complete"
   })

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
    app.run(debug=True)