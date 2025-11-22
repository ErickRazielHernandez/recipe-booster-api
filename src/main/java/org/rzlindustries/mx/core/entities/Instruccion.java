package org.rzlindustries.mx.core.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Instruccion {
    private Integer id;
    private String instruccion;
    private Integer orden;

    //Extras
    private Integer idReceta;
    private Integer idSubprducto;
}
