package com.ceiba.cliente.puerto.repositorio;

import com.ceiba.cliente.modelo.entidad.Cliente;

public interface RepositorioCliente {

    /**
     * Permite crear un cliente
     * @param cliente
     * @return el id generado
     */
    Long crear(Cliente cliente);

    /**
     * Permite validar si existe un cliente con una identificacion
     * @param identificacion
     * @return si existe o no
     */
    boolean existePorIdentificacion(String identificacion);
}
