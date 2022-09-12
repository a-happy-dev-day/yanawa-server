package fashionable.simba.yanawaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class YanawaserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(YanawaserverApplication.class, args);
    }

}
