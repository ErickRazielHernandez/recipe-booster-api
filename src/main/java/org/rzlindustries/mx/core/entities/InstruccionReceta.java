package org.rzlindustries.mx.core.entities;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class InstruccionReceta {
    private Integer idInstruccion;
    private Integer idReceta;
}
