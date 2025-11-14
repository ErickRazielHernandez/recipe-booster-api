package org.rzlindustries.mx.external.rest.dto;

import lombok.Builder;
import lombok.Getter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.rzlindustries.mx.core.entities.Tag;

@Getter
@Builder
@Schema(name = "TagCreateDTO", description = "Clase DTO utilizada para la creación de una etiqueta.")
public class TagCreateDTO {
    private Integer idTipo;
    private String nombre;

    public Tag toEntity(){
        return Tag.builder()
                .idTipo(idTipo)
                .nombre(nombre)
                .build();
    }
}
