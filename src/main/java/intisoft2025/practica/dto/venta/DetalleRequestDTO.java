package intisoft2025.practica.dto.venta;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DetalleRequestDTO {
    private Long productoId;
    private Integer cantidad;
}
