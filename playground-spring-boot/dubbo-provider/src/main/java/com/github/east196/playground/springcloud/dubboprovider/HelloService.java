package com.github.east196.playground.springcloud.dubboprovider;

import com.github.east196.playground.springboot.dubbointerface.IHelloService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

@Service
@Component
public class HelloService implements IHelloService {
    @Override
    public String hello(String s) {
        return "hello world!!!";
    }
}
