package intisoft2025.practica.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "empleado")
@Getter
@Setter

public class Empleado {
    @Id
    private String dni;

    @Column(nullable = false, length = 30)
    private String nombre;

    @Column(nullable = false, length = 30)
    private String apellido;

    @Column(nullable = false, length = 9)
    private String numero_whatsapp;

    @Column(nullable = false, length = 30)
    private String correo;

    @Column(nullable = false)
    private String rol;

    @Column(nullable = false)
    private boolean estado_empleado;

    @Column(nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = "id_empresa", nullable = false)
    @JsonBackReference("empresa_empleado")
    private Empresa empresa;

    @PrePersist
    protected void onCreated(){
        this.estado_empleado = true;
    }


    public Empleado() {}
    public Empleado(String dni,
                    String nombre,
                    String apellido,
                    String rol,
                    String numero_whatsapp,
                    String correo,
                    boolean estado_empleado){
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.rol = rol;
        this.numero_whatsapp = numero_whatsapp;
        this.correo = correo;
        this.estado_empleado = estado_empleado;
    }

}
