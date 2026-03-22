package backend.javabean.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioDTO {

  private Long id;
  private String nombre;
  private String apellidos;
  private String email;
  private String telefono;

}
