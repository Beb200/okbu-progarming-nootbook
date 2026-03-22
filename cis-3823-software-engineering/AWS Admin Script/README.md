# AWS Admin Script README

## Head Config File
In the head config file you have two feileds. You have `task` and `region`. In the region filed you put what region you are working in. 
```
"region": "us-east-1"
```
In the task filed you need to put one of the following: `db empty` `db delete` `db create` `db populate` `s3 empty` `s3 delete` `s3 create` `s3 populate`