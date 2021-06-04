from fabric.api import *

skywalking_agent = "D:\\workspace\\future\\microservice\\ci\\elasticstack\\apache-skywalking-apm-bin-es7\\agent\\skywalking-agent.jar"

def agent(jar):
	local(f"java -javaagent:{skywalking_agent} -jar "+ jar)

def gateway(port=9001):
	agent(f".\\gateway\\target\\gateway-0.0.1-SNAPSHOT.jar --server.port={port}")

def admin(port=9002):
	agent(f".\\consul-admin-server\\target\\consul-admin-server-0.0.1-SNAPSHOT.jar --server.port={port}")

def do(port=8501):
	agent(f".\\consul-producer\\target\\consul-producer-0.0.1-SNAPSHOT.jar --server.port={port}")

def use(port=8503):
	agent(f".\\consul-consumer\\target\\consul-consumer-0.0.1-SNAPSHOT.jar --server.port={port}")