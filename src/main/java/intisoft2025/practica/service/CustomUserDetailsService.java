package intisoft2025.practica.service;

import intisoft2025.practica.model.Empleado;
import intisoft2025.practica.repository.EmpleadoRepository;
import intisoft2025.practica.security.CustomUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final EmpleadoRepository empleadoRepository;
    public CustomUserDetailsService(EmpleadoRepository empleadoRepository){
        this.empleadoRepository = empleadoRepository;
    }

    /**
     * Carga los datos del empleado por su nombre de usuario (username)
     * y los mapea a la clase de seguridad CustomUserDetails.
     * 
     * Incluye validación segura para idEmpresa (permite superadministradores sin empresa asignada).
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Empleado empleado = empleadoRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("No se encontro el nombre de usuario = " + username));

        // ponytail: mapeo seguro de atributos del empleado hacia el contexto de Spring Security
        return CustomUserDetails.builder()
                .dni(empleado.getDni())
                .username(empleado.getUsername())
                .password(empleado.getPassword())
                .rol(empleado.getRol())
                .idEmpresa(empleado.getEmpresa() != null ? empleado.getEmpresa().getId() : null)
                .estado(empleado.isEstado_empleado())
                .build();
    }
}
