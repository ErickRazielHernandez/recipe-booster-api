package org.rzlindustries.mx.core.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@Builder
public class Receta {
    private Integer id;
    private String nombre;
    private String descripcion;
    private LocalTime tiempoPreparacion;
    private LocalDateTime creacion;
    private LocalDateTime actualizacion;
    private Integer vecesRealizada;
    private LocalDateTime ultimaRealizacion;

    //Relaciones
    private List<Instruccion> instrucciones;
    private List<Subproducto> subproductos;
}
