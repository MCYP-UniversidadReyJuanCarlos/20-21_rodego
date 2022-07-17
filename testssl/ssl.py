from flask import Flask, jsonify, request
from flask_restful import Resource, Api

app = Flask(__name__)
api = Api(app)

class Ssl(Resource):
    def get(self):

api.add_resource(Ssl, '/ssl')

if __name__ == '__main__':

    app.run(debug = True, port = 5002)
