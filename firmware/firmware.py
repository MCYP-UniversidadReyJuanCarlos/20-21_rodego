import os
import xmltodict, json

from flask import Flask, jsonify, request
from flask_restful import Resource, Api

app = Flask(__name__)
api = Api(app)  

class Firmware(Resource):
    def get(self, filename):
        index = filename.rfind('.')
        final_name = filename[:index]
        os.system("binwalk -Mre --directory=/usr/src/app/data/ --log=/usr/src/app/data/" + final_name + ".log --csv " + "/usr/src/app/data/" + filename)
        return jsonify({'message': 'OK'})

def to_json(filename):
    with open('data/firmwalker/' + filename + '.xml') as fd:
        xpars = xmltodict.parse(fd.read())

    json_file = json.dumps(xpars)
    f = open('data/firmwalker/' + filename + ".json", "w")
    f.write(json_file)
    f.close()

api.add_resource(Firmware, '/firmware/<string:filename>')

if __name__ == '__main__':
  
    app.run(debug = True, port = 9000)