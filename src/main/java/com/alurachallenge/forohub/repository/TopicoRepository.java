package com.alurachallenge.forohub.repository;

import com.alurachallenge.forohub.model.Topico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

    Page<Topico> findAllByCursoNombreContaining(String cursoNombre, Pageable pageable);

    Page<Topico> findAllByCursoNombreContainingAndFechaCreacionBetween(String cursoNombre, LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);


    Optional<Topico> findById(Long id);
}
