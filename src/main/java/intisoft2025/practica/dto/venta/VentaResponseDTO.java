package intisoft2025.practica.dto.venta;

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
}
