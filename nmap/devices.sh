#!/bin/bash
set +x
if [ ! -d data/nmap ]; then
mkdir data/nmap
fi

ip route > data/ip.log
gatewayIp=$(cat data/ip.log | grep -o -m 1 '[0-9]\+[.][0-9]\+[.][0-9]\+[.][0-9]\+')
createdAt=$(date +%Y-%m-%d_%H:%M)

sudo nmap -sP --privileged ${gatewayIp}/24 -oX data/nmap/devices.xml > data/nmap/devices.log
