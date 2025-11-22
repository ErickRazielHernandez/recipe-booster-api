package org.rzlindustries.mx.core.business.output;

import org.rzlindustries.mx.core.entities.Etiqueta;

import java.util.List;

public interface EtiquetaRepository {
    /**
     * Obtiene las etiquetas que coincidan con los filtros de búsqueda
     *
     * @param idTipo Identificador del tipo de etiqueta
     * @param nombre Filtro de búsqueda por nombre
     * @return Lista de entidades de tipo {@link Etiqueta}
     */
    List<Etiqueta> findByIdTipoAndNombre(Integer idTipo, String nombre);

    /**
     * Valida si existe un registro que coincida con los filtros dados
     *
     * @param nombre Filtro de búsqueda por nombre
     * @return true si existe algún registro que coincida con los filtros, false en caso contrario
     */
    boolean existsByNombre(String nombre);

    /**
     * Persiste una entidad en la base de datos
     *
     * @param etiqueta Entidad de tipo {@link Etiqueta} con la información a persistir
     */
    void save(Etiqueta etiqueta);

    /**
     * Verifica si existe un registro por su identificador
     *
     * @return true si existe el registro, false en caso contrario
     */
    boolean existsById(Integer id);

    /**
     * Elimina un registro por su identificador
     *
     * @param id Identificador del registro
     */
    void deleteById(Integer id);
}
