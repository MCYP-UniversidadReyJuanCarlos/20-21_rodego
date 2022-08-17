import os, subprocess
import xmltodict, json
import xml.etree.ElementTree as elementTree
import logging
import nmap
import requests

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
        #result = scanner.scan(ip + '/24', arguments='-n -Pn -v -sV --privileged')
        result = scanner.scan('192.168.100.105', arguments='-n -Pn -v -sV --privileged')

        hosts = scanner.all_hosts()
        logging.error(hosts)
        for host in hosts:
            https_port = get_https_port(scanner[host])
            http_port = get_http_port(scanner[host])
            ssh_port = get_ssh_port(scanner[host])
            if(https_port is not None):
                process_https(host, https_port)
            if(http_port is not None):
                process_http()
            if(ssh_port is not None):
                process_ssh(host, ssh_port)

        with open('data/nmap/result.json', 'w') as fp:
            json.dump(result, fp)

        #os.system("sudo nmap --script=vuln -p- " + ip2 + " -oX data/nmap/"+ ip2 + "_vulns.xml")
        return jsonify({'message': 'In order to show the results go to: http://localhost:9090/services'})

def process_https(host, https_port):
    logging.error('process https')
    requests.get('http://localhost:5002/ssl/' + host)

def process_ssh(ip, port):
    logging.error('process ssh')
    os.system("ssh-audit --j -v " + ip + " -p " + str(port) + " > data/nmap/ssh_audit_" + ip + ".txt")

def process_http():
    logging.error('process http')

def get_https_port(host):
    if host.get('tcp') is None:
        return None
    for port_number in host.get('tcp').keys():
        logging.error(port_number)
        if 'https' in host.get('tcp')[port_number].get('name') and 'open' in host.get('tcp')[port_number].get('state'):
            return port_number
        elif 'tls' in host.get('tcp')[port_number].get('name') and 'open' in host.get('tcp')[port_number].get('state'):
            return port_number
        elif 'ssl' in host.get('tcp')[port_number].get('name') and 'open' in host.get('tcp')[port_number].get('state'):
            return port_number
    return None

def get_ssh_port(host):
    if host.get('tcp') is None:
        return None
    for port_number in host.get('tcp').keys():
        if 'ssh' in host.get('tcp')[port_number].get('name') and 'open' in host.get('tcp')[port_number].get('state'):
            return port_number
        return None

def get_http_port(host):
    if host.get('tcp') is None:
        return None
    for port_number in host.get('tcp').keys():
        logging.error(port_number)
        if 'http' in host.get('tcp')[port_number].get('name') and 'open' in host.get('tcp')[port_number].get('state'):
            return port_number
        if 'https' in host.get('tcp')[port_number].get('name') and 'open' in host.get('tcp')[port_number].get('state'):
            return port_number
    return None

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

api.add_resource(Analysis, '/analysis')

if __name__ == '__main__':

    app.run(debug = True)
