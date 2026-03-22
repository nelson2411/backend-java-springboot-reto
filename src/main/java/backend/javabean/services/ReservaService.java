package backend.javabean.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import backend.javabean.dto.ReservaDTO;
import backend.javabean.dto.ReservaResponseDTO;
import backend.javabean.exceptions.BusinessException;
import backend.javabean.mappers.ReservaMapper;
import backend.javabean.models.Evento;
import backend.javabean.models.Reserva;
import backend.javabean.models.Usuario;
import backend.javabean.repositories.EventoRepository;
import backend.javabean.repositories.ReservaRepository;
import backend.javabean.repositories.UsuarioRepository;

@Service
public class ReservaService {

  private final ReservaRepository reservaRepository;
  private final UsuarioRepository usuarioRepository;
  private final EventoRepository eventoRepository;

  public ReservaService(ReservaRepository reservaRepository, UsuarioRepository usuarioRepository,
      EventoRepository eventoRepository) {
    this.reservaRepository = reservaRepository;
    this.usuarioRepository = usuarioRepository;
    this.eventoRepository = eventoRepository;
  }

  // Get all Reservas
  public List<ReservaResponseDTO> getAllReservas() {
    return reservaRepository.findAll().stream()
        .map(ReservaMapper::toDTO)
        .collect(Collectors.toList());
  }

  // Get Reservas by Usuario ID
  public List<ReservaResponseDTO> getReservasByUsuarioId(Long usuarioId) {
    return reservaRepository.findByUsuarioId(usuarioId).stream()
        .map(ReservaMapper::toDTO)
        .collect(Collectors.toList());
  }

  @Transactional
  public ReservaResponseDTO createReserva(ReservaDTO dto) {
    Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
        .orElseThrow(() -> new BusinessException("Usuario no encontrado"));

    Evento evento = eventoRepository.findById(dto.getEventoId())
        .orElseThrow(() -> new BusinessException("Evento no encontrado"));

    // Business Logic: Verificar disponibilidad de entradas, etc.
    int disponibles = evento.getAforoMaximo() - evento.getEntradasVendidas();

    if (dto.getNumEntradas() > disponibles) {
      throw new BusinessException("No hay suficientes entradas disponibles");
    }

    // actualizar entradas vendidas
    evento.setEntradasVendidas(evento.getEntradasVendidas() + dto.getNumEntradas());
    eventoRepository.save(evento);

    Reserva reserva = Reserva.builder()
        .usuarioId(usuario.getId())
        .eventoId(evento.getId())
        .numEntradas(dto.getNumEntradas())
        .fechaReserva(LocalDateTime.now())
        .estado("CONFIRMADA")
        .build();

    return ReservaMapper.toDTO(reservaRepository.save(reserva));

  }
}
