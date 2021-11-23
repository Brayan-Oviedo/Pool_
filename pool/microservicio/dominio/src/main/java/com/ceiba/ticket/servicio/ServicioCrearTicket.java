package com.ceiba.ticket.servicio;

import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.ticket.modelo.dto.DtoTicket;
import com.ceiba.ticket.modelo.entidad.Ticket;
import com.ceiba.ticket.puerto.dao.DaoTicket;
import com.ceiba.ticket.puerto.repositorio.RepositorioTicket;

public class ServicioCrearTicket {

    private static final String EL_TICKET_YA_EXISTE_EN_EL_SISTEMA = "El ticket ya existe en el sistema";

    private final RepositorioTicket repositorioTicket;
    private final DaoTicket daoTicket;

    public ServicioCrearTicket(RepositorioTicket repositorioTicket, DaoTicket daoTicket) {
        this.repositorioTicket = repositorioTicket;
        this.daoTicket = daoTicket;
    }

    public DtoTicket ejecutar(Ticket ticket) {
        validarExistenciaPrevia(ticket);
        var id = this.repositorioTicket.crear(ticket);

        return this.daoTicket.obtenerPorId(id);
    }

    private void validarExistenciaPrevia(Ticket ticket) {
        var existe = this.repositorioTicket.existePorId(ticket.getId());
        if(existe) {
            throw new ExcepcionDuplicidad(EL_TICKET_YA_EXISTE_EN_EL_SISTEMA);
        }
    }
}
