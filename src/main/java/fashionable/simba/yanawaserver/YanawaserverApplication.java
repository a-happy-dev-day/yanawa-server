package fashionable.simba.yanawaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@ConfigurationPropertiesScan(value = {
    "fashionable.simba.yanawaserver.auth.kakao",
    "fashionable.simba.yanawaserver.global.config"
})
public class YanawaserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(YanawaserverApplication.class, args);
    }

}
