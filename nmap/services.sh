#!/bin/bash
set +x
mkdir data/nmap

ip route > data/ip.log
gatewayIp=$(cat data/ip.log | grep -o -m 1 '[0-9]\+[.][0-9]\+[.][0-9]\+[.][0-9]\+')
createdAt=$(date +%Y-%m-%d_%H:%M)
ip=$1
outputFile="_services.xml"
outputLog="_services.log"
#sudo nmap -n -Pn -v -sV -O ${gatewayIp}/24 -oX data/nmap/services.xml > data/nmap/services.log
sudo nmap -n -Pn -v -sV -O ${ip} -oX data/nmap/"${ip} ${outputFile}" > data/nmap/"${ip} ${outputLog}"
