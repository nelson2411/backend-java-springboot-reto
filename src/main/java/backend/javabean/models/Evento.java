package backend.javabean.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "eventos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Evento {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)

  private Long id;
  private String titulo;
  private String descripcion;

  @Column(name = "fecha_evento")
  private LocalDate fechaEvento;

  @Column(name = "hora_evento")
  private LocalTime horaEvento;

  private String lugar;

  @Column(name = "aforo_maximo")
  private Integer aforoMaximo;

  @Column(name = "entradas_vendidas")
  private Integer entradasVendidas;

  private BigDecimal precio;

  @Column(name = "imagen_url")
  private String imagenUrl;

  private Boolean destacado;

  private String estado;

  @Column(name = "tipo_id")
  private Long tipoId;

}
