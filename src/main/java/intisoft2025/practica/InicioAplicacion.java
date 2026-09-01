package intisoft2025.practica;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InicioAplicacion implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(InicioAplicacion.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("""
            
            ██╗███╗   ██╗██╗   ██╗███████╗███╗   ██╗████████╗ █████╗ ██████╗ ██╗ ██████╗ 
            ██║████╗  ██║██║   ██║██╔════╝████╗  ██║╚══██╔══╝██╔══██╗██╔══██╗██║██╔═══██╗
            ██║██╔██╗ ██║██║   ██║█████╗  ██╔██╗ ██║   ██║   ███████║██████╔╝██║██║   ██║
            ██║██║╚██╗██║╚██╗ ██╔╝██╔══╝  ██║╚██╗██║   ██║   ██╔══██║██╔══██╗██║██║   ██║
            ██║██║ ╚████║ ╚████╔╝ ███████╗██║ ╚████║   ██║   ██║  ██║██║  ██║██║╚██████╔╝
            ╚═╝╚═╝  ╚═══╝  ╚═══╝  ╚══════╝╚═╝  ╚═══╝   ╚═╝   ╚═╝  ╚═╝╚═╝  ╚═╝╚═╝ ╚═════╝ 
            
                                ╔════════════════════════════════════╗
                                ║         BACKEND INICIADO           ║
                                ║                                    ║
                                ║         INVENTARIO-SYSTEM          ║
                                ║         STATUS: ● ONLINE           ║
                                ╚════════════════════════════════════╝
            
            """);
    }
}
