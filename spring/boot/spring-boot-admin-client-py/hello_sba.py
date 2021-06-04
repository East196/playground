
# pip install pyctuator

import os
from flask import Flask
from pyctuator.auth import BasicAuth
from pyctuator.pyctuator import Pyctuator

app_name = "Flask App with Pyctuator"
app = Flask(app_name)


@app.route("/")
def hello():
    return "Hello World!"


Pyctuator(
    app,
    app_name,
    app_url="http://192.168.99.100:5000",
    pyctuator_endpoint_url="http://192.168.99.100:5000/pyctuator",
    registration_url="http://192.168.99.100:9600/instances",
    registration_auth=BasicAuth(username="admin",password="admin"),
)

app.run(host="0.0.0.0")