package intisoft2025.practica.dto.venta;

import intisoft2025.practica.model.DetalleVenta;
import intisoft2025.practica.model.Venta;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class VentaResponseDTO {
    private Long id;
    private Instant fecha;
    private List<DetalleVentaResponseDTO> detalles;

    public VentaResponseDTO(Venta venta) {
        this.id = venta.getId();
        this.fecha = venta.getFecha();
        if (venta.getDetalleVentas() != null) {
            this.detalles = venta.getDetalleVentas().stream()
                    .map(DetalleVentaResponseDTO::new)
                    .toList();
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class DetalleVentaResponseDTO {
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
}
