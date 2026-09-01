package intisoft2025.practica.dto.empleado;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {
    private String username;
    private String token;
    private String rol;

    public LoginResponse(String username, String token, String rol){
        this.username = username;
        this.token = token;
        this.rol = rol;
    }

    public LoginResponse(){}
}
