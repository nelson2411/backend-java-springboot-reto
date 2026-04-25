package backend.javabean.dto;

import java.math.BigDecimal;

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

  private String fecha;
  private String hora;
  private String lugar;

  private BigDecimal precio;
  private String tipo;
  private String estado;

  private Integer aforo;
  private Integer entradasVendidas;

  private String imagen;
  private Boolean destacado;

}