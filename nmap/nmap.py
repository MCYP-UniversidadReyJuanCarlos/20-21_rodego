import os
import xmltodict, json

from flask import Flask, jsonify, request
from flask_restful import Resource, Api

app = Flask(__name__)
api = Api(app)  

class ListDevices(Resource):
    def get(self):
        os.system("chmod +x devices.sh")
        os.system("sudo ./devices.sh")
        to_json('devices')
        return jsonify({'message': 'In order to show the results go to: http://localhost:9090/devices'})

class PortsAndServices(Resource):
    def get(self):
        os.system("chmod +x services.sh")
        os.system("sudo ./services.sh")
        to_json('services')
        return jsonify({'message': 'In order to show the results go to: http://localhost:9090/services'})

class Vulnerabilities(Resource):
    def get(self, ip):
        os.system("sudo nmap --script=vuln -p- " + ip + " -oX data/nmap/vulns.xml")
        to_json('vulns')
        return jsonify({'message': 'In order to show the results go to: http://localhost:9090/vulnerabilities'})

def to_json(filename):
    with open('data/nmap/' + filename + '.xml') as fd:
        xpars = xmltodict.parse(fd.read())

    json_file = json.dumps(xpars)
    f = open('data/nmap/' + filename + ".json", "w")
    f.write(json_file)
    f.close()

api.add_resource(ListDevices, '/devices')
api.add_resource(PortsAndServices, '/services')
api.add_resource(Vulnerabilities, '/vulnerabilities/<string:ip>')

if __name__ == '__main__':
  
    app.run(debug = True)