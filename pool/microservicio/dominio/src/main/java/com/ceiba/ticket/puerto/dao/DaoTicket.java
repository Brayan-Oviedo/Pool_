package com.ceiba.ticket.puerto.dao;

import com.ceiba.ticket.modelo.dto.DtoTicket;

public interface DaoTicket {

    /**
     * Permite obtener un ticket con un id
     * @param id
     * @return el ticket
     */
    DtoTicket obtenerPorId(Long id);
}
