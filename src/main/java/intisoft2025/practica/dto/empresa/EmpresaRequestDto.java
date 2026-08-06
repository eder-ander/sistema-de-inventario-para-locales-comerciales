package intisoft2025.practica.dto.empresa;

import intisoft2025.practica.model.Empresa;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EmpresaRequestDto {
    private Long id;
    private String nombre;
    private String rubro;
    private String numero_whatsapp;
    private String correo;
    private String direccion;

    public EmpresaRequestDto(Empresa e) {
        this.id = e.getId();
        this.nombre = e.getNombre();
        this.rubro = e.getRubro();
        this.numero_whatsapp = e.getNumero_whatsapp();
        this.correo = e.getCorreo();
        this.direccion = e.getDireccion();
    }
}
