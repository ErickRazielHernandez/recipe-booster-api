package org.rzlindustries.mx.external.jpa.model;

import jakarta.persistence.*;
import lombok.*;
import org.rzlindustries.mx.core.entities.Subproducto;

import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "trc09_subproducto")
public class SubproductoJpa {
    @Id
    @Column(name = "id_subproducto")
    @SequenceGenerator(name = "trc09_subproducto_id_subproducto_seq", sequenceName = "trc09_subproducto_id_subproducto_seq", allocationSize = 1)
    @GeneratedValue(generator = "trc09_subproducto_id_subproducto_seq", strategy = GenerationType.SEQUENCE)
    private Integer id;
    @Column(name = "fk_id_receta")
    private Integer idReceta;
    @Column(name = "tx_nombre")
    private String nombre;
    @Column(name = "tx_descripcion")
    private String descripcion;
    @Column(name = "tm_tiempo_preparacion")
    private LocalTime tiempoPreparacion;
    @Column(name = "nu_orden")
    private Integer orden;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_id_receta", referencedColumnName = "id_receta", insertable = false, updatable = false)
    private RecetaJpa recetaJpa;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "trc11_instruccion_subproducto",
            joinColumns = @JoinColumn(name = "fk_id_subproducto", insertable = false, updatable = false),
            inverseJoinColumns = @JoinColumn(name = "fk_id_instruccion", insertable = false, updatable = false))
    private List<InstruccionJpa> instruccionesJpa;

    public static SubproductoJpa fromEntity(Subproducto subproducto) {
        return SubproductoJpa.builder()
                .id(subproducto.getId())
                .idReceta(subproducto.getIdReceta())
                .nombre(subproducto.getNombre())
                .descripcion(subproducto.getDescripcion())
                .tiempoPreparacion(subproducto.getTiempoPreparacion())
                .orden(subproducto.getOrden())
                .build();
    }

    public Subproducto toEntity() {
        return Subproducto.builder()
                .id(id)
                .idReceta(idReceta)
                .nombre(nombre)
                .descripcion(descripcion)
                .tiempoPreparacion(tiempoPreparacion)
                .orden(orden)
                .build();
    }

    public Subproducto toEntityWithInstrucciones(){
        return Subproducto.builder()
                .id(id)
                .idReceta(idReceta)
                .nombre(nombre)
                .descripcion(descripcion)
                .tiempoPreparacion(tiempoPreparacion)
                .orden(orden)
                .instrucciones(instruccionesJpa.stream().map(InstruccionJpa::toEntity).toList())
                .build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubproductoJpa that = (SubproductoJpa) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
