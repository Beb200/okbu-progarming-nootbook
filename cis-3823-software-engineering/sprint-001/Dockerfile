FROM python:3.11-slim

COPY . /usr/src

WORKDIR /usr/src

RUN pip install -r requirements.txt 

ENTRYPOINT [ "python", "API.py" ]