package intisoft2025.practica.dto.venta;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class VentaRequestDTO {
    private String dniEmpleado;
    private List<DetalleRequestDTO> productos;
}
