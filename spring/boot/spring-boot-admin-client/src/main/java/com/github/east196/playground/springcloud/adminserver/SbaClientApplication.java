package com.github.east196.playground.springcloud.adminserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class SbaClientApplication {

    @RequestMapping("/hello")
    String hello() {
        return "hello world!";
    }

    public static void main(String[] args) {
        SpringApplication.run(SbaClientApplication.class, args);
    }

}
