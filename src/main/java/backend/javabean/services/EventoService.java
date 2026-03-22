package backend.javabean.services;

import java.util.List;

import org.springframework.stereotype.Service;

import backend.javabean.dto.EventoDTO;
import backend.javabean.mappers.EventoMapper;
import backend.javabean.models.Evento;
import backend.javabean.repositories.EventoRepository;

@Service
public class EventoService {

  private final EventoRepository eventoRepository;

  public EventoService(EventoRepository eventoRepository) {
    this.eventoRepository = eventoRepository;
  }

  public List<EventoDTO> getAllEventos() {
    return eventoRepository.findAll().stream()
        .map(EventoMapper::toDTO)
        .toList();
  }

  public Evento getEventoById(Long id) {
    return eventoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Evento no encontrado con id: " + id));
  }

  /*
   * Method for create a new event
   */
  public Evento createEvento(Evento evento) {
    return eventoRepository.save(evento);
  }

  /*
   * Method for getting estados activos
   */

  public List<EventoDTO> getEventosActivos() {
    return eventoRepository.findByEstado("activo").stream()
        .map(EventoMapper::toDTO)
        .toList();
  }

  // find by "Destacado" field if it's true
  public List<EventoDTO> getEventosDestacados() {
    return eventoRepository.findByDestacadoTrue().stream()
        .map(EventoMapper::toDTO)
        .toList();
  }

  public EventoDTO createEvento(EventoDTO eventoDTO) {
    Evento evento = EventoMapper.toEntity(eventoDTO);
    Evento savedEvento = eventoRepository.save(evento);
    return EventoMapper.toDTO(savedEvento);
  }

}
