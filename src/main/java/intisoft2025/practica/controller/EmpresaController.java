package intisoft2025.practica.controller;

import intisoft2025.practica.dto.empresa.EmpresaRequestDto;
import intisoft2025.practica.dto.common.RespuestaApi;
import intisoft2025.practica.model.Empresa;
import intisoft2025.practica.security.CustomUserDetails;
import intisoft2025.practica.service.IEmpresaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/empresas")
public class EmpresaController {

    private final IEmpresaService iEmpresaService;

    public EmpresaController(IEmpresaService iEmpresaService){
        this.iEmpresaService = iEmpresaService;
    }

    @PostMapping
    @PreAuthorize("hasRole('SUPERADMIN')")
    public ResponseEntity<RespuestaApi<EmpresaRequestDto>> crearEmpresa(@RequestBody EmpresaRequestDto empresaRequestDto){
        Empresa empresa = iEmpresaService.crearEmpresa(empresaRequestDto);
        EmpresaRequestDto dto = new EmpresaRequestDto(empresa);
        RespuestaApi<EmpresaRequestDto> response = new RespuestaApi<>(true, "Empresa creada con éxito", dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/mi-empresa")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<RespuestaApi<EmpresaRequestDto>> datosEmpresa(
            @AuthenticationPrincipal CustomUserDetails customUserDetails){
        EmpresaRequestDto empresa = iEmpresaService.datosEmpresa(customUserDetails.getIdEmpresa());

        RespuestaApi<EmpresaRequestDto> response = new RespuestaApi<>(true, "Datos empresa", empresa);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id_empresa}")
    @PreAuthorize("hasAnyRole('SUPERADMIN')")
    public ResponseEntity<RespuestaApi<EmpresaRequestDto>> actualizarEmpresa(@PathVariable Long id_empresa,
                                                                   @RequestBody EmpresaRequestDto empresaRequestDto){

        Empresa empresaActualizada = iEmpresaService.actualizarEmpresa(id_empresa, empresaRequestDto);
        EmpresaRequestDto dto = new EmpresaRequestDto(empresaActualizada);

        RespuestaApi<EmpresaRequestDto> response = new RespuestaApi<>(true, "Empresa actualizada", dto);
        return ResponseEntity.ok(response);
    }
}
