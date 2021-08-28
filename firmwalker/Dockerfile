FROM python:buster

WORKDIR /usr/src/app

RUN pip install flask flask-restful xmltodict
RUN apt-get install -y git

RUN git clone https://github.com/craigz28/firmwalker.git .;

COPY . .

ENTRYPOINT ["python3", "firmwalker.py"]