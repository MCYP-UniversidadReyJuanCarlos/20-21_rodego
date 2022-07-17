FROM python:3-alpine

RUN apk update
RUN apk add git
RUN git clone --depth 1 https://github.com/maurosoria/dirsearch.git
RUN git clone https://github.com/danielmiessler/SecLists.git
RUN pip3 install flask flask-restful

WORKDIR /usr/src/app
COPY . .

ENTRYPOINT ["python3", "app.py"]
