package org.rzlindustries.mx.external.rest.dto;

import org.rzlindustries.mx.core.entities.Tag;

@Getter
@Builder
@Schema(name = "TagCreateDTO", description = "Clase DTO utilizada para la creación de una etiqueta.")
public class TagCreateDTO {
    private Integer idTipo;
    private String nombre;

    public Tag toEntity(){
        return Tag.builder()
                .build();
    }
}
