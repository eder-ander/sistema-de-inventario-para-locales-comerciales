package intisoft2025.practica.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "empresa")
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "nombre_empresa", length = 30)
    private String nombre;

    @Column(nullable = false, length = 20)
    private String rubro;

    @Column(unique = true, nullable = false)
    private String numero_whatsapp;

    @Column(unique = true, nullable = false, length = 30)
    private String correo;

    @Column(nullable = false, length = 40)
    private String direccion;

    @OneToMany(mappedBy = "empresa")
    @JsonManagedReference("empresa_empleado")
    private List<Empleado> empleado;

    @OneToMany(mappedBy = "empresa")
    @JsonManagedReference("empresa_producto")
    private List<Producto> productos;

    public Empresa(String nombre, String rubro, String numero_whatsapp, String correo, String direccion){
        this.nombre = nombre;
        this.rubro = rubro;
        this.numero_whatsapp = numero_whatsapp;
        this.correo = correo;
        this.direccion = direccion;
    }

    public Empresa(){}

}
