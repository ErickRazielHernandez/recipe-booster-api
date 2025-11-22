package org.rzlindustries.mx.core.business.output;

import org.rzlindustries.mx.core.entities.Subproducto;

public interface SubproductoRepository {
    /**
     * Persiste una lista de entidades en la base de datos
     *
     * @param subproducto Lista de entidades de tiepo {@link Subproducto} a persistir
     * @return Entidad de tipo {@link Subproducto}
     */
    Subproducto save(Subproducto subproducto);
}
