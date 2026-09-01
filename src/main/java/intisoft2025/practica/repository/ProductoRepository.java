package intisoft2025.practica.repository;

import intisoft2025.practica.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    List<Producto> findByEmpresaId(Long idEmpresa);
}
