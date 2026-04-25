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

  public ReservaService(
      ReservaRepository reservaRepository,
      UsuarioRepository usuarioRepository,
      EventoRepository eventoRepository
  ) {
    this.reservaRepository = reservaRepository;
    this.usuarioRepository = usuarioRepository;
    this.eventoRepository = eventoRepository;
  }

  public List<ReservaResponseDTO> getAllReservas() {
    return reservaRepository.findAll().stream()
        .map(this::crearRespuestaReserva)
        .collect(Collectors.toList());
  }

  public List<ReservaResponseDTO> getReservasByUsuarioId(Long usuarioId) {
    return reservaRepository.findByUsuarioId(usuarioId).stream()
        .map(this::crearRespuestaReserva)
        .collect(Collectors.toList());
  }

  @Transactional
  public ReservaResponseDTO createReserva(ReservaDTO dto) {

    if (dto.getNumEntradas() == null || dto.getNumEntradas() <= 0) {
      throw new BusinessException("La cantidad de entradas no es válida");
    }

    if (dto.getNumEntradas() > 10) {
      throw new BusinessException("No puedes reservar más de 10 entradas");
    }

    Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
        .orElseThrow(() -> new BusinessException("Usuario no encontrado"));

    Evento evento = eventoRepository.findById(dto.getEventoId())
        .orElseThrow(() -> new BusinessException("Evento no encontrado"));

    if (!"ACTIVO".equals(evento.getEstado())) {
      throw new BusinessException("Este evento no acepta reservas");
    }

    int aforoMaximo = evento.getAforoMaximo() != null ? evento.getAforoMaximo() : 0;
    int entradasVendidas = evento.getEntradasVendidas() != null ? evento.getEntradasVendidas() : 0;
    int disponibles = aforoMaximo - entradasVendidas;

    if (dto.getNumEntradas() > disponibles) {
      throw new BusinessException("No hay suficientes entradas disponibles");
    }

    evento.setEntradasVendidas(entradasVendidas + dto.getNumEntradas());

    if (evento.getEntradasVendidas() >= aforoMaximo) {
      evento.setEstado("AGOTADO");
    }

    eventoRepository.save(evento);

    Reserva reserva = Reserva.builder()
        .usuarioId(usuario.getId())
        .eventoId(evento.getId())
        .numEntradas(dto.getNumEntradas())
        .fechaReserva(LocalDateTime.now())
        .estado("CONFIRMADA")
        .build();

    Reserva reservaGuardada = reservaRepository.save(reserva);

    return ReservaMapper.toDTO(reservaGuardada, evento);
  }

  @Transactional
  public ReservaResponseDTO cancelarReserva(Long id) {

    Reserva reserva = reservaRepository.findById(id)
        .orElseThrow(() -> new BusinessException("Reserva no encontrada"));

    Evento evento = eventoRepository.findById(reserva.getEventoId())
        .orElseThrow(() -> new BusinessException("Evento no encontrado"));

    if ("CANCELADA".equals(reserva.getEstado())) {
      return ReservaMapper.toDTO(reserva, evento);
    }

    reserva.setEstado("CANCELADA");

    int entradasVendidas = evento.getEntradasVendidas() != null ? evento.getEntradasVendidas() : 0;
    int entradasReserva = reserva.getNumEntradas() != null ? reserva.getNumEntradas() : 0;

    evento.setEntradasVendidas(Math.max(0, entradasVendidas - entradasReserva));

    if ("AGOTADO".equals(evento.getEstado()) && evento.getEntradasVendidas() < evento.getAforoMaximo()) {
      evento.setEstado("ACTIVO");
    }

    eventoRepository.save(evento);
    Reserva reservaActualizada = reservaRepository.save(reserva);

    return ReservaMapper.toDTO(reservaActualizada, evento);
  }

  private ReservaResponseDTO crearRespuestaReserva(Reserva reserva) {
    Evento evento = eventoRepository.findById(reserva.getEventoId())
        .orElseThrow(() -> new BusinessException("Evento no encontrado"));

    return ReservaMapper.toDTO(reserva, evento);
  }
}