FROM python:buster

RUN apt-get update && apt-cache search kali-linux
RUN apt-get update && apt-get install -y nmap
RUN apt-get install sudo
RUN apt-get install -y iproute2
RUN apt-get install -y python3
RUN pip install flask flask-restful xmltodict python-nmap
RUN pip3 install ssh-audit

WORKDIR /usr/src/app
COPY . .
ENTRYPOINT ["python3", "nmap_app.py"]
