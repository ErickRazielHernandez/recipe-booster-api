package org.rzlindustries.mx.external.rest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.rzlindustries.mx.core.entities.Receta;
import org.rzlindustries.mx.utils.BsConstants;

import java.time.LocalTime;

@Getter
@Builder
@Schema(name = "Receta", description = "Clase DTO para visualizar una receta.")
public class RecetaDTO {
    @Schema(readOnly = true, description = "Identificador de la receta")
    private Integer id;
    @Schema(readOnly = true, description = "Nombre de la receta")
    private String nombre;
    @Schema(readOnly = true, description = "Descripción de la receta")
    private String descripcion;
    @JsonFormat(shape =  JsonFormat.Shape.STRING, pattern = BsConstants.LOCAL_TIME_FORMAT)
    @Schema(readOnly = true, description = "Tiempo de preparación")
    private LocalTime tiempoPreparacion;

    public static RecetaDTO fromEntity(Receta receta) {
        return RecetaDTO.builder()
                .id(receta.getId())
                .nombre(receta.getNombre())
                .descripcion(receta.getDescripcion())
                .tiempoPreparacion(receta.getTiempoPreparacion())
                .build();
    }
}
