import os
import xmltodict, json
import xml.etree.ElementTree as elementTree
import logging

from pathlib import Path
from flask import Flask, jsonify, request
from flask_restful import Resource, Api

app = Flask(__name__)
api = Api(app)

class Analysis(Resource):
    def get(self):
        os.system("chmod +x devices.sh")
        os.system("sudo ./devices.sh")
        #to_json('devices')
        devices = parse_file('devices')
        create_json(devices)

        ips = get_ips()

        os.system("chmod +x services.sh")
        for ip in ips:
            os.system("sudo ./services.sh " + ip)

        logging.error('parse_ports_and_services_files 1')
        parse_ports_and_services_files(devices)

        return jsonify({'message': 'In order to show the results go to: http://localhost:9090/services'})

class ListDevices(Resource):
    def get(self):
        os.system("chmod +x devices.sh")
        os.system("sudo ./devices.sh")
        #to_json('devices')
        devices = parse_file('devices')
        create_json(devices)
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

def parse_file(filename):
    tree = elementTree.parse('data/nmap/' + filename + '.xml')
    root = tree.getroot()

    devices = []

    for hosts in root.iter('host'):
        ip = ""
        mac = ""
        vendor = ""
        for address in hosts.iter('address'):
            if "ipv4" in (address.attrib['addrtype']):
                ip = (address.attrib['addr'])
            if "mac" in (address.attrib['addrtype']):
                mac = (address.attrib['addr'])
                if "vendor" in address.attrib:
                    vendor = (address.attrib['vendor'])
            logging.error("find " + ip)
            device = find_device(ip, devices)
            if device is None:
                logging.error("is none")
                device = Device(ip, vendor, mac)
                devices.append(device)
            else:
                logging.error("is found")
                device.mac = mac
                device.vendor = vendor
            devices.append(device)

    return devices

def parse_ports_and_services_files(devices):
    logging.error('parse_ports_and_services_files')
    files_dir = Path('data/nmap')
    files = files_dir.glob('*_services.xml')
    for file in files:
        ip = file.name.split("_")[0]
        logging.error(ip)
        tree = elementTree.parse(file)
        root = tree.getroot()
        for host in root.findall('host'):
            os = host.find('os')
            os_list = []
            port_list = []
            if os is not None:
                for match in os.findall("osmatch"):
                    name = match.attrib['name']

                for osclass in match.findall("osclass"):
                    type=osclass.attrib['type']
                    vendor=osclass.attrib['vendor']
                    osfamily=osclass.attrib['osfamily']
                    osgen=osclass.attrib['osgen']
                    accuracy=osclass.attrib['accuracy']

                os_list.append(OperativeSystem(type, vendor, osfamily, osgen, accuracy, name))

            for ports in host.findall('port'):
                for port in ports:
                    if port is not None:
                        port_number = port.attrib['portid']
                        protocol = port.attrib['protocol']
                        if(port.find('state') is not None):
                            state = port.find('state').attrib['state']
                            reason = port.find('state').attrib['reason']
                            reason_ttl = port.find('state').attrib['reason_ttl']
                        if(port.find('service') is not None):
                            service_name = port.find('service').attrib['name']
                        port_info = Port(port_number, protocol)
                        port_info.state = state
                        port_info.reason = reason
                        port_info.reason_ttl = reason_ttl
                        port_info.service_name = service_name
                        port_list.append(port_info)
            device = find_device(ip, devices)
            device.ports = port_list
            device.os = os_list

            logging.error(devices[0].ports[0].state)
            return devices


def find_device(ip, devices):
    aux = [x for x in devices if ip in x.ip]
    if len(aux) == 1:
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
