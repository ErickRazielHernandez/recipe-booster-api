package org.rzlindustries.mx.core.business.implementation;

import io.vavr.control.Either;
import jakarta.enterprise.context.ApplicationScoped;
import org.rzlindustries.mx.core.business.input.TagService;
import org.rzlindustries.mx.core.entities.Tag;
import org.rzlindustries.mx.util.error.ErrorCode;
import org.rzlindustries.mx.util.error.ErrorCodeEnum;

import java.util.List;

@ApplicationScoped
public class TagBs implements TagService {
    @Override
    public List<Tag> listByIdTipo(Integer idTipo) {
        return List.of(Tag.builder().id(1).nombre("Etiqueta 1").build(),
                Tag.builder().id(2).nombre("Etiqueta 2").build());
    }

    @Override
    public Either<ErrorCode, Boolean> create(Tag tag) {
        return Either.right(true);
    }
}
