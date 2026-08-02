package intisoft2025.practica.service;

import intisoft2025.practica.dto.EmpleadoRequestDTO;
import intisoft2025.practica.model.Empleado;

public interface IEmpleadoService {

    Empleado crearEmpleado(EmpleadoRequestDTO empleado, Long id_empresa);

    Empleado editarDatoEmpleado(Long idEmpresa, String dniEmpleado, EmpleadoRequestDTO empleado);

    EmpleadoRequestDTO datosEmpleado(Long idEmpresa, String dniEmpleado);
}
