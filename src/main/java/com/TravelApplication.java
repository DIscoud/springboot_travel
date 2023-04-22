package com;

import com.spring4all.swagger.EnableSwagger2Doc;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ClassName SpringBootApplication
 * @Description
 * @Author lemon
 * @Date 16:10 2023/2/11
 * @Version 2.1
 **/
@Slf4j
@SpringBootApplication
@EnableSwagger2Doc
@MapperScan("com.lemon.mapper")
public class TravelApplication {
    public static void main(String[] args) {
        SpringApplication.run(TravelApplication.class, args);
        log.info("启动成功！");
    }

}
