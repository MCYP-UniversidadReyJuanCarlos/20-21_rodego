FROM python:3-alpine

RUN apk update
RUN apk add git
RUN git clone -b fix --single-branch https://github.com/maurosoria/dirsearch.git /usr/src/app/dirsearch
RUN git clone --depth 1 https://github.com/danielmiessler/SecLists.git /usr/src/app/seclists
RUN pip install -r /usr/src/app/dirsearch/requirements.txt
RUN pip3 install flask flask-restful

WORKDIR /usr/src/app
COPY . .

ENTRYPOINT ["python3", "app.py"]
