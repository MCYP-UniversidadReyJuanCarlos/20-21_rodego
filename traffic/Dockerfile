FROM golang:rc-buster
#FROM ubuntu

RUN apt-get update
RUN apt-get install sudo
#RUN apt-get install -y tshark
RUN apt-get install -y iproute2
RUN apt-get install -y git
RUN apt-get install -y wget
RUN apt-get install -y python3
RUN alias python='python3'
RUN apt-get install unzip
#not install in non interactive mode
RUN sudo DEBIAN_FRONTEND=noninteractive apt-get install -y golang
RUN sudo apt-get install -y git
RUN sudo apt-get install -y build-essential
RUN sudo apt-get install -y libpcap-dev
RUN sudo apt-get install -y libusb-1.0-0-dev
RUN sudo apt-get install -y libnetfilter-queue-dev

WORKDIR /usr/src/app
COPY . .
#ENTRYPOINT ["sudo python3", "init.py"]
CMD ["bash", "bettercap.sh"]
