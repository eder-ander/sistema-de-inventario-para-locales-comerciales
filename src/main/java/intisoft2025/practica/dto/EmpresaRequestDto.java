package intisoft2025.practica.dto;

import intisoft2025.practica.model.Empresa;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class EmpresaRequestDto {
    private Long id;
    private String nombre;
    private String rubro;
    private String numero_whatsapp;
    private String correo;
    private String direccion;

    public EmpresaRequestDto(Empresa e) {
        this.nombre = e.getNombre();
        this.rubro = e.getRubro();
        this.numero_whatsapp = e.getNumero_whatsapp();
        this.correo = e.getCorreo();
        this.direccion = e.getDireccion();
    }

    public EmpresaRequestDto(){}

}
