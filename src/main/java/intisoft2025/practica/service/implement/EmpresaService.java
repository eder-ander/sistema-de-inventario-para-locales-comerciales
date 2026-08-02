package intisoft2025.practica.service.implement;

import intisoft2025.practica.dto.EmpresaRequestDto;
import intisoft2025.practica.model.Empresa;
import intisoft2025.practica.repository.EmpresaRepository;
import intisoft2025.practica.service.IEmpresaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EmpresaService implements IEmpresaService {
    private final EmpresaRepository empresaRepository;

    public EmpresaService(EmpresaRepository empresaRepository){
        this.empresaRepository = empresaRepository;
    }

    @Override
    public Empresa crearEmpresa(EmpresaRequestDto empresaRequestDto) {
        Empresa empresa = new Empresa(
                empresaRequestDto.getNombre(),
                empresaRequestDto.getRubro(),
                empresaRequestDto.getNumero_whatsapp(),
                empresaRequestDto.getCorreo(),
                empresaRequestDto.getDireccion()
        );

        return empresaRepository.save(empresa);
    }

    @Override
    @Transactional(readOnly = true)
    public EmpresaRequestDto datosEmpresa(Long idEmpresa) {
        Empresa empresa = empresaRepository.findById(idEmpresa).
                orElseThrow(() -> new RuntimeException("No se encontro empresa con id = " + idEmpresa));
        return new EmpresaRequestDto(empresa);
    }

    @Override
    public Empresa actualizarEmpresa(Long idEmpresa, EmpresaRequestDto empresaRequestDto) {
        Empresa empresa = empresaRepository.findById(idEmpresa).
                orElseThrow(() -> new RuntimeException("No se encontro empresa con id = " + idEmpresa));

        empresa.setNombre(empresaRequestDto.getNombre());
        empresa.setRubro(empresaRequestDto.getRubro());
        empresa.setNumero_whatsapp(empresaRequestDto.getNumero_whatsapp());
        empresa.setCorreo(empresaRequestDto.getCorreo());
        empresa.setDireccion(empresaRequestDto.getDireccion());

        return empresaRepository.save(empresa);
    }
}
