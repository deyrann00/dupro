package kz.narxoz.dupro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class DuproApplication {

    public static void main(String[] args) {
        SpringApplication.run(DuproApplication.class, args);
    }

}
