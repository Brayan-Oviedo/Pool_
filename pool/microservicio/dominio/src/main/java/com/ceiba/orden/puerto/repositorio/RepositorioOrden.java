package com.ceiba.orden.puerto.repositorio;

import com.ceiba.orden.modelo.entidad.Orden;

public interface RepositorioOrden {

    /**
     * Permite crear una orden
     * @param orden
     * @return el id generado
     */
    Long crear(Orden orden);

    /**
     * Permite validar si existe una orden con un id
     * @param id
     * @return si existe o no
     */
    boolean existePorId(Long id);

    /**
     * Permite eliminar una orden
     * @param id
     */
    void eliminar(Long id);
}
