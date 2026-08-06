package intisoft2025.practica.service.implement;

import intisoft2025.practica.dto.empleado.EmpleadoRequestDTO;
import intisoft2025.practica.model.Empleado;
import intisoft2025.practica.model.Empresa;
import intisoft2025.practica.repository.EmpleadoRepository;
import intisoft2025.practica.repository.EmpresaRepository;
import intisoft2025.practica.service.IEmpleadoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EmpleadoService implements IEmpleadoService {

    private final EmpresaRepository empresaRepository;
    private final EmpleadoRepository empleadoRepository;

    /**
     * Inyeccion de dependencias
     * @param empresaRepository
     * @param empleadoRepository
     */
    public EmpleadoService(EmpresaRepository empresaRepository, EmpleadoRepository empleadoRepository){
        this.empresaRepository = empresaRepository;
        this.empleadoRepository = empleadoRepository;
    }


    private Empresa buscarEmpresa(Long id_empresa) {
        return empresaRepository.findById(id_empresa)
                .orElseThrow(() -> new RuntimeException("No se encontro empresa"));
    }

    /**
     * Metodo para crear un empleado con entrada de Dto
     * @param empleado
     * @param id_empresa
     * @return
     */
    @Override
    public Empleado crearEmpleado(EmpleadoRequestDTO empleado, Long id_empresa){
        Empresa empresa = buscarEmpresa(id_empresa);

        Empleado nuevoEmpleado = new Empleado(
                empleado.getDni(),
                empleado.getNombres(),
                empleado.getApellidos(),
                empleado.getRol(),
                empleado.getNumero_whatsapp(),
                empleado.getCorreo(),
                empleado.isEstado_empleado()
        );

        //a futuro manejar un encriptador de contraseñas
        nuevoEmpleado.setEmpresa(empresa);
        nuevoEmpleado.setPassword(empleado.getDni());

        return empleadoRepository.save(nuevoEmpleado);
    }

    /**
     * Metodo que nos sirve para editar datos de un empleado haciendo las verficaciones nesesarias.
     * @param idEmpresa
     * @param dniEmpleado
     * @param empleado
     * @return
     */
    @Override
    public Empleado editarDatoEmpleado(Long idEmpresa, String dniEmpleado, EmpleadoRequestDTO empleado) {
        //Buscar empleado
        Empleado busquedaEmpleado =
                empleadoRepository
                        .findById(dniEmpleado)
                        .orElseThrow(() -> new RuntimeException("No se encontro empleado con id =" + dniEmpleado));
        //verificar si ese empleado pertenenece a esa empresa
        if(!busquedaEmpleado.getEmpresa().getId().equals(idEmpresa)) throw new RuntimeException("No tienes acceso a esta empresa.");

        busquedaEmpleado.setNombre(empleado.getNombres());
        busquedaEmpleado.setApellido(empleado.getApellidos());
        busquedaEmpleado.setRol(empleado.getRol());
        busquedaEmpleado.setNumero_whatsapp(empleado.getNumero_whatsapp());
        busquedaEmpleado.setCorreo(empleado.getCorreo());

        return empleadoRepository.save(busquedaEmpleado);
    }

    /**
     * Devuelve los datos de un unico empleado.
     * @param idEmpresa
     * @param dniEmpleado
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public EmpleadoRequestDTO datosEmpleado(Long idEmpresa, String dniEmpleado) {
        Empleado empleado = empleadoRepository
                .findById(dniEmpleado)
                .orElseThrow(() -> new RuntimeException("No se encontro empleado con id = " + dniEmpleado));

        if (!empleado.getEmpresa().getId().equals(idEmpresa)) {
            throw new RuntimeException("Acceso denegado: El empleado no pertenece a tu empresa");
        }

        return new EmpleadoRequestDTO(empleado);
    }
}
