# -*- coding: utf-8 -*-

from __future__ import print_function, unicode_literals
import regex
import json
import os
import requests

from pprint import pprint
from PyInquirer import style_from_dict, Token, prompt
from PyInquirer import Validator, ValidationError

style = style_from_dict({
    Token.QuestionMark: '#E91E63 bold',
    Token.Selected: '#673AB7 bold',
    Token.Instruction: '',
    Token.Answer: '#2196f3 bold',
    Token.Question: '',
})

class NumberValidator(Validator):
    def validate(self, document):
        try:
            int(document.text)
        except ValueError:
            raise ValidationError(
                message='Please enter a number',
                cursor_position=len(document.text))

class CliApplication():
    def main(self):       
        action = answers['actions']

        if action == 'devices':
            self.list_devices()
        elif action == 'firmware':
            self.firmware()
        elif action == 'portsAndServices':
            self.devices_services_and_ports()
        elif action == 'vulnerabilities':
            self.vulnerabilities()
        
    def list_devices(self):
        print('listDevices')
        response = requests.get("http://localhost:5000/devices")
        data = response.json()
        print(data['message'])

    def firmware(self):
        print('firmware')
        firmware_file = answers['filePath']
        index = firmware_file.rfind('.')
        final_name = firmware_file[:index]
        print(final_name)
        self.binwalk(firmware_file)
        self.firmwalker(firmware_file)

    def binwalk(self, filename):
        print('binwalk')
        response = requests.get("http://localhost:9000/firmware/" + filename)

    def firmwalker(self, extracted_folder):
        print('firmwalker')
        response = requests.get("http://localhost:8000/firmwalker/" + extracted_folder + ".extracted")
        data = response.json()
        print(data['message'])

    def devices_services_and_ports(self):
        print('devicesServicesAndPorts')
        response = requests.get("http://localhost:5000/services")
        data = response.json()
        print(data['message'])
    
    def vulnerabilities(self):
        ip = answers['vulnerabilities']
        print('vulnerabilities')
        response = requests.get("http://localhost:5000/vulnerabilities/" + ip)
        data = response.json()
        print(data['message'])


print("\n                audIoTVuln \n")

questions = [
    {
        'type': 'list',
        'name': 'actions',
        'message': 'Choose one option to run the application',
        'choices': [
            {
                'key': 'devices',
                'name': 'List and identify devices',
                'value': 'devices'
            },
            {
                'key': 'firmware',
                'name': 'Firmware analysis',
                'value': 'firmware'
            },
            {
                'key': 'portsAndServices',
                'name': 'Analyze open ports and exposed services',
                'value': 'portsAndServices'
            },
            {
                'key': 'vulnerabilities',
                'name': 'List possbile vulnerbilities',
                'value': 'vulnerabilities'
            }
        ]
    },
    {
        'type': 'input',
        'name': 'filePath',
        'message': 'Introduce the file name to analyze (example: firmware.bin)',
        'filter': lambda val: str(val),
        'when': lambda answers: answers['actions'] == 'firmware'
    },
    {
        'type': 'input',
        'name': 'vulnerabilities',
        'message': 'Introduce the target ip',
        'filter': lambda val: str(val),
        'when': lambda answers: answers['actions'] == 'vulnerabilities'
    }
]

answers = prompt(questions, style=style)
app = CliApplication()
app.main()