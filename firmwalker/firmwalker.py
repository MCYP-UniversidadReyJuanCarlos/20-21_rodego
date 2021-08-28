import os
import xmltodict, json

from flask import Flask, jsonify, request
from flask_restful import Resource, Api

app = Flask(__name__)
api = Api(app)  

class Firmwalker(Resource):
    def get(self, path):
        os.system("./firmwalker.sh firmware_data/" + path + " firmware_data/firmwalker_" + path)
        return jsonify({'message': 'OK'})

def to_json(filename):
    with open('firmware_data/firmwalker/' + filename + '.xml') as fd:
        xpars = xmltodict.parse(fd.read())

    json_file = json.dumps(xpars)
    f = open('firmware_data/firmwalker/' + filename + ".json", "w")
    f.write(json_file)
    f.close()

api.add_resource(Firmwalker, '/firmwalker/<string:path>')

if __name__ == '__main__':
  
    app.run(debug = True, port = 8000)