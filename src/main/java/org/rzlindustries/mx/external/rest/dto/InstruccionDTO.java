package org.rzlindustries.mx.external.rest.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Getter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.rzlindustries.mx.core.entities.Instruccion;

@Getter
@Builder
@Schema(name = "Instruccion", description = "Clase DTO utilizada para visualizar y crear las instrucciones de una nueva receta.")
public class InstruccionDTO {
    @Schema(readOnly = true, description = "Identificador de la instrucción")
    private Integer id;
    @NotNull(message = "REB_RNS002")
    @NotEmpty(message = "REB_RNS002")
    @Schema(required = true, description = "Texto de la instrucción")
    private String instruccion;
    @NotNull(message = "REB_RNS002")
    @Positive(message = "REB_RNS002")
    @Schema(required = true, description = "Orden de la instrucción")
    private Integer orden;

    public Instruccion toEntity() {
        return Instruccion.builder()
                .instruccion(instruccion)
                .orden(orden)
                .build();
    }

    public static InstruccionDTO fromEntity(Instruccion instruccion) {
        return InstruccionDTO.builder()
                .id(instruccion.getId())
                .instruccion(instruccion.getInstruccion())
                .orden(instruccion.getOrden())
                .build();
    }
}
