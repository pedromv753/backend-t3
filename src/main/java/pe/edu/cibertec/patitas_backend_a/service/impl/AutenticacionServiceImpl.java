package pe.edu.cibertec.patitas_backend_a.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.patitas_backend_a.dto.LogRequestDTO;
import pe.edu.cibertec.patitas_backend_a.dto.LoginRequestDTO;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.Date;

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
    public Date cerrarSesion(pe.edu.cibertec.patitas_backend_a.dto.LogRequestDTO logoutRequestDTO) throws IOException {


        Date fechaLogout = null;
        //Resource resource = resourceLoader.getResource("auditoria.txt");
        Path rutaArchivo = Paths.get("log.txt");
        //File file = resource.getFile();
        try (BufferedWriter bw = Files.newBufferedWriter(rutaArchivo, StandardOpenOption.APPEND)) {

            // definir fecha
            fechaLogout = new Date();

            // preparar linea
            StringBuilder sb = new StringBuilder();
            sb.append(logoutRequestDTO.tipoDocumento());
            sb.append(";");
            sb.append(logoutRequestDTO.numeroDocumento());
            sb.append(";");
            sb.append(fechaLogout);

            // escribir linea
            bw.write(sb.toString());
            bw.newLine();
            System.out.println(sb.toString());

        } catch (IOException e) {

            fechaLogout = null;
            throw new IOException(e);

        }

        return fechaLogout;

    }
}