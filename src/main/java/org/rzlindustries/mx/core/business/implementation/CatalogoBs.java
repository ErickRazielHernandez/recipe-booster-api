package org.rzlindustries.mx.core.business.implementation;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.rzlindustries.mx.core.business.input.CatalogoService;
import org.rzlindustries.mx.utils.generic.CatalogoRepository;
import org.rzlindustries.mx.core.entities.TipoEtiqueta;

import java.util.List;

@Slf4j
@ApplicationScoped
public class CatalogoBs implements CatalogoService {
    private final CatalogoRepository catalogoRepository;

    @Inject
    public CatalogoBs(CatalogoRepository catalogoRepository) {
        this.catalogoRepository = catalogoRepository;
    }

    @Override
    public List<TipoEtiqueta> listAllTipoEtiqueta(Boolean activo) {
        return catalogoRepository.findAll(TipoEtiqueta.class, activo);
    }
}
