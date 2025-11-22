package org.rzlindustries.mx.external.jpa.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;
import org.rzlindustries.mx.core.entities.InstruccionReceta;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Embeddable
public class InstruccionRecetaJpaId implements Serializable {
    @Column(name = "fk_id_instruccion")
    private final Integer idInstruccion;
    @Column(name = "fk_id_receta")
    private final Integer idReceta;

    public static InstruccionRecetaJpaId fromEntity(InstruccionReceta instruccionReceta) {
        return InstruccionRecetaJpaId.builder()
                .idInstruccion(instruccionReceta.getIdInstruccion())
                .idReceta(instruccionReceta.getIdReceta())
                .build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InstruccionRecetaJpaId that = (InstruccionRecetaJpaId) o;
        return Objects.equals(idInstruccion, that.idInstruccion) && Objects.equals(idReceta, that.idReceta);
    }

    @Override
    public int hashCode() {return Objects.hash(idInstruccion, idReceta);}
}

