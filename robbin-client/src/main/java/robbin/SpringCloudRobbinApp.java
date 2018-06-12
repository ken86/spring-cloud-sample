package robbin;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class SpringCloudRobbinApp {

    private static final Logger logger = LoggerFactory.getLogger(SpringCloudRobbinApp.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudRobbinApp.class, args);
    }

    @RequestMapping("/hello")
    public String helloService() {
        return restTemplate().getForObject("http://SERVER-ROBBIN/hello", String.class);
    }

    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
