import os

from flask import Flask, jsonify, request
from flask_restful import Resource, Api

app = Flask(__name__)
api = Api(app)

class Directories(Resource):
    def get(self, ip):
        os.system("rm data/" + ip  + ".json")
        os.system("python3 dirsearch/dirsearch.py -u " + ip + " -e html,php,txt,log,sql,csv,jpg,png,js,css,zip,json,conf -w seclists/Discovery/Web-Content/common.txt -r -f -x 400-499,500 --format json -o data/" + ip + ".json")

api.add_resource(Directories, '/directories/<string:ip>')

if __name__ == '__main__':

    app.run(debug = True, port = 5001)

#api.add_resource(Vulnerabilities, '/vulnerabilities/<string:ip>')
# def get(self, ip):  e asp,aspx,html,php,txt,jpg,png,old,bak,zip,json,xml,xls,csv,tsv -w Herramientas/Seclists-master/Discovery/Web-Content/common.txt -t 10 -f -b -x 400,401,404,403,406,405,500 --format json -o
