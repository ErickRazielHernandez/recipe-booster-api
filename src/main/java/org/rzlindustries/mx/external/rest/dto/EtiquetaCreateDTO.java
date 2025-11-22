package org.rzlindustries.mx.external.rest.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.rzlindustries.mx.core.entities.Etiqueta;

@Getter
@Builder
@Schema(name = "TagCreateDTO", description = "Clase DTO utilizada para la creación de una etiqueta.")
public class EtiquetaCreateDTO {
    @NotNull(message = "REB_RNS002")
    @Positive(message = "REB_RNS002")
    @Schema(writeOnly = true, required = true, description = "Identificador del tipo de etiqueta")
    private Integer idTipo;
    @NotNull(message = "REB_RNS002")
    @NotEmpty(message = "REB_RNS002")
    @Size(max = 100, message = "REB_RNN002")
    @Schema(writeOnly = true, required = true, description = "Nombre de la etiqueta")
    private String nombre;
    @NotNull(message = "REB_RNS002")
    @NotEmpty(message = "REB_RNS002")
    @Size(min = 8, max = 8, message = "REB_RNN002")
    @Schema(writeOnly = true, required = true, description = "Color de la etiqueta en fromato hexadecimal", examples = "0xFFFFFF")
    private String color;

    public Etiqueta toEntity(){
        return Etiqueta.builder()
                .idTipo(idTipo)
                .nombre(nombre)
                .color(color)
                .build();
    }
}
