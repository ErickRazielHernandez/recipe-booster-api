package org.rzlindustries.mx.core.business.input;

import io.vavr.Value;
import io.vavr.control.Either;
import org.rzlindustries.mx.core.entities.Etiqueta;
import org.rzlindustries.mx.utils.error.ErrorCode;

import java.util.List;

public interface EtiquetaService {
    /**
     * Obtiene las etiquetas que coincidan con los filtros de búsqueda
     *
     * @param idTipo Identificador del tipo de etiqueta
     * @param nombre Filtro de búsqueda por nombre
     * @return Lista de entidades de tipo {@link Etiqueta}
     */
    List<Etiqueta> listByIdTipoAndNombre(Integer idTipo, String nombre);

    /**
     * Persiste en la base de datos una entidad
     *
     * @param etiqueta Entidad {@link Etiqueta} con la infromación a persistir
     * @return {@link Either} con un true si se completó la acción, o con un {@link ErrorCode} en caso contrario
     */
    Either<ErrorCode, Boolean> create(Etiqueta etiqueta);

    /**
     * Elimina un registro de la base de datos por su identificador
     *
     * @param idEtiqueta Identificador de la etiqueta
     * @return {@link Either} con un true si se completó la acción, o con un {@link ErrorCode} en caso contrario
     */
    Either<ErrorCode, Boolean> delete(Integer idEtiqueta);
}
