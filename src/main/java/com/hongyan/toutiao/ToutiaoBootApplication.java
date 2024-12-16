package com.hongyan.toutiao;

import cn.dev33.satoken.SaManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@Slf4j
@SpringBootApplication
public class ToutiaoBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(ToutiaoBootApplication.class, args);
        log.info("启动成功，Sa-Token 配置如下：{}", SaManager.getConfig());
    }

}
