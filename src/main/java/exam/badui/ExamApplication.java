package exam.badui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ExamApplication {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(ExamApplication.class, args);

        System.out.println("Running...");
    }
}