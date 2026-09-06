package intisoft2025.practica.controller;

import intisoft2025.practica.dto.empleado.EmpleadoRequestDTO;
import intisoft2025.practica.dto.common.RespuestaApi;
import intisoft2025.practica.model.Empleado;
import intisoft2025.practica.security.CustomUserDetails;
import intisoft2025.practica.service.IEmpleadoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/empleados")
public class EmpleadoController {

    private final IEmpleadoService empleadoService;

    public EmpleadoController(IEmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    /**
     * Crear un nuevo empleado para una empresa
     * @param id_empresa
     * @param dto
     */
    @PostMapping("/{id_empresa}")
    @PreAuthorize("hasRole('SUPERADMIN') or (hasRole('ADMIN') and authentication.principal.idEmpresa == #id_empresa)")
    public ResponseEntity<RespuestaApi<EmpleadoRequestDTO>> crearEmpleado(
            @PathVariable Long id_empresa,
            @RequestBody EmpleadoRequestDTO dto) {

        Empleado nuevoEmpleado = empleadoService.crearEmpleado(dto, id_empresa);
        EmpleadoRequestDTO responseDto = new EmpleadoRequestDTO(nuevoEmpleado);

        RespuestaApi<EmpleadoRequestDTO> response = new RespuestaApi<>(true, "Empleado creado con éxito", responseDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Obtener los datos de un empleado por su DNI y empresa
     */
    @GetMapping("/{dni}")
    @PreAuthorize("hasAnyRole('SUPERADMIN', 'ADMIN')")
    public ResponseEntity<RespuestaApi<EmpleadoRequestDTO>> datosEmpleado(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @PathVariable String dni) {

        EmpleadoRequestDTO dto = empleadoService.datosEmpleado(customUserDetails.getIdEmpresa(), dni);

        RespuestaApi<EmpleadoRequestDTO> response = new RespuestaApi<>(true, "Datos de empleado recuperados", dto);
        return ResponseEntity.ok(response);
    }

    /**
     * Editar datos de un empleado existente
     */
    @PutMapping("/{dni}")
    @PreAuthorize("hasAnyRole('SUPERADMIN','ADMIN')")
    public ResponseEntity<RespuestaApi<EmpleadoRequestDTO>> editarEmpleado(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @PathVariable String dni,
            @RequestBody EmpleadoRequestDTO dto) {

        Empleado empleadoEditado = empleadoService.editarDatoEmpleado(customUserDetails.getIdEmpresa(), dni, dto);
        EmpleadoRequestDTO responseDto = new EmpleadoRequestDTO(empleadoEditado);

        RespuestaApi<EmpleadoRequestDTO> response = new RespuestaApi<>(true, "Empleado actualizado con éxito", responseDto);
        return ResponseEntity.ok(response);
    }
}
