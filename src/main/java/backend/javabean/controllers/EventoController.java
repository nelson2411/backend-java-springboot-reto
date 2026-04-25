package backend.javabean.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.javabean.dto.EventoDTO;
import backend.javabean.mappers.EventoMapper;
import backend.javabean.services.EventoService;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/eventos")
@Tag(name = "Eventos", description = "Operaciones relacionadas con los eventos")
public class EventoController {

  private final EventoService eventoService;

  public EventoController(EventoService eventoService) {
    this.eventoService = eventoService;
  }

  @GetMapping
  public List<EventoDTO> getAllEventos() {
    return eventoService.getAllEventos();
  }

  @GetMapping("/{id}")
  public EventoDTO getEventoById(@PathVariable Long id) {
    return EventoMapper.toDTODetalle(eventoService.getEventoById(id));
  }

  @GetMapping("/activos")
  public List<EventoDTO> getEventosActivos() {
    return eventoService.getEventosActivos();
  }

  @GetMapping("/destacados")
  public List<EventoDTO> getEventosDestacados() {
    return eventoService.getEventosDestacados();
  }

  @PostMapping
  public EventoDTO createEvento(@RequestBody EventoDTO dto) {
    return eventoService.createEvento(dto);
  }

}
