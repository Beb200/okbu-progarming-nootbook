FROM python:3.11-slim

RUN mkdir -p /usr/src

COPY . /usr/src

WORKDIR /usr/src

RUN pip install -r requirements.txt 

ENTRYPOINT [ "python", "API.py" ]