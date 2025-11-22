package org.rzlindustries.mx.external.jpa.dao;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.rzlindustries.mx.core.business.output.SubproductoRepository;
import org.rzlindustries.mx.core.entities.Subproducto;
import org.rzlindustries.mx.external.jpa.model.SubproductoJpa;
import org.rzlindustries.mx.external.jpa.repository.SubproductoJpaRepository;

@ApplicationScoped
public class SubproductoDao implements SubproductoRepository {
    private final SubproductoJpaRepository subproductoJpaRepository;

    @Inject
    public SubproductoDao(SubproductoJpaRepository subproductoJpaRepository) {
        this.subproductoJpaRepository = subproductoJpaRepository;
    }

    @Override
    public Subproducto save(Subproducto subproducto) {
        return subproductoJpaRepository.saveAndFlush(SubproductoJpa.fromEntity(subproducto)).toEntity();
    }
}
