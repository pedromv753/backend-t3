package pe.edu.cibertec.patitas_backend_a.service.impl;

import pe.edu.cibertec.patitas_backend_a.dto.LogRequestDTO;
import pe.edu.cibertec.patitas_backend_a.dto.LoginRequestDTO;

import java.io.IOException;
import java.util.Date;

public interface Autenticarservicio {

    String[] validarUsuario(LoginRequestDTO loginRequestDTO) throws IOException;

    Date cerrarSesion(LogRequestDTO logoutRequestDTO) throws IOException;
}


