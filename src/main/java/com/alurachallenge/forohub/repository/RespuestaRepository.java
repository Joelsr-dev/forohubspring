package com.alurachallenge.forohub.repository;

import com.alurachallenge.forohub.model.Respuesta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RespuestaRepository extends JpaRepository<Respuesta, Long> {

    Optional<Respuesta> findById(Long id);
}
