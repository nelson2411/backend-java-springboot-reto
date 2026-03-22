package backend.javabean.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservaDTO {

  private Long usuarioId;
  private Long eventoId;
  private Integer numEntradas;

}
