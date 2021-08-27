#!/bin/bash
set +x
mkdir data/nmap

ip route > data/ip.log
gatewayIp=$(cat data/ip.log | grep -o -m 1 '[0-9]\+[.][0-9]\+[.][0-9]\+[.][0-9]\+')

sudo nmap -sP --privileged ${gatewayIp}/24 -oX data/nmap/devices.xml > data/nmap/devices.log