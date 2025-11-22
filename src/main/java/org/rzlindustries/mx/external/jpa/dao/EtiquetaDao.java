package org.rzlindustries.mx.external.jpa.dao;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceUnit;
import org.hibernate.query.TypedParameterValue;
import org.hibernate.type.StandardBasicTypes;
import org.rzlindustries.mx.core.business.output.EtiquetaRepository;
import org.rzlindustries.mx.core.entities.Etiqueta;
import org.rzlindustries.mx.external.jpa.model.EtiquetaJpa;
import org.rzlindustries.mx.external.jpa.repository.EtiquetaJpaRepository;

import java.util.List;
import java.util.stream.Stream;

@ApplicationScoped
public class EtiquetaDao implements EtiquetaRepository {
    @PersistenceUnit(name = "reading")
    EntityManager readEntityManager;
    private final EtiquetaJpaRepository etiquetaJpaRepository;

    @Inject
    public EtiquetaDao(EntityManager readEntityManager,
                       EtiquetaJpaRepository etiquetaJpaRepository) {
        this.readEntityManager = readEntityManager;
        this.etiquetaJpaRepository = etiquetaJpaRepository;
    }

    private static final String QUERY_FIND_BY_ID_TIPO_AND_NOMBRE = """
        select trc03.* from trc03_etiqueta trc03
        where (:idTipo is null or trc03.fk_id_tipo = :idTipo)
        and (:nombre is null or trc03.tx_nombre ilike concat('%', :nombre, '%'))
    """;
    private static final String QUERY_EXISTS_BY_NOMBRE = """
        select exists(select 1 from trc03_etiqueta trc03 where trc03.tx_nombre = :nombre)
    """;
    private static final String QUERY_EXISTS_BY_ID = """
        select exists(select 1 from trc03_etiqueta trc03 where trc03.id_etiqueta = :idEtiqueta)
    """;

    private static final String PARAM_ID_ETIQUETA = "idEtiqueta";
    private static final String PARAM_ID_TIPO = "idTipo";
    private static final String PARAM_NOMBRE = "nombre";

    @Override
    @SuppressWarnings("unchecked")
    public List<Etiqueta> findByIdTipoAndNombre(Integer idTipo, String nombre) {
        Stream<EtiquetaJpa> result = readEntityManager.createNativeQuery(QUERY_FIND_BY_ID_TIPO_AND_NOMBRE, EtiquetaJpa.class)
                .setParameter(PARAM_ID_TIPO, new TypedParameterValue<>(StandardBasicTypes.INTEGER, idTipo))
                .setParameter(PARAM_NOMBRE, new TypedParameterValue<>(StandardBasicTypes.STRING, nombre))
                .getResultStream();
        return result.map(EtiquetaJpa::toEntity).toList();
    }

    @Override
    public boolean existsByNombre(String nombre) {
        return (boolean) readEntityManager.createNativeQuery(QUERY_EXISTS_BY_NOMBRE)
                .setParameter(PARAM_NOMBRE, nombre)
                .getSingleResult();
    }

    @Override
    public void save(Etiqueta etiqueta) {
        etiquetaJpaRepository.saveAndFlush(EtiquetaJpa.fromEntity(etiqueta));
    }

    @Override
    public boolean existsById(Integer id) {
        return (boolean) readEntityManager.createNativeQuery(QUERY_EXISTS_BY_ID)
                .setParameter(PARAM_ID_ETIQUETA, id)
                .getSingleResult();
    }

    @Override
    public void deleteById(Integer id) {
        etiquetaJpaRepository.deleteById(id);
    }
}
