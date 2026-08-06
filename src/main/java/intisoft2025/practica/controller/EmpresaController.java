package intisoft2025.practica.controller;

import intisoft2025.practica.dto.empresa.EmpresaRequestDto;
import intisoft2025.practica.dto.common.RespuestaApi;
import intisoft2025.practica.model.Empresa;
import intisoft2025.practica.service.IEmpresaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/empresa")
public class EmpresaController {

    private final IEmpresaService iEmpresaService;

    public EmpresaController(IEmpresaService iEmpresaService){
        this.iEmpresaService = iEmpresaService;
    }

    @PostMapping
    public ResponseEntity<RespuestaApi<EmpresaRequestDto>> crearEmpresa(@RequestBody EmpresaRequestDto empresaRequestDto){
        Empresa empresa = iEmpresaService.crearEmpresa(empresaRequestDto);
        EmpresaRequestDto dto = new EmpresaRequestDto(empresa);
        RespuestaApi<EmpresaRequestDto> response = new RespuestaApi<>(true, "Empresa creada con éxito", dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id_empresa}")
    public ResponseEntity<RespuestaApi<EmpresaRequestDto>> datosEmpresa(@PathVariable Long id_empresa){
        EmpresaRequestDto empresa = iEmpresaService.datosEmpresa(id_empresa);

        RespuestaApi<EmpresaRequestDto> response = new RespuestaApi<>(true, "Datos empresa", empresa);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id_empresa}")
    public ResponseEntity<RespuestaApi<EmpresaRequestDto>> actualizarEmpresa(@PathVariable Long id_empresa,
                                                                   @RequestBody EmpresaRequestDto empresaRequestDto){

        Empresa empresaActualizada = iEmpresaService.actualizarEmpresa(id_empresa, empresaRequestDto);
        EmpresaRequestDto dto = new EmpresaRequestDto(empresaActualizada);

        RespuestaApi<EmpresaRequestDto> response = new RespuestaApi<>(true, "Empresa actualizada", dto);
        return ResponseEntity.ok(response);
    }
}
