package org.rzlindustries.mx.external.rest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.rzlindustries.mx.core.entities.Receta;
import org.rzlindustries.mx.utils.BsConstants;

import java.time.LocalTime;
import java.util.List;

@Getter
@Builder
@Schema(name = "RecetaCreate", description = "Clase DTO utilizada para crear una nueva receta.")
public class RecetaCreateDTO {
    @NotNull(message = "REB_RNS002")
    @NotEmpty(message = "REB_RNS002")
    @Schema(writeOnly = true, required = true, description = "Nombre de la receta")
    private String nombre;

    @NotEmpty(message = "REB_RNS002")
    @Schema(writeOnly = true, description = "Descripción de la receta")
    private String descripcion;

    @NotNull(message = "REB_RNS002")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = BsConstants.LOCAL_TIME_FORMAT)
    @Schema(writeOnly = true, required = true, description = "Tiempo de preparación total de la receta", implementation = String.class, pattern = BsConstants.LOCAL_TIME_FORMAT)
    private LocalTime tiempoPreparacion;

    @NotNull(message = "REB_RNS002")
    @NotEmpty(message = "REB_RNS002")
    @Schema(writeOnly = true, required = true, description = "Lista de instrucciones de la receta")
    private @Valid List<InstruccionDTO> instrucciones;

    @NotNull(message = "REB_RNS002")
    @NotEmpty(message = "REB_RNS002")
    @Schema(writeOnly = true, required = true, description = "Lista de subproductos de la receta")
    private @Valid List<SubproductoDTO> subproductos;

    public Receta toEntity() {
        return Receta.builder()
                .nombre(nombre)
                .descripcion(descripcion)
                .tiempoPreparacion(tiempoPreparacion)
                .instrucciones(instrucciones.stream().map(InstruccionDTO::toEntity).toList())
                .subproductos(subproductos.stream().map(SubproductoDTO::toEntity).toList())
                .build();
    }
}
