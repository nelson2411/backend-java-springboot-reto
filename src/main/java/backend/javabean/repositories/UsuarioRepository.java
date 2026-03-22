package backend.javabean.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import backend.javabean.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

  Optional<Usuario> findByEmail(String email);

}
