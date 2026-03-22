package backend.javabean.mappers;

/*
Import EventoDTO and Evento model 
*/
import backend.javabean.dto.EventoDTO;
import backend.javabean.models.Evento;

public class EventoMapper {

  public static EventoDTO toDTO(Evento evento) {
    return EventoDTO.builder()
        .id(evento.getId())
        .titulo(evento.getTitulo())
        .descripcion(evento.getDescripcion())
        .fechaEvento(evento.getFechaEvento())
        .precio(evento.getPrecio())
        .build();
  }

  public static Evento toEntity(EventoDTO dto) {
    return Evento.builder()
        .id(dto.getId())
        .titulo(dto.getTitulo())
        .descripcion(dto.getDescripcion())
        .fechaEvento(dto.getFechaEvento())
        .precio(dto.getPrecio())
        .build();
  }
}
