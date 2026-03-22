package backend.javabean.dto;

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
  private Integer numEntradas;
  private LocalDateTime fechaReserva;
  private String estado;

}
