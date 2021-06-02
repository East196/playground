package com.github.east196.playground;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@RequestMapping("/api/")
public class HelloController {

    @GetMapping("mvc")
    public String mvc() {
        log.info("mvc");
        return "hello mvc";
    }

    @GetMapping("mono")
    public Mono<String> mono() {
        log.info("mono");
        return Mono.just("hello webflux");
    }

    @GetMapping("mvc1")
    public String mvc1() {
        try {
            log.info("mvc1 start");
            Thread.sleep(500);
            log.info("mvc1 end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "hello mvc1";
    }

    @GetMapping("mono1")
    public Mono<String> mono1() {
        return Mono.fromSupplier(() -> createStr());
    }

    public String createStr() {
        log.info("mono1 start");
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("mono1 end");
        return "hello webflux1 ";
    }

    @GetMapping("error")
    public String error() {
        try {
            throw new RuntimeException();
        } catch (Throwable e) {
            // 方案1 ：命令行打印栈
            e.printStackTrace();

            // 方案2 ：日志打印栈，比较全
            StringWriter trace = new StringWriter();
            e.printStackTrace(new PrintWriter(trace));
            log.error(trace.toString());

            // 方案3 ：日志打印栈，但日志首行为空
            log.error("HelloController:error() ; error: ", e);
        }
        return "hello error ";
    }
}