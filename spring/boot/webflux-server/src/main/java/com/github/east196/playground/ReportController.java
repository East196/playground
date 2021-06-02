package com.github.east196.playground;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/report")
public class ReportController {

    @GetMapping("/alarm")
    public String alarm() {
        log.info("alarm");
        return "hello alarm";
    }

}
