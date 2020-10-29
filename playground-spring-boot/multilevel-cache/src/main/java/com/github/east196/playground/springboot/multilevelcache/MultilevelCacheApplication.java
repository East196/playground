package com.github.east196.playground.springboot.multilevelcache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableCaching
@SpringBootApplication
public class MultilevelCacheApplication {

    @Cacheable(value = "get",key = "#key")
    @GetMapping("/get")
    public String get(String key){
        return "success";
    }

    public static void main(String[] args) {
        SpringApplication.run(MultilevelCacheApplication.class, args);
    }

}
