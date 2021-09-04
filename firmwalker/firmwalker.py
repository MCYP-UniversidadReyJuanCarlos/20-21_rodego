import os
import xmltodict, json

from flask import Flask, jsonify, request
from flask_restful import Resource, Api

app = Flask(__name__)
api = Api(app)  

class Firmwalker(Resource):
    def get(self, path):
        os.system("./firmwalker.sh firmware_data/" + path + " firmware_data/firmwalker_" + path)
        return jsonify({'message': 'In order to show the results go to: http://localhost:9090/firmware'})

api.add_resource(Firmwalker, '/firmwalker/<string:path>')

if __name__ == '__main__':
  
    app.run(debug = True, port = 8000)