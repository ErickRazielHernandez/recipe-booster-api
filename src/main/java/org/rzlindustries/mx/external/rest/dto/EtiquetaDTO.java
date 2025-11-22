package org.rzlindustries.mx.external.rest.dto;

import lombok.Builder;
import lombok.Getter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.rzlindustries.mx.core.entities.Etiqueta;

@Getter
@Builder
@Schema(name = "Etiqueta", description = "Clase DTO para visualizar una etiqueta.")
public class EtiquetaDTO {
    @Schema(readOnly = true, description = "Identificador de la etiqueta")
    private Integer id;
    @Schema(readOnly = true, description = "Identificador del tipo de etiqueta")
    private Integer idTipo;
    @Schema(readOnly = true, description = "Nombre de la etiqueta")
    private String nombre;
    @Schema(readOnly = true, description = "Color de la etiqueta en formato hexadecimal")
    private String color;

    public static EtiquetaDTO fromEntity(Etiqueta etiqueta) {
        return EtiquetaDTO.builder()
                .id(etiqueta.getId())
                .idTipo(etiqueta.getIdTipo())
                .nombre(etiqueta.getNombre())
                .color(etiqueta.getColor())
                .build();
    }
}
