package backend.javabean.models;

import java.time.LocalDateTime;

import backend.javabean.enums.RolUsuario;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String nombre;
  private String apellidos;
  private String email;
  private String password;
  private String telefono;
  private Boolean activo;
  @Enumerated(EnumType.STRING)
  private RolUsuario rol;

  @Column(name = "fecha_registro")
  private LocalDateTime fechaRegistro;

}
