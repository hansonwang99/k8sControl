package com.hansonwang99.controller;

import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by Administrator on 2018/3/3.
 */

@RestController
@RequestMapping("/testloggingwithlombok")
@Log4j2
public class LoggingTestControllerLombok {

    @GetMapping("/hello")
    public String hello() {
        for(int i=0;i<10_0000;i++){
            log.info("info execute index method2");
            log.warn("warn execute index method2");
            log.error("error execute index method2");

        }

        return "My First SpringBoot Application2";
    }
}
