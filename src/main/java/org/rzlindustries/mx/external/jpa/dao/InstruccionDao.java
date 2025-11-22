package org.rzlindustries.mx.external.jpa.dao;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceUnit;
import org.rzlindustries.mx.core.business.output.InstruccionRepository;
import org.rzlindustries.mx.core.entities.Instruccion;
import org.rzlindustries.mx.core.entities.InstruccionReceta;
import org.rzlindustries.mx.core.entities.InstruccionSubproducto;
import org.rzlindustries.mx.external.jpa.model.InstruccionJpa;
import org.rzlindustries.mx.external.jpa.model.InstruccionRecetaJpa;
import org.rzlindustries.mx.external.jpa.model.InstruccionSubproductoJpa;
import org.rzlindustries.mx.external.jpa.repository.InstruccionJpaRepository;
import org.rzlindustries.mx.external.jpa.repository.InstruccionRecetaJpaRepository;
import org.rzlindustries.mx.external.jpa.repository.InstruccionSubproductoJpaRepository;

import java.util.List;

@ApplicationScoped
public class InstruccionDao implements InstruccionRepository {
    @PersistenceUnit(name = "reading")
    private final EntityManager readEntityManager;
    private final InstruccionJpaRepository instruccionJpaRepository;
    private final InstruccionRecetaJpaRepository instruccionRecetaJpaRepository;
    private final InstruccionSubproductoJpaRepository instruccionSubproductoJpaRepository;

    @Inject
    public InstruccionDao(EntityManager readEntityManager,
                          InstruccionJpaRepository instruccionJpaRepository,
                          InstruccionRecetaJpaRepository instruccionRecetaJpaRepository,
                          InstruccionSubproductoJpaRepository instruccionSubproductoJpaRepository) {
        this.readEntityManager = readEntityManager;
        this.instruccionJpaRepository = instruccionJpaRepository;
        this.instruccionRecetaJpaRepository = instruccionRecetaJpaRepository;
        this.instruccionSubproductoJpaRepository = instruccionSubproductoJpaRepository;
    }

    @Override
    public List<Instruccion> saveAll(List<Instruccion> instrucciones) {
        return instruccionJpaRepository.saveAll(instrucciones.stream().map(InstruccionJpa::fromEntity).toList())
                .stream().map(InstruccionJpa::toEntity).toList();
    }

    @Override
    public void saveAllInstruccionReceta(List<InstruccionReceta> instruccionesRecetas) {
        instruccionRecetaJpaRepository.saveAll(instruccionesRecetas.stream().map(InstruccionRecetaJpa::fromEntity).toList());
    }

    @Override
    public void saveAllInstruccionSubproducto(List<InstruccionSubproducto> instruccionesSubproductos) {
        instruccionSubproductoJpaRepository.saveAll(instruccionesSubproductos.stream().map(InstruccionSubproductoJpa::fromEntity).toList());
    }
}
