package com.huangguang.wechat.controller;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by huangguang on 2017/7/28.
 */
@SpringBootApplication
public class WeChatServiceApplication {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(WeChatServiceApplication.class);
        app.setBannerMode(Banner.Mode.CONSOLE);
        app.run(args);
    }
}
