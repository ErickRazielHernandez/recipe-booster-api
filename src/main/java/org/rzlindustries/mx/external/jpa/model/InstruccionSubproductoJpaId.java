package org.rzlindustries.mx.external.jpa.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;
import org.rzlindustries.mx.core.entities.InstruccionSubproducto;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Embeddable
public class InstruccionSubproductoJpaId implements Serializable {
    @Column(name = "fk_id_instruccion")
    private final Integer idInstruccion;
    @Column(name = "fk_id_subproducto")
    private final Integer idSubproducto;

    public static InstruccionSubproductoJpaId fromEntity(InstruccionSubproducto instruccionSubproducto) {
        return InstruccionSubproductoJpaId.builder()
                .idInstruccion(instruccionSubproducto.getIdInstruccion())
                .idSubproducto(instruccionSubproducto.getIdSubproducto())
                .build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InstruccionSubproductoJpaId that = (InstruccionSubproductoJpaId) o;
        return Objects.equals(idInstruccion, that.idInstruccion) && Objects.equals(idSubproducto, that.idSubproducto);
    }

    @Override
    public int hashCode() {return Objects.hash(idInstruccion, idSubproducto);}
}
