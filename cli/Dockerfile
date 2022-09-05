FROM python:3.6.14-buster

RUN pip install regex
RUN pip install PyInquirer requests

WORKDIR /usr/src/app
COPY . .
ENTRYPOINT ["python3", "cli.py"]
