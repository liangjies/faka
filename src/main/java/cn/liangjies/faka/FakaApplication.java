package cn.liangjies.faka;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.liangjies.faka.dao")
public class FakaApplication {

    public static void main(String[] args) {
        SpringApplication.run(FakaApplication.class, args);
    }

}
