package intisoft2025.practica;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PracticaApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(PracticaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Backend Inciado.");
    }
}
