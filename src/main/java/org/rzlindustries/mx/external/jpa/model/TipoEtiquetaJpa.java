package org.rzlindustries.mx.external.jpa.model;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;
import org.rzlindustries.mx.utils.generic.CatalogoJpa;

import java.util.Objects;

@Entity
@NoArgsConstructor
@AttributeOverride(name = "id", column = @Column(name = "id_tipo_etiqueta"))
@Table(name = "crc02_tipo_etiqueta")
public class TipoEtiquetaJpa extends CatalogoJpa {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TipoEtiquetaJpa that = (TipoEtiquetaJpa) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
