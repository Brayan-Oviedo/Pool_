package com.ceiba.orden.modelo.dto;

import com.ceiba.cliente.modelo.dto.DtoCliente;
import com.ceiba.ticket.modelo.dto.DtoTicket;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DtoOrden {

    private Long id;
    private int tiempoExtra;
    private DtoTicket ticket;
    private DtoCliente cliente;
}
