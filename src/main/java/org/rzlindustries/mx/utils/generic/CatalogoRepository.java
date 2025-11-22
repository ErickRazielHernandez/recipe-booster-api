package org.rzlindustries.mx.utils.generic;

import java.util.List;
import java.util.Optional;

public interface CatalogoRepository {
    /**
     * Obtiene los elementos de un catalogo
     *
     * @param clazz Clase del catalogo a buscar
     * @param <T>   Tipo de catalogo que es una subclase de Catalogo
     * @return Lista con los elementos del catalogo
     */
    <T extends Catalogo> List<T> findAll(Class<T> clazz);

    /**
     * Obtiene la lista de los elementos de un catalo que cumplan con el filtro
     *
     * @param clazz  Tipo del catalogo a buscar que es subclase de Catalogo
     * @param activo Indica si se quiere obtener solo los elementos activos o inactivos o sin filtrar
     * @param <T>    Tipo de catalogo que es una subclase de Catalogo
     * @return Lista con los elementos del catalogo
     */
    <T extends Catalogo> List<T> findAll(Class<T> clazz, Boolean activo);

    /**
     * Obtiene un elemento de un catalogo por su id
     *
     * @param clazz      Clase del catalogo a buscar
     * @param idCatalogo Identificador del catalogo
     * @param <T>        Subclase de catalogo
     * @return Optional vacio o con el elemnto encontrado
     */
    <T extends Catalogo> Optional<T> findById(Class<T> clazz, Integer idCatalogo);

    /**
     * Determina si cierto elemento con id existe en el catalogo
     *
     * @param clazz      Generico del catalogo a buscar
     * @param idCatalogo Identificador del elemento a buscar
     * @return Verdadero si existe, falso en caso contrario
     */
    boolean existsById(Class<? extends Catalogo> clazz, Integer idCatalogo);
}
