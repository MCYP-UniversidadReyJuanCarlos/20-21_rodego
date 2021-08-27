FROM kalilinux/kali

RUN apt-get update && apt-cache search kali-linux
RUN apt-get update && apt-get install -y nmap
RUN apt-get install sudo 
RUN apt-get install -y iproute2
RUN apt-get install -y python3
RUN apt-get install -y pip
RUN pip install flask flask-restful xmltodict

WORKDIR /usr/src/app
COPY . .
ENTRYPOINT ["python3", "nmap.py"]