package intisoft2025.practica.service;

import intisoft2025.practica.dto.empleado.EditarEmpleadoRequestDTO;
import intisoft2025.practica.dto.empleado.EmpleadoRequestDTO;
import intisoft2025.practica.model.Empleado;
import intisoft2025.practica.security.CustomUserDetails;

public interface IEmpleadoService {

    Empleado crearEmpleado(CustomUserDetails user,EmpleadoRequestDTO empleado);

    Empleado editarDatoEmpleado(CustomUserDetails user, String dniEmpleado, EditarEmpleadoRequestDTO empleado);

    EmpleadoRequestDTO datosEmpleado(String dniEmpleado, CustomUserDetails usuario);
}
