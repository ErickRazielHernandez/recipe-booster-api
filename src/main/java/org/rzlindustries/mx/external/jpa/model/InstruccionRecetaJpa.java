package org.rzlindustries.mx.external.jpa.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import org.rzlindustries.mx.core.entities.InstruccionReceta;

import java.util.Objects;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "trc10_instruccion_receta")
public class InstruccionRecetaJpa {
    @EmbeddedId
    private InstruccionRecetaJpaId id;

    public static InstruccionRecetaJpa fromEntity(InstruccionReceta instruccionReceta) {
        return InstruccionRecetaJpa.builder()
                .id(InstruccionRecetaJpaId.fromEntity(instruccionReceta))
                .build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InstruccionRecetaJpa that)) return false;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {return Objects.hash(id);}
}
