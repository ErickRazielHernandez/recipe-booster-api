package org.rzlindustries.mx.core.entities;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Tag {
    private Integer id;
    private Integer idTipo;
    private String nombre;
}
