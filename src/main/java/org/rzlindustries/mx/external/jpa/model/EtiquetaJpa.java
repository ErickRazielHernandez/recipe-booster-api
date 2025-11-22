package org.rzlindustries.mx.external.jpa.model;

import jakarta.persistence.*;
import lombok.*;
import org.rzlindustries.mx.core.entities.Etiqueta;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "trc03_etiqueta")
public class EtiquetaJpa {
    @Id
    @Column(name = "id_etiqueta")
    @SequenceGenerator(name = "trc03_etiqueta_id_etiqueta_seq", sequenceName = "trc03_etiqueta_id_etiqueta_seq", allocationSize = 1)
    @GeneratedValue(generator = "trc03_etiqueta_id_etiqueta_seq", strategy = GenerationType.SEQUENCE)
    private Integer id;
    @Column(name = "fk_id_tipo")
    private Integer idTipo;
    @Column(name = "tx_nombre")
    private String nombre;
    @Column(name = "tx_color")
    private String color;

    public Etiqueta toEntity() {
        return Etiqueta.builder()
                .id(id)
                .idTipo(idTipo)
                .nombre(nombre)
                .color(color)
                .build();
    }

    public static EtiquetaJpa fromEntity(Etiqueta etiqueta) {
        return EtiquetaJpa.builder()
                .id(etiqueta.getId())
                .idTipo(etiqueta.getIdTipo())
                .nombre(etiqueta.getNombre())
                .color(etiqueta.getColor())
                .build();
    }
}
