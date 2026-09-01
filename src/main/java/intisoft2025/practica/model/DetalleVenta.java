package intisoft2025.practica.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "detalle_venta")
public class DetalleVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)
    @JsonBackReference("producto-detalle")
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "venta_id", nullable = false)
    @JsonBackReference("venta-detalle")
    private Venta venta;

    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

    @Column(name = "precio_unitario", nullable = false, precision = 10, scale = 2)
    private BigDecimal precio;

    public DetalleVenta(Producto producto, Venta venta, Integer cantidad){
        this.producto = producto;
        this.venta = venta;
        this.cantidad = cantidad;
        this.precio = producto.getPrecio();
    }
}
