package org.rzlindustries.mx.external.jpa.dao;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceUnit;
import org.rzlindustries.mx.core.business.output.RecetaRepository;
import org.rzlindustries.mx.core.entities.Receta;
import org.rzlindustries.mx.external.jpa.model.RecetaJpa;
import org.rzlindustries.mx.external.jpa.repository.RecetaJpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@ApplicationScoped
public class RecetaDao implements RecetaRepository {
    @PersistenceUnit(name = "reading")
    private final EntityManager readEntityManager;
    private final RecetaJpaRepository recetaJpaRepository;

    @Inject
    public RecetaDao(EntityManager readEntityManager,
                     RecetaJpaRepository recetaJpaRepository) {
        this.readEntityManager = readEntityManager;
        this.recetaJpaRepository = recetaJpaRepository;
    }

    private static final String QUERY_FIND_BY_ID = """
        select trc01.* from trc01_receta trc01 where trc01.id_receta = :idReceta
    """;
    private static final String QUERY_EXISTS_BY_NOMBRE = """
        select exists(select 1 from trc01_receta trc01 where trc01.tx_nombre = :nombre)
    """;
    private static final String PARAM_ID_RECETA = "idReceta";
    private static final String PARAM_NOMBRE = "nombre";

    @Override
    public List<Receta> findAll() {
        return recetaJpaRepository.findAll().stream().map(RecetaJpa::toEntity).toList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public Optional<Receta> findByIdWithSubproductosAndInstrucciones(Integer id) {
        Stream<RecetaJpa> result = readEntityManager.createNativeQuery(QUERY_FIND_BY_ID, RecetaJpa.class)
                .setParameter(PARAM_ID_RECETA, id)
                .getResultStream();
        return result.findFirst().map(RecetaJpa::toEntityWithSubproductosAndInstrucciones);
    }

    @Override
    public boolean existsByNombre(String nombre) {
        return (boolean) readEntityManager.createNativeQuery(QUERY_EXISTS_BY_NOMBRE)
                .setParameter(PARAM_NOMBRE, nombre)
                .getSingleResult();
    }

    @Override
    public Receta save(Receta receta) {
        return recetaJpaRepository.save(RecetaJpa.fromEntity(receta)).toEntity();
    }
}
