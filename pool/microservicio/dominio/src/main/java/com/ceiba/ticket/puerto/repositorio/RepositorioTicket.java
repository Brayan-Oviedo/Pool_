package com.ceiba.ticket.puerto.repositorio;

import com.ceiba.ticket.modelo.entidad.Ticket;

public interface RepositorioTicket {

    /**
     * Permite crear un ticket
     * @param ticket
     * @return el id generado
     */
    Long crear(Ticket ticket);

    /**
     * Permite validar si existe un ticket con un id
     * @param id
     * @return si existe o no
     */
    boolean existePorId(Long id);
}
