package com.alurachallenge.forohub.dto;

import java.time.LocalDateTime;

public record TopicoDTO(Long id,
                        String titulo,
                        String mensaje,
                        Long autorId,
                        Long cursoId,
                        LocalDateTime fechaCreacion,
                        String estado) {
}
