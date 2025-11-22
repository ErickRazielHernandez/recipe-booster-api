package org.rzlindustries.mx.core.business.input;

import io.netty.util.AsyncMapping;
import io.vavr.control.Either;
import org.rzlindustries.mx.core.entities.Receta;
import org.rzlindustries.mx.utils.error.ErrorCode;

import java.util.List;

public interface RecetaService {
    /**
     * Obtiene todas las recetas
     *
     * @return Lista de entidades de tipo {@link Receta}
     */
    List<Receta> listAll();

    /**
     * Obtiene una receta por el identificador dado
     *
     * @param idReceta Identificador del registro buscado
     * @return {@link Either} con una entidad de tipo {@link Receta} si se encontró el registro, o con un {@link ErrorCode} en caso contrario
     */
    Either<ErrorCode, Receta> getById(Integer idReceta);

    /**
     * Persiste una entidad en la base de datos
     *
     * @param receta Entidad de tipo {@link Receta} con la información a persistir
     * @return {@link Either} con un true si se completó la acción, o con un {@link ErrorCode} en caso contrario
     */
    Either<ErrorCode, Boolean> create(Receta receta);
}
