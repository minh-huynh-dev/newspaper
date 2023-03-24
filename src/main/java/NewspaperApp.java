import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.newspaper")
public class NewspaperApp {

    public static void main(String[] args) {
        SpringApplication.run(NewspaperApp.class, args);
    }
}