package intisoft2025.practica.dto.empleado;

import intisoft2025.practica.model.Empleado;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EmpleadoRequestDTO {

    private String dni;
    private String rol;
    private String nombres;
    private String apellidos;
    private String numero_whatsapp;
    private String correo;
    private boolean estado_empleado;

    public EmpleadoRequestDTO(Empleado e) {
        this.dni = e.getDni();
        this.rol = e.getRol();
        this.nombres = e.getNombre();
        this.apellidos = e.getApellido();
        this.numero_whatsapp = e.getNumero_whatsapp();
        this.correo = e.getCorreo();
        this.estado_empleado = e.isEstado_empleado();
    }
}
