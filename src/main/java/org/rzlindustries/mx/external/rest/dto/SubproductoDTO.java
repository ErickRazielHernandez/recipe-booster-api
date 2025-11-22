package org.rzlindustries.mx.external.rest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Getter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.rzlindustries.mx.core.entities.Receta;
import org.rzlindustries.mx.core.entities.Subproducto;
import org.rzlindustries.mx.utils.BsConstants;

import java.time.LocalTime;
import java.util.List;

@Getter
@Builder
@Schema(name = "RecetaCreate", description = "Clase DTO utilizada para crear una nueva receta.")
public class SubproductoDTO {
    @Schema(readOnly = true, description = "Identificador del subproducto")
    private Integer id;

    @NotNull(message = "REB_RNS002")
    @NotEmpty(message = "REB_RNS002")
    @Schema(required = true, description = "Nombre del subproducto")
    private String nombre;

    @NotEmpty(message = "REB_RNS002")
    @Schema(description = "Descripción del subproducto")
    private String descripcion;

    @NotNull(message = "REB_RNS002")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = BsConstants.LOCAL_TIME_FORMAT)
    @Schema(required = true, description = "Tiempo de preparación del subproducto", implementation = String.class, pattern = BsConstants.LOCAL_TIME_FORMAT)
    private LocalTime tiempoPreparacion;

    @NotNull(message = "REB_RNS002")
    @NotEmpty(message = "REB_RNS002")
    @Schema(required = true, description = "Lista de instrucciones de la receta")
    private @Valid List<InstruccionDTO> instrucciones;

    @NotNull(message = "REB_RNS002")
    @Positive(message = "REB_RNS002")
    @Schema(required = true, description = "Número de orden del subproducto")
    private Integer orden;

    public Subproducto toEntity() {
        return Subproducto.builder()
                .nombre(nombre)
                .descripcion(descripcion)
                .tiempoPreparacion(tiempoPreparacion)
                .instrucciones(instrucciones.stream().map(InstruccionDTO::toEntity).toList())
                .orden(orden)
                .build();
    }

    public static SubproductoDTO fromEntity(Subproducto subproducto) {
        return SubproductoDTO.builder()
                .id(subproducto.getId())
                .nombre(subproducto.getNombre())
                .descripcion(subproducto.getDescripcion())
                .tiempoPreparacion(subproducto.getTiempoPreparacion())
                .instrucciones(subproducto.getInstrucciones().stream().map(InstruccionDTO::fromEntity).toList())
                .orden(subproducto.getOrden())
                .build();
    }
}
