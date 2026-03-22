package backend.javabean.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import backend.javabean.models.Reserva;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {

  List<Reserva> findByUsuarioId(Long usuarioId);

}
