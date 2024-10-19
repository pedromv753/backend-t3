package pe.edu.cibertec.patitas_backend_a.dto;

import java.util.Date;

public record LogResponseDTO(Boolean resultado, Date fecha, String mensajeError) {
}
