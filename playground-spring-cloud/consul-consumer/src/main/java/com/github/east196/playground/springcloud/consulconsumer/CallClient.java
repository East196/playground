package com.github.east196.playground.springcloud.consulconsumer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value="consul-producer",fallback = CallClientBack.class)
public interface CallClient {
	@RequestMapping(value = "/hello",method = RequestMethod.GET)
	String hello(@RequestParam(value = "name") String name);

	@RequestMapping(value = "/hello4back",method = RequestMethod.GET)
	String hello4back(@RequestParam(value = "name") String name);
}