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

  public Evento createEvento(Evento evento) {
    return eventoRepository.save(evento);
  }

  public List<EventoDTO> getEventosActivos() {
    return eventoRepository.findByEstado("ACTIVO").stream()
        .map(EventoMapper::toDTO)
        .toList();
  }

  public List<EventoDTO> getEventosDestacados() {
    return eventoRepository.findByDestacadoTrue().stream()
        .map(EventoMapper::toDTO)
        .toList();
  }

  public EventoDTO createEvento(EventoDTO eventoDTO) {
    Evento evento = EventoMapper.toEntity(eventoDTO);
    Evento eventoGuardado = eventoRepository.save(evento);
    return EventoMapper.toDTODetalle(eventoGuardado);
  }

  public EventoDTO actualizarEvento(Long id, EventoDTO eventoDTO) {
    Evento eventoActual = eventoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Evento no encontrado con id: " + id));

    Evento eventoActualizado = EventoMapper.toEntity(eventoDTO);

    eventoActual.setTitulo(eventoActualizado.getTitulo());
    eventoActual.setDescripcion(eventoActualizado.getDescripcion());
    eventoActual.setFechaEvento(eventoActualizado.getFechaEvento());
    eventoActual.setHoraEvento(eventoActualizado.getHoraEvento());
    eventoActual.setLugar(eventoActualizado.getLugar());
    eventoActual.setAforoMaximo(eventoActualizado.getAforoMaximo());
    eventoActual.setEntradasVendidas(eventoActualizado.getEntradasVendidas());
    eventoActual.setPrecio(eventoActualizado.getPrecio());
    eventoActual.setImagenUrl(eventoActualizado.getImagenUrl());
    eventoActual.setDestacado(eventoActualizado.getDestacado());
    eventoActual.setEstado(eventoActualizado.getEstado());
    eventoActual.setTipoId(eventoActualizado.getTipoId());

    Evento eventoGuardado = eventoRepository.save(eventoActual);

    return EventoMapper.toDTODetalle(eventoGuardado);
  }

}