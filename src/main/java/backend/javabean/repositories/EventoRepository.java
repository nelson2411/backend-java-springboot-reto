package backend.javabean.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import backend.javabean.models.Evento;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {

  List<Evento> findByEstado(String estado);

  // Find by "Destacado" field if it's true
  List<Evento> findByDestacadoTrue();

}
