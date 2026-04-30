import json

with open('head_config_admin_script.json', 'r') as file:
        head_config = json.load(file)

if head_config["task"] == "db empty":
        print("task is db empty")
        with open('db_empty_config.json', 'r') as file:
            task_config = json.load(file)
elif head_config["task"] == "db delete":
       print("task is db delete")
       with open('db_delete_config.json', 'r') as file:
        task_config = json.load(file)
elif head_config["task"] == "db create":
      with open('db_create_config.json', 'r') as file:
        task_config = json.load(file)
elif head_config["task"] == "db populate":
     with open('db_populate_config.json', 'r') as file:
        task_config = json.load(file)