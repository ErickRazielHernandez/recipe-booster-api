package org.rzlindustries.mx.external.jpa.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import org.rzlindustries.mx.core.entities.InstruccionSubproducto;

import java.util.Objects;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "trc11_instruccion_subproducto")
public class InstruccionSubproductoJpa {
    @EmbeddedId
    private InstruccionSubproductoJpaId id;

    public static InstruccionSubproductoJpa fromEntity(InstruccionSubproducto instruccionSubproducto) {
        return InstruccionSubproductoJpa.builder()
                .id(InstruccionSubproductoJpaId.fromEntity(instruccionSubproducto))
                .build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InstruccionSubproductoJpa that)) return false;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {return Objects.hash(id);}
}
