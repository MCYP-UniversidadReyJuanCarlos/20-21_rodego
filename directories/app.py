from flask import Flask, jsonify, request
from flask_restful import Resource, Api

app = Flask(__name__)
api = Api(app)

class Directories(Resource):
    def get(self):

api.add_resource(Directories, '/directories')

if __name__ == '__main__':

    app.run(debug = True, port = 5001)
