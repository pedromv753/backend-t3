package pe.edu.cibertec.patitas_backend_a.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record LoginRequestDTO(String tipoDocumento, String numeroDocumento, String password) {
}
