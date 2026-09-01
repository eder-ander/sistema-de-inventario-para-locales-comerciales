package intisoft2025.practica.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_productos", length = 50, nullable = false)
    private String nombre;

    @Column(name = "precio_unitario", nullable = false, precision = 10, scale = 2)
    private BigDecimal precio;

    @Column(nullable = false)
    private Integer cantidad;

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL)
    @JsonManagedReference("producto-detalle")
    List<DetalleVenta> detalleVentas = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "id_empresa", nullable = false)
    @JsonBackReference("empresa_producto")
    private Empresa empresa;

    public Producto(String nombre, BigDecimal precio, int cantidad) {
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    public Producto() {
    }


    @Override
    public String toString() {
        return "Producto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", cantidad=" + cantidad +
                '}';
    }
}
