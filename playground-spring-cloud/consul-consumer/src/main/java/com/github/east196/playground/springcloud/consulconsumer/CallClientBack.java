package com.github.east196.playground.springcloud.consulconsumer;

public class CallClientBack implements CallClient {
 
	@Override
	public String hello(String name) {
		return "sorry,hello熔断介入";
	}

	@Override
	public String hello4back(String name) {
		return "sorry,hello4back熔断介入";
	}
 
}