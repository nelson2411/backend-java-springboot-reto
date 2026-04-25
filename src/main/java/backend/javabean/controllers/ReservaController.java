package backend.javabean.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.javabean.dto.ReservaDTO;
import backend.javabean.dto.ReservaResponseDTO;
import backend.javabean.services.ReservaService;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/reservas")
@Tag(name = "Reservas", description = "Operaciones relacionadas con las reservas")
public class ReservaController {

  private final ReservaService reservaService;

  public ReservaController(ReservaService reservaService) {
    this.reservaService = reservaService;
  }

  @GetMapping
  public List<ReservaResponseDTO> getAllReservas() {
    return reservaService.getAllReservas();
  }

  @GetMapping("/usuario/{usuarioId}")
  public List<ReservaResponseDTO> getReservasByUsuarioId(@PathVariable Long usuarioId) {
    return reservaService.getReservasByUsuarioId(usuarioId);
  }

  @PostMapping
  public ReservaResponseDTO createReserva(@RequestBody ReservaDTO dto) {
    return reservaService.createReserva(dto);
  }
  
  @PutMapping("/{id}/cancelar")
  public ReservaResponseDTO cancelarReserva(@PathVariable Long id) {
    return reservaService.cancelarReserva(id);
  }

}
