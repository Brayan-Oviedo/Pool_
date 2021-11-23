package com.ceiba.ticket.servicio.testDataBuilder;

import com.ceiba.ticket.modelo.dto.DtoTicket;
import com.ceiba.ticket.modelo.entidad.Ticket;

import java.time.LocalDateTime;


public class TicketTestDataBuilder {

    private Long id;
    private float costoTotal;
    private LocalDateTime fechaVencimiento;
    private Long ordenId;

    public TicketTestDataBuilder() {
        this.costoTotal = 5000;
        this.fechaVencimiento = LocalDateTime.now().plusHours(5);
        this.ordenId = 1L;
    }

    public TicketTestDataBuilder conId(Long id) {
        this.id = id;
        return this;
    }

    public TicketTestDataBuilder conCostoTotal(float costoTotal) {
        this.costoTotal = costoTotal;
        return this;
    }

    public TicketTestDataBuilder conFechaVencimiento(LocalDateTime fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
        return this;
    }

    public TicketTestDataBuilder conOrdenId(Long ordenId) {
        this.ordenId = ordenId;
        return this;
    }

    public Ticket build() {
        return new Ticket(id, costoTotal, fechaVencimiento, ordenId);
    }

    public DtoTicket buildDto() { return new DtoTicket(id, costoTotal, fechaVencimiento); }
}
