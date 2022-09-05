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

        if action == 'analysis':
            self.analysis()
        elif action == 'firmware':
            self.firmware()

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

    def analysis(self):
        print('analysis')
        response = requests.get("http://localhost:5000/analysis")
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
                'key': 'analysis',
                'name': 'Run analysis',
                'value': 'analysis'
            },
            {
                'key': 'firmware',
                'name': 'Firmware analysis',
                'value': 'firmware'
            }
        ]
    },
    {
        'type': 'input',
        'name': 'filePath',
        'message': 'Introduce the file name to analyze (example: firmware.bin)',
        'filter': lambda val: str(val),
        'when': lambda answers: answers['actions'] == 'firmware'
    }
]

answers = prompt(questions, style=style)
app = CliApplication()
app.main()
