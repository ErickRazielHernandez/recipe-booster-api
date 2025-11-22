package org.rzlindustries.mx.core.entities;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Etiqueta {
    private Integer id;
    private Integer idTipo;
    private String nombre;
    private String color;
}
