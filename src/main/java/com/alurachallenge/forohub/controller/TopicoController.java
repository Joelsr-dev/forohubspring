package com.alurachallenge.forohub.controller;

import com.alurachallenge.forohub.dto.TopicoDTO;
import com.alurachallenge.forohub.model.Curso;
import com.alurachallenge.forohub.model.Topico;
import com.alurachallenge.forohub.usuarios.Usuario;
import com.alurachallenge.forohub.repository.CursoRepository;
import com.alurachallenge.forohub.repository.TopicoRepository;
import com.alurachallenge.forohub.usuarios.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("/forohub/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;


    //Crear topico
    @PostMapping
    public ResponseEntity<TopicoDTO> crearTopico(@RequestBody @Valid TopicoDTO topicoDTO) {
        Optional<Curso> curso = cursoRepository.findById(topicoDTO.cursoId());
        Optional<Usuario> autor = usuarioRepository.findById(topicoDTO.autorId());

        if (curso.isEmpty() || autor.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        Topico topico = new Topico();
        topico.setTitulo(topicoDTO.titulo());
        topico.setMensaje(topicoDTO.mensaje());
        topico.setAutor(autor.get());
        topico.setCurso(curso.get());
        topico.setFechaCreacion(LocalDate.now().atStartOfDay());
        topico.setStatus(topicoDTO.estado() != null ? topicoDTO.estado() : "ACTIVO");

        topicoRepository.save(topico);

        return ResponseEntity.ok(new TopicoDTO(topico.getId(), topico.getTitulo(), topico.getMensaje(),
                topico.getAutor().getId(), topico.getCurso().getId(), topico.getFechaCreacion(), topico.getStatus()));
    }

    //Listar los topicos
    @GetMapping
    public Page<TopicoDTO> listarTopicos(@RequestParam(name = "curso", required = false) String cursoNombre,
                                         @RequestParam(name = "anio", required = false) String anio,
                                         @RequestParam(name = "page", defaultValue = "0") int page,
                                         @RequestParam(name = "size", defaultValue = "10") int size) {

        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Topico> topicos;

        if (cursoNombre == null) cursoNombre = "";

        if (anio != null) {
            LocalDate startDate = LocalDate.parse(anio + "-01-01");
            LocalDate endDate = LocalDate.parse(anio + "-12-31");
            topicos = topicoRepository.findAllByCursoNombreContainingAndFechaCreacionBetween(cursoNombre, startDate.atStartOfDay(), endDate.atTime(23, 59), pageRequest);
        } else {
            topicos = topicoRepository.findAllByCursoNombreContaining(cursoNombre, pageRequest);
        }

        return topicos.map(topico -> new TopicoDTO(topico.getId(), topico.getTitulo(), topico.getMensaje(),
                topico.getAutor().getId(), topico.getCurso().getId(), topico.getFechaCreacion(), topico.getStatus()));
    }

    //Modificar solo un valor del topico
    @PatchMapping("/{id}")
    public ResponseEntity<TopicoDTO> modificarTopico(@PathVariable Long id, @RequestBody @Valid TopicoDTO topicoDTO) {
        Optional<Topico> topicoExistente = topicoRepository.findById(id);
        if (topicoExistente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Topico topico = topicoExistente.get();

        if (topicoDTO.titulo() != null) topico.setTitulo(topicoDTO.titulo());
        if (topicoDTO.mensaje() != null) topico.setMensaje(topicoDTO.mensaje());
        if (topicoDTO.estado() != null) topico.setStatus(topicoDTO.estado());
        if (topicoDTO.cursoId() != null) {
            Optional<Curso> curso = cursoRepository.findById(topicoDTO.cursoId());
            if (curso.isPresent()) {
                topico.setCurso(curso.get());
            }
        }
        if (topicoDTO.autorId() != null) {
            Optional<Usuario> autor = usuarioRepository.findById(topicoDTO.autorId());
            if (autor.isPresent()) {
                topico.setAutor(autor.get());
            }

        }

        topicoRepository.save(topico);

        return ResponseEntity.ok(new TopicoDTO(topico.getId(), topico.getTitulo(), topico.getMensaje(),
                topico.getAutor().getId(), topico.getCurso().getId(), topico.getFechaCreacion(), topico.getStatus()));
    }

    //Listar topico por ID
    @GetMapping("/{id}")
    public ResponseEntity<TopicoDTO> buscarTopicoPorId(@PathVariable Long id) {
        Optional<Topico> topico = topicoRepository.findById(id);

        if (topico.isEmpty()) {
            return ResponseEntity.notFound().build();  // Retorna 404 si no se encuentra el tópico
        }

        TopicoDTO topicoDTO = new TopicoDTO(
                topico.get().getId(),
                topico.get().getTitulo(),
                topico.get().getMensaje(),
                topico.get().getAutor().getId(),
                topico.get().getCurso().getId(),
                topico.get().getFechaCreacion(),
                topico.get().getStatus()
        );

        return ResponseEntity.ok(topicoDTO);  // Retorna el tópico encontrado con código 200
    }

    //Eliminar topico
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTopico(@PathVariable Long id) {
        // Verificar si el tópico existe en la base de datos
        Optional<Topico> topicoExistente = topicoRepository.findById(id);

        // Si el tópico no existe, retornamos un 404 Not Found
        if (topicoExistente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // Si el tópico existe, lo eliminamos de la base de datos
        topicoRepository.deleteById(id);

        // Retornamos un 204 No Content si la eliminación fue exitosa
        return ResponseEntity.noContent().build();
    }



}
