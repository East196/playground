package com.github.east196.playground.springcloud.dubboconsumer;

import com.github.east196.playground.springboot.dubbointerface.IHelloService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


@SpringBootApplication
public class DubboConsumerApplication {
    @Reference
    IHelloService helloService;

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(DubboConsumerApplication.class, args);
        String result=applicationContext.getBean(IHelloService.class).hello("123");
        System.out.println(result);
    }

}
