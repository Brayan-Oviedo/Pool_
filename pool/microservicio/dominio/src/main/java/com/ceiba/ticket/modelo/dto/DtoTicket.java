package com.ceiba.ticket.modelo.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DtoTicket {

    private Long id;
    private float costoTotal;
    private LocalDateTime fechaVencimiento;
}
