package org.rzlindustries.mx.external.jpa.model;

import jakarta.persistence.*;
import lombok.*;
import org.rzlindustries.mx.core.entities.Instruccion;

import java.util.Objects;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "trc04_instruccion")
public class InstruccionJpa {
    @Id
    @Column(name = "id_instruccion")
    @SequenceGenerator(name = "trc04_instruccion_id_instruccion_seq", sequenceName = "trc04_instruccion_id_instruccion_seq", allocationSize = 1)
    @GeneratedValue(generator = "trc04_instruccion_id_instruccion_seq", strategy = GenerationType.SEQUENCE)
    private Integer id;
    @Column(name = "tx_instruccion")
    private String instruccion;
    @Column(name = "nu_orden")
    private Integer orden;

    public static InstruccionJpa fromEntity(Instruccion instruccion) {
        return InstruccionJpa.builder()
                .id(instruccion.getId())
                .instruccion(instruccion.getInstruccion())
                .orden(instruccion.getOrden())
                .build();
    }

    public Instruccion toEntity() {
        return Instruccion.builder()
                .id(id)
                .instruccion(instruccion)
                .orden(orden)
                .build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InstruccionJpa that = (InstruccionJpa) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
