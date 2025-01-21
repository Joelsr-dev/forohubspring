package com.alurachallenge.forohub.repository;

import com.alurachallenge.forohub.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Puedes agregar métodos personalizados si es necesario
    // Ejemplo: Buscar usuario por correo electrónico
    Usuario findByCorreoElectronico(String correoElectronico);
}
