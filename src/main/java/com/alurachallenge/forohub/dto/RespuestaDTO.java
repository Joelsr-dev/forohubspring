package com.alurachallenge.forohub.dto;

import java.time.LocalDateTime;

public record RespuestaDTO(Long id, String mensaje, Long autorId, Long topicoId, LocalDateTime fechaCreacion, boolean solucion) {
}
