package intisoft2025.practica.dto.empleado;

import intisoft2025.practica.model.Empleado;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EditarEmpleadoRequestDTO {

    private String nombres;
    private String apellidos;
    private String numero_whatsapp;
    private String correo;

    public EditarEmpleadoRequestDTO(Empleado e) {
        this.nombres = e.getNombre();
        this.apellidos = e.getApellido();
        this.numero_whatsapp = e.getNumero_whatsapp();
        this.correo = e.getCorreo();
    }
}
