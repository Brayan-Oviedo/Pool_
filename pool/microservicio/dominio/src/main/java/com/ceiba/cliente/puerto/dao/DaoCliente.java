package com.ceiba.cliente.puerto.dao;

import com.ceiba.cliente.modelo.dto.DtoCliente;

public interface DaoCliente {

    /**
     * Permite obtener un cliente con una identificacion
     * @param identificacion
     * @return el cliente
     */
    DtoCliente obtenerPorIdentificacion(String identificacion);
}
