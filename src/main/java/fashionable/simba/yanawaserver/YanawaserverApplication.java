package fashionable.simba.yanawaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@ConfigurationPropertiesScan("fashionable.simba.yanawaserver.auth.kakao")
public class YanawaserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(YanawaserverApplication.class, args);
    }

}
