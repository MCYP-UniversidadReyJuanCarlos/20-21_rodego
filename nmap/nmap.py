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
        return jsonify({'message': 'OK'})

class PortsAndServices(Resource):
    def get(self):
        os.system("chmod +x services.sh")
        os.system("sudo ./services.sh")
        to_json('services')
        return jsonify({'message': 'OK'})

def to_json(filename):
    with open('data/nmap/' + filename + '.xml') as fd:
        xpars = xmltodict.parse(fd.read())

    json_file = json.dumps(xpars)
    f = open('data/nmap/' + filename + ".json", "w")
    f.write(json_file)
    f.close()

api.add_resource(ListDevices, '/devices')
api.add_resource(PortsAndServices, '/services')

if __name__ == '__main__':
  
    app.run(debug = True)