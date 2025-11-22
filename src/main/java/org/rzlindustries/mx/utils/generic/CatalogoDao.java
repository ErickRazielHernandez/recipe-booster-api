package org.rzlindustries.mx.utils.generic;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceUnit;
import lombok.extern.slf4j.Slf4j;
import org.rzlindustries.mx.core.entities.TipoEtiqueta;
import org.rzlindustries.mx.external.jpa.model.TipoEtiquetaJpa;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@ApplicationScoped
public class CatalogoDao implements CatalogoRepository {
    @Inject
    @PersistenceUnit(name = "reading")
    EntityManager readEntityManager;

    private static final Map<Class<? extends Catalogo>, Class<? extends CatalogoJpa>> jpaClassMap = Map.ofEntries(
            Map.entry(TipoEtiqueta.class, TipoEtiquetaJpa.class)
    );
    private static final List<String> CLASS_NAMES = new ArrayList<>(jpaClassMap.values()).stream().map(Class::getName).toList();
    private static final String ERROR_MESSAGE = "No se encontró la clase JPA para el catálogo {}";
    private static final String QUERY_FIND_ALL = "select catalogo from <table> catalogo";
    private static final String QUERY_FIND_BY_ID =  "select catalogo from <table> catalogo where catalogo.id =:idCatalogo";
    private static final String PARAM_TABLE = "<table>";
    private static final String PARAM_ID_CATALOGO = "idCatalogo";

    @Override
    public <T extends Catalogo> List<T> findAll(Class<T> clazz) {
        var jpaEntityClazzOptional = getJpaEntityClazz(clazz);
        List<T> catalogos = Collections.emptyList();
        if (jpaEntityClazzOptional.isPresent()) {
            var jpaEntityClazz = jpaEntityClazzOptional.get();
            var getQuery = getQuery(QUERY_FIND_ALL, PARAM_TABLE, jpaEntityClazz);
            if (getQuery.isPresent()) {
                catalogos = readEntityManager.createQuery(getQuery.get(), jpaEntityClazz)
                        .getResultStream()
                        .map(jpa -> this.toEntity(clazz, jpa))
                        .collect(Collectors.toList());
            } else {
                throw new IllegalArgumentException(ERROR_MESSAGE);
            }
        } else {
            log.error(ERROR_MESSAGE, clazz.getName());
        }
        return catalogos;
    }

    @Override
    public <T extends Catalogo> List<T> findAll(Class<T> clazz, Boolean activo) {
        List<T> lista = findAll(clazz);
        if (activo != null) {
            lista = lista.stream().filter(c -> c.getActivo().equals(activo)).collect(Collectors.toList());
        }
        return lista;
    }

    @Override
    public <T extends Catalogo> Optional<T> findById(Class<T> clazz, Integer idCatalogo) {
        var jpaEntityClazzOptional = getJpaEntityClazz(clazz);
        Optional<T> optionalResult = Optional.empty();
        if (jpaEntityClazzOptional.isPresent()) {
            var jpaEntityClazz = jpaEntityClazzOptional.get();
            var optionalQuery = getQuery(QUERY_FIND_BY_ID, PARAM_TABLE, jpaEntityClazz);
            if (optionalQuery.isPresent()) {
                optionalResult = readEntityManager.createQuery(optionalQuery.get(), jpaEntityClazz)
                        .setParameter(PARAM_ID_CATALOGO, idCatalogo)
                        .getResultStream()
                        .map(c -> this.toEntity(clazz, c))
                        .map(clazz::cast)
                        .findFirst();
            } else {
                throw new IllegalArgumentException(ERROR_MESSAGE);
            }
        } else {
            log.error(ERROR_MESSAGE, clazz.getName());
        }
        return optionalResult;
    }

    @Override
    public boolean existsById(Class<? extends Catalogo> clazz, Integer idCatalogo) {
        var jpaEntityClazzOptional = getJpaEntityClazz(clazz);
        boolean exists = false;
        if (jpaEntityClazzOptional.isPresent()) {
            var jpaEntityClazz = jpaEntityClazzOptional.get();
            exists = readEntityManager.find(jpaEntityClazz, idCatalogo) != null;
        } else {
            log.error(ERROR_MESSAGE, clazz.getName());
        }
        return exists;
    }

    private <T extends Catalogo> T toEntity(Class<T> clazz, CatalogoJpa catalogoJpa) {
        try {
            T entity = clazz.getDeclaredConstructor().newInstance();
            entity.setId(catalogoJpa.getId());
            entity.setNombre(catalogoJpa.getNombre());
            entity.setDescripcion(catalogoJpa.getDescripcion());
            entity.setActivo(catalogoJpa.getActivo());
            return entity;
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Obtiene la clase jpa que corresponde a una clase dada
     *
     * @param clazz Valor de la clase que se va a mapear
     * @param <T1>  Clase que será mapeada
     * @return {@link Optional} con la clase obtenida
     */
    private <T1 extends Catalogo> Optional<Class<? extends CatalogoJpa>> getJpaEntityClazz(Class<T1> clazz) {
        return Optional.ofNullable(jpaClassMap.get(clazz));
    }

    /**
     * Obtiene la query que será ejecutada reemplazando el placeholder por el valor requerido
     *
     * @param query String con la sentencia de la query a ejecutar
     * @param param Parámetro que será reemplazado en la query
     * @param clazz Valor por el que se reemplazará el param en la query
     * @return {@link Optional<String>} con la query resultante
     */
    private Optional<String> getQuery(String query, String param, Class<? extends CatalogoJpa> clazz) {
        if (CLASS_NAMES.stream().anyMatch(name -> name.equals(clazz.getName()))) {
            return Optional.of(query.replace(param, clazz.getName()));
        }
        return Optional.empty();
    }
}
