package intisoft2025.practica.dto.empresa;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class EmpresasResponseDto {
    private List<EmpresaResponseDto> empresas;

    public EmpresasResponseDto(List<EmpresaResponseDto> d){
        this.empresas = d;
    }
}
