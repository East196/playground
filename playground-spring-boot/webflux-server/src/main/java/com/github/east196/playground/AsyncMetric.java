package com.github.east196.playground;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
public class AsyncMetric {

    public static void main(String[] args) {
        testAsync2Sync();//首次启动慢
        testAsync2Async();
        testAsync2Sync();
        testSync2Async();
        testSync2Sync();
    }

    private static void testAsync2Sync() {
        WebClient webClient = WebClient.builder().baseUrl("http://localhost:8080").build();
        long current = System.currentTimeMillis();
        Mono<String> result = webClient.get().uri("/api/mvc1").retrieve().bodyToMono(String.class);
        log.info("testAsync2Sync cost:{}", System.currentTimeMillis() - current);
        log.info("testAsync2Sync result:{}", result.block()); 
        log.info("testAsync2Sync cost2:{}", System.currentTimeMillis() - current);
    }

    private static void testAsync2Async() {
        WebClient webClient = WebClient.builder().baseUrl("http://localhost:8080").build();
        long current = System.currentTimeMillis();
        Mono<String> result = webClient.get().uri("/api/mono1").retrieve().bodyToMono(String.class);
        log.info("testAsync2Async cost:{}", System.currentTimeMillis() - current);
        log.info("testAsync2Async result:{}", result.block());
        log.info("testAsync2Async cost2:{}", System.currentTimeMillis() - current);
    }

    private static void testSync2Async() {
        RestTemplate restTemplate = new RestTemplate();
        long current = System.currentTimeMillis();
        String result = restTemplate.getForObject("http://localhost:8080/api/mono1"
                , String.class);
        log.info("testSync2Async cost:{}", System.currentTimeMillis() - current);
        log.info("testSync2Async result:{}", result);
        log.info("testSync2Async cost2:{}", System.currentTimeMillis() - current);
    }


    private static void testSync2Sync() {
        RestTemplate restTemplate = new RestTemplate();
        long current = System.currentTimeMillis();
        String result = restTemplate.getForObject("http://localhost:8080/api/mvc1", String.class);
        log.info("testSync2Sync cost:{}", System.currentTimeMillis() - current);
        log.info("testSync2Sync result:{}", result);
        log.info("testSync2Sync cost2:{}", System.currentTimeMillis() - current);
    }
}
