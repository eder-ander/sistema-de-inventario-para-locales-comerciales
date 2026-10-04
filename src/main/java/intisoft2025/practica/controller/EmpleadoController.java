package intisoft2025.practica.controller;

import intisoft2025.practica.dto.empleado.EditarEmpleadoRequestDTO;
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
     * Crear un nuevo empleado para una empres
     * @param dto
     */

    //corregir funcion, se ve dificil de mantener
    @PostMapping()
    @PreAuthorize("hasAnyRole('SUPERADMIN','ADMIN')")
    public ResponseEntity<RespuestaApi<EmpleadoRequestDTO>> crearEmpleado(
            @AuthenticationPrincipal CustomUserDetails usuario, @RequestBody EmpleadoRequestDTO dto) {

        Empleado nuevoEmpleado = empleadoService.crearEmpleado(usuario, dto);
        EmpleadoRequestDTO responseDto = new EmpleadoRequestDTO(nuevoEmpleado);

        RespuestaApi<EmpleadoRequestDTO> response = new RespuestaApi<>(true, "Empleado creado con éxito", responseDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Obtener los datos de un empleado por su DNI
     */
    @GetMapping("/{dni}")
    @PreAuthorize("hasAnyRole('SUPERADMIN', 'ADMIN')")
    public ResponseEntity<RespuestaApi<EmpleadoRequestDTO>> datosEmpleado(
            @PathVariable String dni, @AuthenticationPrincipal CustomUserDetails usuario) {

        EmpleadoRequestDTO dto = empleadoService.datosEmpleado(dni, usuario);

        RespuestaApi<EmpleadoRequestDTO> response = new RespuestaApi<>(true, "Datos de empleado recuperados", dto);
        return ResponseEntity.ok(response);
    }

    /**
     * Editar datos de un empleado existente
     */
    @PutMapping("/{dni}")
    @PreAuthorize("hasAnyRole('SUPERADMIN','ADMIN')")
    public ResponseEntity<RespuestaApi<EmpleadoRequestDTO>> editarEmpleado(
            @AuthenticationPrincipal CustomUserDetails user,
            @PathVariable String dni,
            @RequestBody EditarEmpleadoRequestDTO dto) {

        Empleado empleadoEditado = empleadoService.editarDatoEmpleado(user, dni, dto);
        EmpleadoRequestDTO responseDto = new EmpleadoRequestDTO(empleadoEditado);

        RespuestaApi<EmpleadoRequestDTO> response = new RespuestaApi<>(true, "Empleado actualizado con éxito", responseDto);
        return ResponseEntity.ok(response);
    }

}
