package org.rzlindustries.mx.utils.generic;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class Catalogo {
    private Integer id;
    private String nombre;
    private String descripcion;
    private Boolean activo;
}
