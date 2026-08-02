package intisoft2025.practica.controller;

import intisoft2025.practica.dto.EmpresaRequestDto;
import intisoft2025.practica.dto.RespuestaApi;
import intisoft2025.practica.model.Empresa;
import intisoft2025.practica.service.IEmpresaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

public class EmpresaController {

    private final IEmpresaService iEmpresaService;

    public EmpresaController(IEmpresaService iEmpresaService){
        this.iEmpresaService = iEmpresaService;
    }

    @PostMapping
    public ResponseEntity<RespuestaApi<Empresa>> crearEmpresa(EmpresaRequestDto empresaRequestDto){
        Empresa empresa = iEmpresaService.crearEmpresa(empresaRequestDto);
        /**
         * Aqui falta... manejar excepciones
         */
        RespuestaApi<Empresa> response = new RespuestaApi<>(true, "Empresa creada con exito", empresa);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id_empresa}")
    public ResponseEntity<RespuestaApi<EmpresaRequestDto>> datosEmpresa(@PathVariable Long id_empresa){
        EmpresaRequestDto empresa = iEmpresaService.datosEmpresa(id_empresa);

        RespuestaApi<EmpresaRequestDto> response = new RespuestaApi<>(true, "Datos empresa", empresa);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/")
    public ResponseEntity<RespuestaApi<EmpresaRequestDto>> actualizarEmpresa(@PathVariable Long id_empresa,
                                                                   EmpresaRequestDto empresaRequestDto){

        Empresa empresaActualizada = iEmpresaService.actualizarEmpresa(id_empresa, empresaRequestDto);
        EmpresaRequestDto dto = new EmpresaRequestDto(empresaActualizada);

        RespuestaApi<EmpresaRequestDto> responde = new RespuestaApi<>(true, "Empresa actualizada", dto);
        return ResponseEntity.ok(responde);
    }

}
