package backend.javabean.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservaResponseDTO {

  private Long id;
  private Long usuarioId;
  private Long eventoId;

  private String evento;
  private String fecha;
  private String hora;
  private String lugar;

  private Integer entradas;
  private BigDecimal precio;
  private String estado;
  private String imagen;

  private LocalDateTime fechaReserva;

}