package org.rzlindustries.mx.external.jpa.model;

import jakarta.persistence.*;
import lombok.*;
import org.rzlindustries.mx.core.entities.Receta;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "trc01_receta")
public class RecetaJpa {
    @Id
    @Column(name = "id_receta")
    @SequenceGenerator(name = "trc01_receta_id_receta_seq", sequenceName = "trc01_receta_id_receta_seq", allocationSize = 1)
    @GeneratedValue(generator = "trc01_receta_id_receta_seq", strategy = GenerationType.SEQUENCE)
    private Integer id;
    @Column(name = "tx_nombre")
    private String nombre;
    @Column(name = "tx_descripcion")
    private String descripcion;
    @Column(name = "tm_tiempo_preparacion")
    private LocalTime tiempoPreparacion;
    @Column(name = "fh_creacion")
    private LocalDateTime creacion;
    @Column(name = "fh_actualizacion")
    private LocalDateTime actualizacion;
    @Column(name = "nu_veces_realizada")
    private Integer vecesRealizada;
    @Column(name = "fh_ultima_realizacion")
    private LocalDateTime ultimaRealizacion;

    @OneToMany(mappedBy = "recetaJpa", fetch = FetchType.LAZY)
    private List<SubproductoJpa> subproductosJpa;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "trc10_instruccion_receta", joinColumns = @JoinColumn(name = "fk_id_receta"), inverseJoinColumns = @JoinColumn(name = "fk_id_instruccion"))
    private List<InstruccionJpa> instruccionesJpa;

    public static RecetaJpa fromEntity(Receta receta) {
        return RecetaJpa.builder()
                .id(receta.getId())
                .nombre(receta.getNombre())
                .descripcion(receta.getDescripcion())
                .tiempoPreparacion(receta.getTiempoPreparacion())
                .creacion(receta.getCreacion())
                .actualizacion(receta.getActualizacion())
                .vecesRealizada(receta.getVecesRealizada())
                .ultimaRealizacion(receta.getUltimaRealizacion())
                .build();
    }

    public Receta toEntity() {
        return Receta.builder()
                .id(id)
                .nombre(nombre)
                .descripcion(descripcion)
                .tiempoPreparacion(tiempoPreparacion)
                .creacion(creacion)
                .actualizacion(actualizacion)
                .vecesRealizada(vecesRealizada)
                .ultimaRealizacion(ultimaRealizacion)
                .build();
    }

    public Receta toEntityWithSubproductosAndInstrucciones() {
        return Receta.builder()
                .id(id)
                .nombre(nombre)
                .descripcion(descripcion)
                .tiempoPreparacion(tiempoPreparacion)
                .creacion(creacion)
                .actualizacion(actualizacion)
                .vecesRealizada(vecesRealizada)
                .ultimaRealizacion(ultimaRealizacion)
                .subproductos(subproductosJpa.stream().map(SubproductoJpa::toEntityWithInstrucciones).toList())
                .instrucciones(instruccionesJpa.stream().map(InstruccionJpa::toEntity).toList())
                .build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecetaJpa that = (RecetaJpa) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
