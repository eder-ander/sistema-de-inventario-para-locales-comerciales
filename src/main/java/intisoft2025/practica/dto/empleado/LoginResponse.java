package intisoft2025.practica.dto.empleado;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {
    private String username;
    private String token;
    private String rol;
    private Long idEmpresa;

    public LoginResponse(String username, String token, String rol, Long idEmpresa){
        this.username = username;
        this.token = token;
        this.rol = rol;
        this.idEmpresa = idEmpresa;
    }

    public LoginResponse(){}
}
