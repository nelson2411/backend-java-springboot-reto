package backend.javabean.models;

import java.time.LocalDateTime;

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
@Table(name = "reservas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reserva {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "usuario_id")
  private Long usuarioId;

  @Column(name = "evento_id")
  private Long eventoId;

  @Column(name = "num_entradas")
  private Integer numEntradas;

  @Column(name = "fecha_reserva")
  private LocalDateTime fechaReserva;

  private String estado; // "CONFIRMADA", "CANCELADA", etc.

}
