package com.ceiba.ticket.modelo.entidad;


import lombok.Getter;

import java.time.LocalDateTime;

import static com.ceiba.dominio.ValidadorArgumento.*;

@Getter
public class Ticket {

    private static final String EL_COSTO_TOTAL_DEBE_SER_MAYOR_A_CERO = "El costo total debe ser mayor a cero";
    private static final String SE_DEBE_INGRESAR_UNA_FECHA_DE_VENCIMIENTO = "Se debe ingresar una fecha de vencimiento";
    private static final String SE_DEBE_INGRESAR_UNA_FECHA_DE_VENCIMIENTO_POSTERIOR = "Se debe ingresar una fecha de vencimiento posterior";
    private static final String SE_DEBE_INGRESAR_EL_ID_DE_LA_ORDEN = "Se debe ingresar el id de la orden";


    private Long id;
    private float costoTotal;
    private LocalDateTime fechaVencimiento;
    private Long ordenId;


    public Ticket(Long id, float costoTotal, LocalDateTime fechaVencimiento, Long ordenId) {

        validarPositivo((double) costoTotal, EL_COSTO_TOTAL_DEBE_SER_MAYOR_A_CERO);
        validarObligatorio(fechaVencimiento, SE_DEBE_INGRESAR_UNA_FECHA_DE_VENCIMIENTO);
        validarMenor(LocalDateTime.now(), fechaVencimiento, SE_DEBE_INGRESAR_UNA_FECHA_DE_VENCIMIENTO_POSTERIOR);
        validarObligatorio(ordenId, SE_DEBE_INGRESAR_EL_ID_DE_LA_ORDEN);

        this.id = id;
        this.costoTotal = costoTotal;
        this.fechaVencimiento = fechaVencimiento;
        this.ordenId = ordenId;
    }
}
