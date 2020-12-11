package com.tangv.rmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * author:   tangwei
 * Date:     2020/12/11 17:32
 * Description:
 */
@SpringBootApplication
public class RmqApplication {
    public static void main(String[] args) {
        SpringApplication.run(RmqApplication.class, args);
    }
}