FROM python:buster

RUN apt-get update && apt-get install -y nmap sudo -y iproute2 -y python3
RUN pip install flask flask-restful xmltodict python-nmap
RUN pip3 install ssh-audit

WORKDIR /usr/src/app
COPY . .
ENTRYPOINT ["python3", "nmap_app.py"]
