package com.ceiba.orden.puerto.dao;

import com.ceiba.orden.modelo.dto.DtoOrden;

import java.util.List;

public interface DaoOrden {

    /**
     * Permite listar ordenes con un cliente
     * @return las ordenes
     */
    List<DtoOrden> listarPorCliente(String identificacion);

    /**
     * Permite obtener una orden con un id
     * @param id
     * @return la orden
     */
    DtoOrden obtenerPorId(Long id);
}
