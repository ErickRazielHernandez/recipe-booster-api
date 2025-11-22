package org.rzlindustries.mx.external.rest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.rzlindustries.mx.core.entities.Receta;
import org.rzlindustries.mx.utils.BsConstants;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "RecetaGet", description = "Clase DTO para visualizar la información de una receta.")
public class RecetaGetDTO {
    @Schema(readOnly = true, description = "Identificador de la receta")
    private Integer id;
    @Schema(readOnly = true, description = "Nombre de la receta")
    private String nombre;
    @Schema(readOnly = true, description = "Descripción")
    private String descripcion;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = BsConstants.LOCAL_TIME_FORMAT)
    @Schema(readOnly = true, description = "Tiempo aproximado de preparación")
    private LocalTime tiempoPreparacion;
    @JsonFormat(shape =  JsonFormat.Shape.STRING, pattern = BsConstants.LOCAL_DATE_TIME_FORMAT)
    @Schema(readOnly = true, description = "Fecha y hora de creación")
    private LocalDateTime creacion;
    @JsonFormat(shape =  JsonFormat.Shape.STRING, pattern = BsConstants.LOCAL_DATE_TIME_FORMAT)
    @Schema(readOnly = true, description = "Fecha y hora de última actualización")
    private LocalDateTime actualizacion;
    @Schema(readOnly = true, description = "Número de veces que se ha realizado la receta")
    private Integer vecesRealizada;
    @JsonFormat(shape =  JsonFormat.Shape.STRING, pattern = BsConstants.LOCAL_DATE_TIME_FORMAT)
    @Schema(readOnly = true, description = "Fecha y hora de la última vez que se realizó")
    private LocalDateTime ultimaRealizacion;
    @Schema(readOnly = true, description = "Lista de instrucciones de la receta")
    private List<InstruccionDTO> instrucciones;
    @Schema(readOnly = true, description = "Lista de subproductos de la receta")
    private List<SubproductoDTO> subproductos;

    public static RecetaGetDTO fromEntity(Receta receta) {
        return RecetaGetDTO.builder()
                .id(receta.getId())
                .nombre(receta.getNombre())
                .descripcion(receta.getDescripcion())
                .tiempoPreparacion(receta.getTiempoPreparacion())
                .creacion(receta.getCreacion())
                .actualizacion(receta.getActualizacion())
                .vecesRealizada(receta.getVecesRealizada())
                .ultimaRealizacion(receta.getUltimaRealizacion())
                .instrucciones(receta.getInstrucciones().stream().map(InstruccionDTO::fromEntity).toList())
                .subproductos(receta.getSubproductos().stream().map(SubproductoDTO::fromEntity).toList())
                .build();
    }
}
