package exam.badui;

import exam.badui.services.DnaService;
import exam.badui.validators.DnaValidator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Scanner;

@SpringBootApplication
public class ExamApplication {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(ExamApplication.class, args);
        //System.out.println("Running...");

        DnaService dnaService = context.getBean(DnaService.class);

        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el tamaño de la matriz NxN: ");
        int n = scanner.nextInt();
        scanner.nextLine();

        String[] dna = new String[n];
        System.out.println("Ingrese las filas de ADN");

        for (int i = 0; i < n; i++) {
            dna[i] = scanner.nextLine();
        }

        dnaService.analyzeDna(dna);

        scanner.close();


    }
}