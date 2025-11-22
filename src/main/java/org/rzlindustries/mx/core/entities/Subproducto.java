package org.rzlindustries.mx.core.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@Builder
public class Subproducto {
    private Integer id;
    private Integer idReceta;
    private String nombre;
    private String descripcion;
    private LocalTime tiempoPreparacion;
    private List<Instruccion> instrucciones;
    private Integer orden;
}