package com.github.east196.playground.springcloud.consulconsumer;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Bean
    @LoadBalanced//代表ribbon负载均衡的restTemplate客户端对象
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}