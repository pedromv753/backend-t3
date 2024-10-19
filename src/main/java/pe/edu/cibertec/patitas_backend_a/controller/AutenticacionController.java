package pe.edu.cibertec.patitas_backend_a.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.cibertec.patitas_backend_a.dto.LogRequestDTO;
import pe.edu.cibertec.patitas_backend_a.dto.LogResponseDTO;
import pe.edu.cibertec.patitas_backend_a.dto.LoginRequestDTO;
import pe.edu.cibertec.patitas_backend_a.dto.LoginResponseDTO;
import pe.edu.cibertec.patitas_backend_a.service.impl.Autenticarservicio;


import java.io.IOException;
import java.time.Duration;
import java.util.Date;


@RestController
@RequestMapping("/autenticacion")
public class AutenticacionController {

    @Autowired
    private Autenticarservicio autenticacionService;

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginRequestDTO loginRequestDTO) {

        try{

            Thread.sleep(Duration.ofSeconds(5));
            String[] datosUsuario = autenticacionService.validarUsuario((loginRequestDTO));

            if(datosUsuario == null){
                return new LoginResponseDTO("01", "Error: usuario no encontrado.", "", "");
            }

            return new LoginResponseDTO("00", "", datosUsuario[0], datosUsuario[1]);

        } catch (IOException e) {

            return new LoginResponseDTO("99", "Error: Ocurrió un problema", "", "");

        } catch (InterruptedException e) {

            throw new RuntimeException(e);

        }

    }

    @PostMapping("/logout")
    public LogResponseDTO logout(@RequestBody LogRequestDTO logoutRequestDTO) {
        try {
            Thread.sleep(Duration.ofSeconds(5));//tiempo en que va a devolver la respuesta
            Date fechaLogout = autenticacionService.cerrarSesion(logoutRequestDTO);
            System.out.println("Respuesta backend : " + fechaLogout);

            if (fechaLogout == null) {
                return new LogResponseDTO(false, null, "Error: No se pudo registrar auditoria");
            }
            return new LogResponseDTO(true,fechaLogout,"Sesion Cerrada Con Exito");


        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new LogResponseDTO(false, null, "Error: Ocurrio un problema");
        }
    }

}