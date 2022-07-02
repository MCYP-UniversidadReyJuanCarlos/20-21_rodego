import os, subprocess
import xmltodict, json
import xml.etree.ElementTree as elementTree
import logging
import nmap

from pathlib import Path
from flask import Flask, jsonify, request
from flask_restful import Resource, Api

app = Flask(__name__)
api = Api(app)

class Analysis(Resource):
    def get(self):
        os.system("chmod +x devices.sh")
        os.system("sudo ./devices.sh")

        ip = subprocess.getoutput('echo $(cat data/ip.log | grep -o -m 1 "[0-9]\+[.][0-9]\+[.][0-9]\+[.][0-9]\+" | head -1)')

        logging.error('ip' + ip)
        scanner = nmap.PortScanner()
        result = scanner.scan(ip + '/24', arguments='-n -Pn -v -sV --privileged')
        hosts = scanner.all_hosts()
        logging.error(hosts)
        for host in hosts:
            if(has_https(scanner[host])):
                logging.error('has https')

        with open('data/nmap/result.json', 'w') as fp:
            json.dump(result, fp)

        return jsonify({'message': 'In order to show the results go to: http://localhost:9090/services'})

class ListDevices(Resource):
    def get(self):
        os.system("chmod +x devices.sh")
        os.system("sudo ./devices.sh")

        get_hosts()
        return jsonify({'message': 'In order to show the results go to: http://localhost:9090/devices'})

class PortsAndServices(Resource):
    def get(self):
        os.system("chmod +x services.sh")
        os.system("sudo ./services.sh 192.168.100.101")
        to_json('services')
        return jsonify({'message': 'In order to show the results go to: http://localhost:9090/services'})

class Vulnerabilities(Resource):
    def get(self, ip):

        ips = get_ips()

        for ip2 in ips:
            os.system("sudo nmap --script=vuln -p- " + ip2 + " -oX data/nmap/"+ ip2 + "_vulns.xml")
            to_json(ip2 + '_vulns')

        return jsonify({'message': 'In order to show the results go to: http://localhost:9090/vulnerabilities'})

class ListAndPorts(Resource):
    def get(self):
        os.system("chmod +x devices.sh")
        os.system("sudo ./devices.sh")
        to_json('devices')

def has_https(host):
    for port_number in host.get('tcp').keys():
        if https in host.get('tcp')[port_number].get('name'):
            return True
        return False

 #if 'https' in scanner['192.168.102.1'].get('tcp').get(443).get('name'):

def to_json(filename):
    with open('data/nmap/' + filename + '.xml') as fd:
        xpars = xmltodict.parse(fd.read())

    json_file = json.dumps(xpars)
    f = open('data/nmap/' + filename + ".json", "w")
    f.write(json_file)
    f.close()

def get_hosts():
    f = open("data/nmap/devices.json", "r")
    devices = json.loads(f.read())
    print("devices")
    print(devices["nmaprun"]["host"])
    hosts = devices["nmaprun"]["host"]

def get_ips():
    jsonFile = open("data/nmap/device.json", "r")
    devices = json.loads(jsonFile.read())
    ips = []

    for device in devices:
        ips.append(device["ip"])

    return ips


def find_device(ip, devices):
    aux = [x for x in devices if ip in x.ip]
    if len(aux) > 0:
        print("yes")
        return aux[0]
    else:
        return None

class Device:
    ports = ''
    os = ''
    def __init__(self, ip, name, mac):
        self.ip = ip
        self.name = name
        self.mac = mac

    def read_ip(self):
       print("Device ip " + self.ip)

class Port:
    state = ''
    reason = ''
    reason_ttl = ''
    service_name = ''

    def __init__(self, port_number, protocol):
        self.port_number = port_number
        self.protocol = protocol

class OperativeSystem:
    def __init__(self, type, vendor, osfamily, osgen, accuracy, name):
        self.type = type
        self.vendor = vendor
        self.osfamily = osfamily
        self.osgen = osgen
        self.accuracy = accuracy
        self.name = name

def create_json(devices):
    with open("./data/nmap/device.json", "w") as file:
        json.dump([ob.__dict__ for ob in devices], file)

api.add_resource(ListDevices, '/devices')
api.add_resource(PortsAndServices, '/services')
api.add_resource(Vulnerabilities, '/vulnerabilities/<string:ip>')
api.add_resource(Analysis, '/analysis')

if __name__ == '__main__':

    app.run(debug = True)
