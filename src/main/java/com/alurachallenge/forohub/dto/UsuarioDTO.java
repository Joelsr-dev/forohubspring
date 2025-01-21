package com.alurachallenge.forohub.dto;

import com.alurachallenge.forohub.model.Perfil;
import com.alurachallenge.forohub.usuarios.Usuario;

import java.util.Set;

public record UsuarioDTO(Long id, String nombre, String correoElectronico, Set<Long> perfilesIds) {

    public UsuarioDTO(Usuario usuario) {
        this(usuario.getId(), usuario.getNombre(), usuario.getCorreoElectronico(),
                (Set<Long>) usuario.getPerfiles().stream().map(Perfil::getId).toList());
    }
}
