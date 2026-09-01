package intisoft2025.practica.dto.producto;

import intisoft2025.practica.model.Producto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class RequestProductoDto {
    private Long id;
    private String nombre;
    private BigDecimal precio;
    private Integer cantidad;

    public RequestProductoDto(Producto p) {
        this.id = p.getId();
        this.nombre = p.getNombre();
        this.precio = p.getPrecio();
        this.cantidad = p.getCantidad();
    }
}
