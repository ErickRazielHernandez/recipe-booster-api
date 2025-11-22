package org.rzlindustries.mx.core.business.input;

import org.rzlindustries.mx.core.entities.TipoEtiqueta;
import org.rzlindustries.mx.utils.generic.Catalogo;

import java.util.List;

public interface CatalogoService {
    /**
     * Obtiene el catálogo de tipos de etiquetas
     *
     * @param activo Filtro por estado
     * @return Lista de entidades de tipo {@link Catalogo}
     */
    List<TipoEtiqueta> listAllTipoEtiqueta(Boolean activo);
}
