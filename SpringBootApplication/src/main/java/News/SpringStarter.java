package News;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringStarter {
    public static void main(String[] args) {
        ApplicationContext context = org.springframework.boot.SpringApplication.run(SpringStarter.class, args);
    }
}
