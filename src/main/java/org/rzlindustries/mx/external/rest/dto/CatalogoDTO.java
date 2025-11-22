package org.rzlindustries.mx.external.rest.dto;

import lombok.Builder;
import lombok.Getter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.rzlindustries.mx.utils.generic.Catalogo;

@Getter
@Builder
@Schema(name = "Catalogo", description = "Clase DTO genérica para visualizar la infromación de un catálogo.")
public class CatalogoDTO {
    @Schema(readOnly = true, description = "Identificador del registro")
    private Integer id;
    @Schema(readOnly = true, description = "Nombre del registro")
    private String nombre;

    public static CatalogoDTO fromEntity(Catalogo catalogo) {
        return CatalogoDTO.builder()
                .id(catalogo.getId())
                .nombre(catalogo.getNombre())
                .build();
    }
}
