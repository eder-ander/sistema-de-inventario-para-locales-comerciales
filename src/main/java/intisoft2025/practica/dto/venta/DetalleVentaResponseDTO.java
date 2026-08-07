package intisoft2025.practica.dto.venta;

import intisoft2025.practica.model.DetalleVenta;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DetalleVentaResponseDTO {
    private Long idProducto;
    private String nombreProducto;
    private Integer cantidad;
    private Integer precioUnitario;
    private Integer subtotal;

    public DetalleVentaResponseDTO(DetalleVenta detalle) {
        if (detalle.getProducto() != null) {
            this.idProducto = detalle.getProducto().getId();
            this.nombreProducto = detalle.getProducto().getNombre();
        }
        this.cantidad = detalle.getCantidad();
        this.precioUnitario = detalle.getPrecio();
        this.subtotal = (detalle.getCantidad() != null && detalle.getPrecio() != null)
                ? detalle.getCantidad() * detalle.getPrecio()
                : 0;
    }
}
