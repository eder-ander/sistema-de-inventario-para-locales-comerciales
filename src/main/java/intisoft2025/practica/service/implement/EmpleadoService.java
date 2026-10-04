package intisoft2025.practica.service.implement;

import intisoft2025.practica.dto.empleado.EditarEmpleadoRequestDTO;
import intisoft2025.practica.dto.empleado.EmpleadoRequestDTO;
import intisoft2025.practica.exception.BadRequestException;
import intisoft2025.practica.exception.ResourceNotFoundException;
import intisoft2025.practica.model.Empleado;
import intisoft2025.practica.model.Empresa;
import intisoft2025.practica.repository.EmpleadoRepository;
import intisoft2025.practica.repository.EmpresaRepository;
import intisoft2025.practica.security.CustomUserDetails;
import intisoft2025.practica.service.IEmpleadoService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EmpleadoService implements IEmpleadoService {

    private final EmpresaRepository empresaRepository;
    private final EmpleadoRepository empleadoRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Inyeccion de dependencias
     * @param empresaRepository
     * @param empleadoRepository
     * @param passwordEncoder
     */
    public EmpleadoService(EmpresaRepository empresaRepository,
                           EmpleadoRepository empleadoRepository,
                           PasswordEncoder passwordEncoder){
        this.empresaRepository = empresaRepository;
        this.empleadoRepository = empleadoRepository;
        this.passwordEncoder = passwordEncoder;
    }



    private Empresa buscarEmpresa(Long id_empresa) {
        return empresaRepository.findById(id_empresa)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontro empresa con id = " + id_empresa));
    }

    /**
     * Metodo para crear un empleado con entrada de Dto
     * @param empleado
     * @return
     */
    @Override
    public Empleado crearEmpleado(CustomUserDetails user, EmpleadoRequestDTO empleado){
        Long idEmpresaDestino;

        if("SUPERADMIN".equals(user.getRol())){
            if(empleado.getIdEmpresaDestino() == null){
                throw new BadRequestException("El SuperAdmin debe indicar el id empresa de destino");
            }
            idEmpresaDestino = empleado.getIdEmpresaDestino();
        }else{
            idEmpresaDestino = user.getIdEmpresa();
            if("SUPERADMIN".equalsIgnoreCase(empleado.getRol())){
                throw new BadRequestException("Un Admin no puede crear un Super Admin");
            }
        }
        Empresa empresa = buscarEmpresa(idEmpresaDestino);
        Empleado nuevoEmpleado = new Empleado(
                empleado.getDni(),
                empleado.getNombres(),
                empleado.getApellidos(),
                empleado.getRol(),
                empleado.getNumero_whatsapp(),
                empleado.getCorreo(),
                empleado.isEstado_empleado(),
                empleado.getUsername()
        );

        // contraseña inicial = dnii encriptado con BCrypt
        nuevoEmpleado.setEmpresa(empresa);
        nuevoEmpleado.setPassword(passwordEncoder.encode(empleado.getDni()));
        return empleadoRepository.save(nuevoEmpleado);
    }

    /**
     * Metodo que nos sirve para editar datos de un empleado haciendo las verficaciones nesesarias.
     * @param dniEmpleado
     * @param empleado
     * @return
     */
    @Override
    public Empleado editarDatoEmpleado(CustomUserDetails user, String dniEmpleado, EditarEmpleadoRequestDTO empleado) {
        //Buscar empleado
        Empleado busquedaEmpleado = empleadoRepository
                        .findById(dniEmpleado)
                        .orElseThrow(() -> new ResourceNotFoundException("No se encontro empleado con id = " + dniEmpleado));

        if("ADMIN".equalsIgnoreCase(user.getRol())){
            if(!user.getIdEmpresa().equals(busquedaEmpleado.getEmpresa().getId())){
                throw new BadRequestException("Acceso Denegado: Ese empleado no pertenence a su empresa.");
            }
        }

        busquedaEmpleado.setNombre(empleado.getNombres());
        busquedaEmpleado.setApellido(empleado.getApellidos());
        busquedaEmpleado.setNumero_whatsapp(empleado.getNumero_whatsapp());
        busquedaEmpleado.setCorreo(empleado.getCorreo());

        return empleadoRepository.save(busquedaEmpleado);
    }

    /**
     * Devuelve los datos de un unico empleado.
     * @param usuario
     * @param dniEmpleado
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public EmpleadoRequestDTO datosEmpleado(String dniEmpleado, CustomUserDetails usuario) {
        Empleado empleado = empleadoRepository
                .findById(dniEmpleado)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontro empleado con dni = " + dniEmpleado));

        if("ADMIN".equals(usuario.getRol())){
            if(!empleado.getEmpresa().getId().equals(usuario.getIdEmpresa())){
                throw new BadRequestException("El empleado no pertenece a tu empresa");
            }
        }

        return new EmpleadoRequestDTO(empleado);
    }
}

