FROM python:slim

RUN apt-get update && apt-get install -y nmap sudo -y iproute2 -y python3
RUN pip install flask flask-restful xmltodict python-nmap requests
RUN pip3 install ssh-audit

WORKDIR /usr/src/app
COPY . .
ENTRYPOINT ["python3", "nmap_app.py"]
