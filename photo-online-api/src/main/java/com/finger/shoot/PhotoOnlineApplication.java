package com.finger.shoot;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.client.RestTemplate;

/**
 * Created by zb on 2017/6/6.
 */
@Slf4j
@EnableCaching
@EnableAsync
@SpringBootApplication
@MapperScan("com.finger.shoot.mapper")
public class PhotoOnlineApplication {

    public static void main(String[] args){
        SpringApplication.run(PhotoOnlineApplication.class, args);
        log.info("shoot-photo-online start success!");
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}
