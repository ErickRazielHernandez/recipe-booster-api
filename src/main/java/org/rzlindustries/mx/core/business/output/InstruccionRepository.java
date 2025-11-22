package org.rzlindustries.mx.core.business.output;

import org.rzlindustries.mx.core.entities.Instruccion;
import org.rzlindustries.mx.core.entities.InstruccionReceta;
import org.rzlindustries.mx.core.entities.InstruccionSubproducto;

import java.util.List;

public interface InstruccionRepository {
    /**
     * Persiste una lista de entidades en la base de datos
     *
     * @param instrucciones Lista de entidades de tipo {@link Instruccion} a persistir
     * @return Lista de entidades de tipo {@link Instruccion}
     */
    List<Instruccion> saveAll(List<Instruccion> instrucciones);

    /**
     * Persiste una lista de entidades en la base de datos
     *
     * @param instruccionesRecetas Lista de entidades de tipo {@link InstruccionReceta} a persistir
     */
    void saveAllInstruccionReceta(List<InstruccionReceta> instruccionesRecetas);

    /**
     * Persiste una lista de entidades en la base de datos
     *
     * @param instruccionesSubproductos Lista de entidades de tipo {@link InstruccionSubproducto} a persistir
     */
    void saveAllInstruccionSubproducto(List<InstruccionSubproducto> instruccionesSubproductos);
}
