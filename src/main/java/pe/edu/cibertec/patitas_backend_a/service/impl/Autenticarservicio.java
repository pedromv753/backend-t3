package pe.edu.cibertec.patitas_backend_a.service.impl;

import pe.edu.cibertec.patitas_backend_a.dto.LogRequestDTO;
import pe.edu.cibertec.patitas_backend_a.dto.LoginRequestDTO;

import java.io.IOException;

public interface Autenticarservicio {

    String[] validarUsuario(LoginRequestDTO loginRequestDTO) throws IOException;
    void registrarLogout(LogRequestDTO logRequestDTO) throws IOException;

}
