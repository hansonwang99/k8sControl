package com.hansonwang99.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by Administrator on 2018/3/3.
 */

@RestController
@RequestMapping("/testlogging")
public class LoggingTestController {

    private final Logger logger = LogManager.getLogger(this.getClass());

    @GetMapping("/hello")
    public String hello() {
        for(int i=0;i<10_0000;i++){
            logger.info("info execute index method");
            logger.warn("warn execute index method");
            logger.error("error execute index method");

        }

        return "My First SpringBoot Application";
    }
}
