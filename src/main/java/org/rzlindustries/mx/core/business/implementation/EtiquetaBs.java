package org.rzlindustries.mx.core.business.implementation;

import io.vavr.control.Either;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.rzlindustries.mx.core.business.input.EtiquetaService;
import org.rzlindustries.mx.core.business.output.EtiquetaRepository;
import org.rzlindustries.mx.core.entities.Etiqueta;
import org.rzlindustries.mx.utils.error.ErrorCode;
import org.rzlindustries.mx.utils.error.ErrorCodeEnum;

import java.util.List;

@ApplicationScoped
public class EtiquetaBs implements EtiquetaService {
    private final EtiquetaRepository etiquetaRepository;

    @Inject
    public EtiquetaBs(EtiquetaRepository etiquetaRepository) {
        this.etiquetaRepository = etiquetaRepository;
    }

    @Override
    public List<Etiqueta> listByIdTipoAndNombre(Integer idTipo, String nombre) {
        return etiquetaRepository.findByIdTipoAndNombre(idTipo, nombre);
    }

    @Override
    public Either<ErrorCode, Boolean> create(Etiqueta etiqueta) {
        if (etiquetaRepository.existsByNombre(etiqueta.getNombre())){
            return Either.left(ErrorCodeEnum.REB_RNN001);
        }
        etiquetaRepository.save(etiqueta);
        return Either.right(true);
    }

    @Override
    public Either<ErrorCode, Boolean> delete(Integer idEtiqueta) {
        if (!etiquetaRepository.existsById(idEtiqueta)){
            return Either.left(ErrorCodeEnum.REB_NOT_FOUND);
        }
        etiquetaRepository.deleteById(idEtiqueta);
        return Either.right(true);
    }
}
