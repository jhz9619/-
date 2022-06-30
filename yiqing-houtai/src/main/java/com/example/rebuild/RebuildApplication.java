package com.example.rebuild;

import com.example.rebuild.util.FileProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
@EnableConfigurationProperties({FileProperties.class})
@MapperScan("com.example.rebuild.mapper")
public class RebuildApplication {

    public static void main(String[] args) {
        SpringApplication.run(RebuildApplication.class, args);
    }

}
