package org.generation.ecommerce.repository;

import java.util.Optional;

import org.generation.ecommerce.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
//TODO meotod para buscar repetidos 
	Optional<Usuario> findByEmail(String email);
}
