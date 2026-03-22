package backend.javabean.mappers;

import backend.javabean.dto.ReservaResponseDTO;
import backend.javabean.models.Reserva;

public interface ReservaMapper {

  public static ReservaResponseDTO toDTO(Reserva reserva) {
    return ReservaResponseDTO.builder()
        .id(reserva.getId())
        .usuarioId(reserva.getUsuarioId())
        .eventoId(reserva.getEventoId())
        .numEntradas(reserva.getNumEntradas())
        .fechaReserva(reserva.getFechaReserva())
        .estado(reserva.getEstado())
        .build();
  }

}
