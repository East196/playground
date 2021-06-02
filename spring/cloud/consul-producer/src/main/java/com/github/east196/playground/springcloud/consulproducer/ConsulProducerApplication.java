package com.github.east196.playground.springcloud.consulproducer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@SpringBootApplication
@EnableDiscoveryClient
public class ConsulProducerApplication {

    @RequestMapping("/hello")
    String hello() {
        log.info("hello world!");
        return "hello world!";
    }

    @RequestMapping("/hello4back")
    String hello4back() {
        if(1==1){
            throw new RuntimeException();
        }
        return "hello world!";
    }

    public static void main(String[] args) {
        SpringApplication.run(ConsulProducerApplication.class, args);
    }

}
