package org.rzlindustries.mx.core.business.output;

import org.rzlindustries.mx.core.entities.Receta;

import java.util.List;
import java.util.Optional;

public interface RecetaRepository {
    /**
     * Obtiene todas las recetas
     *
     * @return Lista de entidades de tipo {@link Receta}
     */
    List<Receta> findAll();

    /**
     * Busca un registro por su identificador
     *
     * @param id Identificador del registro buscado
     * @return {@link Optional} con una entidad de tipo {@link Receta} si encontró el registro, o un {@link Optional#EMPTY}
     */
    Optional<Receta> findByIdWithSubproductosAndInstrucciones(Integer id);

    /**
     * Valida si existe alguna receta con el nombre dado
     *
     * @param nombre Nombre buscado
     * @return true si existe algún registro que coincida, false en caso contrario
     */
    boolean existsByNombre(String nombre);

    /**
     * Persiste una entidad en la base de datos
     *
     * @param receta Entidad de tipo {@link Receta} con la información a persistir
     * @return Entidad de tipo {@link Receta}
     */
    Receta save(Receta receta);
}
