package intisoft2025.practica.service;

import intisoft2025.practica.dto.EmpresaRequestDto;
import intisoft2025.practica.model.Empresa;

public interface IEmpresaService {

    Empresa crearEmpresa(EmpresaRequestDto empresaRequestDto);
    EmpresaRequestDto datosEmpresa(Long idEmpresa);
    Empresa actualizarEmpresa(Long idEmpresa, EmpresaRequestDto empresaRequestDto);

}
