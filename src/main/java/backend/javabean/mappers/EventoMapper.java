package backend.javabean.mappers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import backend.javabean.dto.EventoDTO;
import backend.javabean.models.Evento;

public class EventoMapper {

  public static EventoDTO toDTO(Evento evento) {
    return toDTOListado(evento);
  }

  public static EventoDTO toDTOListado(Evento evento) {
    DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("d MMM yyyy", new Locale("es", "ES"));
    DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm");

    Integer lugaresDisponibles = 0;

    if (evento.getAforoMaximo() != null && evento.getEntradasVendidas() != null) {
      lugaresDisponibles = evento.getAforoMaximo() - evento.getEntradasVendidas();
    }

    return EventoDTO.builder()
        .id(evento.getId())
        .titulo(evento.getTitulo())
        .descripcion(evento.getDescripcion())
        .fecha(evento.getFechaEvento() != null ? evento.getFechaEvento().format(formatoFecha) : null)
        .hora(evento.getHoraEvento() != null ? evento.getHoraEvento().format(formatoHora) : null)
        .lugar(evento.getLugar())
        .precio(evento.getPrecio())
        .tipo(convertirTipo(evento.getTipoId()))
        .estado(evento.getEstado())
        .aforo(lugaresDisponibles)
        .entradasVendidas(evento.getEntradasVendidas())
        .imagen(evento.getImagenUrl())
        .destacado(evento.getDestacado())
        .build();
  }

  public static EventoDTO toDTODetalle(Evento evento) {
    DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("d MMM yyyy", new Locale("es", "ES"));
    DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm");

    return EventoDTO.builder()
        .id(evento.getId())
        .titulo(evento.getTitulo())
        .descripcion(evento.getDescripcion())
        .fecha(evento.getFechaEvento() != null ? evento.getFechaEvento().format(formatoFecha) : null)
        .hora(evento.getHoraEvento() != null ? evento.getHoraEvento().format(formatoHora) : null)
        .lugar(evento.getLugar())
        .precio(evento.getPrecio())
        .tipo(convertirTipo(evento.getTipoId()))
        .estado(evento.getEstado())
        .aforo(evento.getAforoMaximo())
        .entradasVendidas(evento.getEntradasVendidas())
        .imagen(evento.getImagenUrl())
        .destacado(evento.getDestacado())
        .build();
  }

  public static Evento toEntity(EventoDTO dto) {
    return Evento.builder()
        .id(dto.getId())
        .titulo(dto.getTitulo())
        .descripcion(dto.getDescripcion())
        .fechaEvento(dto.getFecha() != null ? LocalDate.parse(dto.getFecha()) : null)
        .horaEvento(dto.getHora() != null ? LocalTime.parse(dto.getHora()) : null)
        .lugar(dto.getLugar())
        .aforoMaximo(dto.getAforo())
        .entradasVendidas(dto.getEntradasVendidas())
        .precio(dto.getPrecio())
        .imagenUrl(dto.getImagen())
        .destacado(dto.getDestacado())
        .estado(dto.getEstado())
        .tipoId(convertirTipoId(dto.getTipo()))
        .build();
  }

  private static String convertirTipo(Long tipoId) {
    if (tipoId == null) {
      return "Sin tipo";
    }

    return switch (tipoId.intValue()) {
      case 1 -> "Música";
      case 2 -> "Tecnología";
      case 3 -> "Entretenimiento";
      case 4 -> "E-Sports";
      case 5 -> "Gastronomía";
      case 6 -> "Teatro";
      case 7 -> "Deportes";
      case 8 -> "Arte";
      case 9 -> "Cine";
      case 10 -> "Educación";
      default -> "Sin tipo";
    };
  }

  private static Long convertirTipoId(String tipo) {
    if (tipo == null) {
      return null;
    }

    return switch (tipo) {
      case "Música" -> 1L;
      case "Tecnología" -> 2L;
      case "Entretenimiento" -> 3L;
      case "E-Sports" -> 4L;
      case "Gastronomía" -> 5L;
      case "Teatro" -> 6L;
      case "Deportes" -> 7L;
      case "Arte" -> 8L;
      case "Cine" -> 9L;
      case "Educación" -> 10L;
      default -> null;
    };
  }
}