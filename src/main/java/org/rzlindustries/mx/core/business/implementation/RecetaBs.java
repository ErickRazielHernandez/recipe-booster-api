package org.rzlindustries.mx.core.business.implementation;

import io.vavr.control.Either;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.rzlindustries.mx.core.business.input.RecetaService;
import org.rzlindustries.mx.core.business.output.InstruccionRepository;
import org.rzlindustries.mx.core.business.output.RecetaRepository;
import org.rzlindustries.mx.core.business.output.SubproductoRepository;
import org.rzlindustries.mx.core.entities.Instruccion;
import org.rzlindustries.mx.core.entities.InstruccionReceta;
import org.rzlindustries.mx.core.entities.InstruccionSubproducto;
import org.rzlindustries.mx.core.entities.Receta;
import org.rzlindustries.mx.utils.BsConstants;
import org.rzlindustries.mx.utils.error.ErrorCode;
import org.rzlindustries.mx.utils.error.ErrorCodeEnum;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class RecetaBs implements RecetaService {
    private final RecetaRepository recetaRepository;
    private final SubproductoRepository subproductoRepository;
    private final InstruccionRepository instruccionRepository;

    @Inject
    public RecetaBs(RecetaRepository recetaRepository,
                    SubproductoRepository subproductoRepository,
                    InstruccionRepository instruccionRepository) {
        this.recetaRepository = recetaRepository;
        this.subproductoRepository = subproductoRepository;
        this.instruccionRepository = instruccionRepository;
    }

    @Override
    public List<Receta> listAll() {
        return recetaRepository.findAll();
    }

    @Override
    public Either<ErrorCode, Receta> getById(Integer idReceta) {
        return recetaRepository.findByIdWithSubproductosAndInstrucciones(idReceta)
                .<Either<ErrorCode, Receta>>map(Either::right).orElseGet(() -> Either.left(ErrorCodeEnum.REB_NOT_FOUND));
    }

    @Override
    @Transactional
    public Either<ErrorCode, Boolean> create(Receta receta) {
        var subproductos = receta.getSubproductos();
        if (recetaRepository.existsByNombre(receta.getNombre()) ||
                subproductos.stream().anyMatch(subproducto -> subproductos.stream()
                        .filter(subproducto1 -> subproducto1.getNombre().equals(subproducto.getNombre())).toList().size() > 1)) {
            return Either.left(ErrorCodeEnum.REB_RNN001);
        }
        receta.setCreacion(LocalDateTime.now(BsConstants.DEFAULT_ZONE_ID));
        receta.setActualizacion(LocalDateTime.now(BsConstants.DEFAULT_ZONE_ID));
        receta.setVecesRealizada(0);
        var recetaSaved = recetaRepository.save(receta);
        instruccionRepository.saveAllInstruccionReceta(instruccionRepository.saveAll(receta.getInstrucciones())
                .stream().map(instruccion -> InstruccionReceta.builder()
                        .idInstruccion(instruccion.getId())
                        .idReceta(recetaSaved.getId())
                        .build()).toList());
        List<InstruccionSubproducto> instruccionSubproductos = new ArrayList<>();
        subproductos.forEach(subproducto -> {
            subproducto.setIdReceta(recetaSaved.getId());
            var subproductoSaved = subproductoRepository.save(subproducto);
            instruccionSubproductos.addAll(instruccionRepository.saveAll(subproducto.getInstrucciones())
                    .stream().map(instruccion -> InstruccionSubproducto.builder()
                            .idInstruccion(instruccion.getId())
                            .idSubproducto(subproductoSaved.getId())
                            .build()).toList());
        });
        instruccionRepository.saveAllInstruccionSubproducto(instruccionSubproductos);
        return Either.right(true);
    }
}
