package backend.javabean.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventoDTO {

  private Long id;
  private String titulo;
  private String descripcion;
  private LocalDate fechaEvento;
  private BigDecimal precio;

}
