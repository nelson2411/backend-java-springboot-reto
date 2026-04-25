package backend.javabean.dto;

import lombok.Data;

@Data
public class RegistroUsuarioDto {
    private String nombre;
    private String apellidos;
    private String email;
    private String password;
    private String telefono;
}
