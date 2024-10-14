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

    @PostMapping("/log")
    public LogResponseDTO log(@RequestBody LogRequestDTO logRequestDTO) {

        try {

            Thread.sleep(Duration.ofSeconds(5));
            autenticacionService.registrarLogout(logRequestDTO);

            return new LogResponseDTO("00", "Log satisfactoriamente ");

        } catch (IOException e) {

            return new LogResponseDTO("99", "tenemos un problema");

        } catch (InterruptedException e) {

            throw new RuntimeException(e);

        }

    }

}
