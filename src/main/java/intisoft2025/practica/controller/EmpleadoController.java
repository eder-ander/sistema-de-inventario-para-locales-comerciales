package intisoft2025.practica.controller;

import intisoft2025.practica.dto.empleado.EmpleadoRequestDTO;
import intisoft2025.practica.dto.common.RespuestaApi;
import intisoft2025.practica.model.Empleado;
import intisoft2025.practica.service.IEmpleadoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/empleados")
public class EmpleadoController {

    private final IEmpleadoService empleadoService;

    public EmpleadoController(IEmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    /**
     * Crear un nuevo empleado para una empresa
     */
    @PostMapping("/{id_empresa}")
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
    @GetMapping("/{id_empresa}/{dni}")
    public ResponseEntity<RespuestaApi<EmpleadoRequestDTO>> datosEmpleado(
            @PathVariable Long id_empresa,
            @PathVariable String dni) {

        EmpleadoRequestDTO dto = empleadoService.datosEmpleado(id_empresa, dni);

        RespuestaApi<EmpleadoRequestDTO> response = new RespuestaApi<>(true, "Datos de empleado recuperados", dto);
        return ResponseEntity.ok(response);
    }

    /**
     * Editar datos de un empleado existente
     */
    @PutMapping("/{id_empresa}/{dni}")
    public ResponseEntity<RespuestaApi<EmpleadoRequestDTO>> editarEmpleado(
            @PathVariable Long id_empresa,
            @PathVariable String dni,
            @RequestBody EmpleadoRequestDTO dto) {

        Empleado empleadoEditado = empleadoService.editarDatoEmpleado(id_empresa, dni, dto);
        EmpleadoRequestDTO responseDto = new EmpleadoRequestDTO(empleadoEditado);

        RespuestaApi<EmpleadoRequestDTO> response = new RespuestaApi<>(true, "Empleado actualizado con éxito", responseDto);
        return ResponseEntity.ok(response);
    }
}
