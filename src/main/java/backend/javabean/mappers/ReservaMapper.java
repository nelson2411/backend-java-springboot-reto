package backend.javabean.mappers;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

import backend.javabean.dto.ReservaResponseDTO;
import backend.javabean.models.Evento;
import backend.javabean.models.Reserva;

public class ReservaMapper {

  public static ReservaResponseDTO toDTO(Reserva reserva, Evento evento) {

    DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("d MMM yyyy", new Locale("es", "ES"));
    DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm");

    return ReservaResponseDTO.builder()
        .id(reserva.getId())
        .usuarioId(reserva.getUsuarioId())
        .eventoId(reserva.getEventoId())
        .evento(evento.getTitulo())
        .fecha(evento.getFechaEvento() != null ? evento.getFechaEvento().format(formatoFecha) : null)
        .hora(evento.getHoraEvento() != null ? evento.getHoraEvento().format(formatoHora) : null)
        .lugar(evento.getLugar())
        .entradas(reserva.getNumEntradas())
        .precio(evento.getPrecio())
        .estado(reserva.getEstado())
        .imagen(evento.getImagenUrl())
        .fechaReserva(reserva.getFechaReserva())
        .build();
  }

}