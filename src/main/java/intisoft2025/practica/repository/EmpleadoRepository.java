package intisoft2025.practica.repository;

import intisoft2025.practica.model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface EmpleadoRepository extends JpaRepository<Empleado, String> {
    Optional<Empleado> findByUsername(String username);
}
