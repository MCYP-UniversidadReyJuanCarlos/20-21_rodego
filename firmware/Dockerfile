FROM python:buster

RUN apt-get update && apt-cache search kali-linux
ENV DEBIAN_FRONTEND noninteractive
WORKDIR /binwalk

RUN apt-get update -y; \
 \
 apt-get install -y \
 wget \
 curl \
 python3 \
 xvfb \
 python3-setuptools \
 git;

RUN pip install flask flask-restful xmltodict

RUN git clone https://github.com/ReFirmLabs/binwalk.git /binwalk; \
 cd /binwalk; \
 python3 setup.py install;

RUN rm deps.sh
COPY deps.sh /binwalk/
RUN chmod +x deps.sh
RUN ./deps.sh --yes

RUN apt-get clean; \
 rm -rf /var/lib/apt/lists/*

COPY . .

ENTRYPOINT ["python3", "firmware.py"]