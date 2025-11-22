package org.rzlindustries.mx.utils.generic;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.*;

@Getter
@Setter
@MappedSuperclass
public abstract class CatalogoJpa {
    @Id
    protected Integer id;
    @Column(name = "tx_nombre")
    protected String nombre;
    @Column(name = "tx_descripcion")
    protected String descripcion;
    @Column(name = "st_activo")
    protected Boolean activo;
}
