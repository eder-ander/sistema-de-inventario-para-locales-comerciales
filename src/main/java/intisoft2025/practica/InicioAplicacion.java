package intisoft2025.practica;

import intisoft2025.practica.model.Empleado;
import intisoft2025.practica.model.Empresa;
import intisoft2025.practica.repository.EmpleadoRepository;
import intisoft2025.practica.repository.EmpresaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class InicioAplicacion implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(InicioAplicacion.class, args);
    }

    private final EmpleadoRepository empleadoRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmpresaRepository empresaRepository;

    //inyeccion de dependencias
    public InicioAplicacion(EmpleadoRepository empleadoRepository,
                            PasswordEncoder passwordEncoder,
                            EmpresaRepository empresaRepository){
        this.empleadoRepository = empleadoRepository;
        this.passwordEncoder = passwordEncoder;
        this.empresaRepository  = empresaRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Empresa empresa = new Empresa("empresa1","drogas","000002323","123456788","212123123123");
        empresaRepository.save(empresa);
        Empleado empleado = new Empleado(
            "000000000",
            "juan",
            "perez",
            "SUPERADMIN",
            "921420759",
            "ederander123@gmail.com",
            true,
            "juancito"
        );
        empleado.setPassword("000000000");
        empleado.setPassword(passwordEncoder.encode(empleado.getPassword()));


        empleado.setEmpresa(empresa);
        empleadoRepository.save(empleado);

        System.out.println(empresa);
        System.out.println(empleado);
    }
}
