package intisoft2025.practica.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, updatable = false)
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "UTC"
    )
    private Instant fecha;

    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL)
    @JsonManagedReference("venta-detalle")
    List<DetalleVenta> detalleVentas = new ArrayList<>();

    @PrePersist
    protected void onCreated(){
        this.fecha = Instant.now();
    }

    public void añadirDetalleVenta(DetalleVenta detalleVenta){
        this.detalleVentas.add(detalleVenta);
        detalleVenta.setVenta(this);
    }

    public Venta(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getFecha() {
        return fecha;
    }

    public void setFecha(Instant fecha) {
        this.fecha = fecha;
    }

    public List<DetalleVenta> getDetalleVentas() {
        return detalleVentas;
    }

    public void setDetalleVentas(List<DetalleVenta> detalleVentas) {
        this.detalleVentas = detalleVentas;
    }
}
