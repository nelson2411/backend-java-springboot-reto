package backend.javabean.mappers;

import backend.javabean.dto.UsuarioDTO;
import backend.javabean.models.Usuario;

public class UsuarioMapper {

  public static UsuarioDTO toDTO(Usuario usuario) {
    return UsuarioDTO.builder()
        .id(usuario.getId())
        .nombre(usuario.getNombre())
        .apellidos(usuario.getApellidos())
        .email(usuario.getEmail())
        .telefono(usuario.getTelefono())
        .build();
  }

  public static Usuario toEntity(UsuarioDTO dto) {
    return Usuario.builder()
        .nombre(dto.getNombre())
        .apellidos(dto.getApellidos())
        .email(dto.getEmail())
        .telefono(dto.getTelefono())
        .activo(true)
        .build();

  }

}
