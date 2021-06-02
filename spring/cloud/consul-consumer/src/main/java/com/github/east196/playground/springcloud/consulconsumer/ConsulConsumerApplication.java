package com.github.east196.playground.springcloud.consulconsumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class ConsulConsumerApplication {
    @Autowired
    LoadBalancerClient loadBalancerClient;

    @RequestMapping("/call")
    String call() {
        ServiceInstance serviceList = loadBalancerClient.choose("consul-producer");
        System.out.println(serviceList.getUri());
        System.out.println(serviceList.getServiceId());
        String result = new RestTemplate().getForObject(serviceList.getUri().toString()+"/hello", String.class);
        return result;
    }

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping("/call2")
    String call2() {
        String result = restTemplate.getForObject("http://consul-producer/hello", String.class);
        return result;
    }

    @Autowired
    CallClient callClient;

    @RequestMapping("/call3")
    String call3() {
        String result = callClient.hello("hh");
        return result;
    }

    @RequestMapping("/call3back")
    String call3back() {
        String result = callClient.hello4back("hh");
        return result;
    }

    public static void main(String[] args) {
        SpringApplication.run(ConsulConsumerApplication.class, args);
    }

}
