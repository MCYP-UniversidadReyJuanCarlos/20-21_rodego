import os

from flask import Flask, jsonify, request
from flask_restful import Resource, Api

app = Flask(__name__)
api = Api(app)

class Ssl(Resource):
    def get(self, ip):
        os.system("rm data/" + ip + ".json")
        os.system("./testssl.sh --jsonfile-pretty /tmp " + ip)
        os.system("mv " + ip + ".json /home/testssl/data")
        return jsonify({'message': 'In order to show the results go to: http://localhost:9090/services'})

api.add_resource(Ssl, '/ssl/<string:ip>')

if __name__ == '__main__':

    app.run(debug = True, port = 5002)
