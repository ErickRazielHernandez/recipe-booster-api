package org.rzlindustries.mx.core.entities;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class InstruccionSubproducto {
    private Integer idInstruccion;
    private Integer idSubproducto;
}
