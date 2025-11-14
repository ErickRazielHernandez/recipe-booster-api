package org.rzlindustries.mx.core.business.input;

import io.vavr.control.Either;
import org.rzlindustries.mx.core.entities.Tag;
import org.rzlindustries.mx.util.error.ErrorCode;

import java.util.List;

public interface TagService {
    /**
     * Obtiene las etiquetas que coincidan con los filtros de búsqueda
     *
     * @param idTipo Identificador del tipo de etiqueta
     * @return Lista de entidades de tipo {@link Tag}
     */
    List<Tag> listByIdTipo(Integer idTipo);

    /**
     * Persiste en la base de datos una entidad
     *
     * @param tag Entidad {@link Tag} con la infromación a persistir
     * @return {@link Either} con un true si se completó la acción, o con un {@link ErrorCode} en caso contrario
     */
    Either<ErrorCode, Boolean> create(Tag tag);
}
