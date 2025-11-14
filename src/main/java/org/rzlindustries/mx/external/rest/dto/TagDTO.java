package org.rzlindustries.mx.external.rest.dto;

import lombok.Builder;
import lombok.Getter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.rzlindustries.mx.core.entities.Tag;

@Getter
@Builder
@Schema(name = "Tag", description = "Clase DTO para visualizar una etiqueta.")
public class TagDTO {
    @Schema(readOnly = true, description = "Identificador de la etiqueta")
    private Integer id;
    @Schema(readOnly = true, description = "Nombre de la etiqueta")
    private String nombre;

    public static TagDTO fromEntity(Tag tag) {
        return TagDTO.builder()
                .id(tag.getId())
                .nombre(tag.getNombre())
                .build();
    }
}
