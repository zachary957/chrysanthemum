package com.eutopia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.eutopia.mapper")
public class HarunaApplication {

    public static void main(String[] args) {
        SpringApplication.run(HarunaApplication.class, args);
    }
}
