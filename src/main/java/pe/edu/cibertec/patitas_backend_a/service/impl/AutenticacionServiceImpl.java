package pe.edu.cibertec.patitas_backend_a.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.patitas_backend_a.dto.LogRequestDTO;
import pe.edu.cibertec.patitas_backend_a.dto.LoginRequestDTO;

import java.io.*;
import java.time.LocalDate;

@Service
public class AutenticacionServiceImpl implements Autenticarservicio{

    @Autowired
    ResourceLoader resourceLoader;

    @Override
    public String[] validarUsuario(LoginRequestDTO loginRequestDTO) throws IOException {

        String[] datosUsuario = null;
        Resource resource = resourceLoader.getResource("classpath:usuarios.txt");

        try (BufferedReader br = new BufferedReader(new FileReader(resource.getFile()))) {

            //Ocurre algo aqui
            String linea;
            while ((linea = br.readLine()) != null) {

                String[] datos = linea.split(";");
                if (loginRequestDTO.tipoDocumento().equals(datos[0]) &&
                        loginRequestDTO.numeroDocumento().equals(datos[1]) &&
                        loginRequestDTO.password().equals(datos[2])) {

                    datosUsuario = new String[2];
                    datosUsuario[0] = datos[3]; //recuperar nombre
                    datosUsuario[1] = datos[4]; //recuperar correo
                    break;

                }
            }

        } catch (IOException e) {
            datosUsuario = null;
            throw new IOException(e);
        }


        return datosUsuario;
    }

    @Override
    public void registrarLogout(LogRequestDTO logoutRequestDTO) throws IOException {

        String path = "src/main/resources/log.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path, true))) {
            writer.write(logoutRequestDTO.tipoDocumento() + ";" + logoutRequestDTO.numeroDocumento() + ";" + LocalDate.now() + "\n");
        } catch (IOException e) {
            throw new IOException("error  en log.txt", e);
        }

    }
}