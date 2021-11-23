package com.ceiba.orden.servicio;

import com.ceiba.cliente.modelo.entidad.Cliente;
import com.ceiba.cliente.servicio.ServicioCrearCliente;
import com.ceiba.orden.modelo.entidad.Orden;
import com.ceiba.ticket.modelo.dto.DtoTicket;
import com.ceiba.ticket.servicio.ServicioCrearTicket;
import com.ceiba.ticket.servicio.ServicioGenerarTicket;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;

public class ServicioProcesarOrden {

    private static final float COSTO_BASE_EDAD_MENOR_A_8 = 4000;
    private static final float COSTO_BASE_EDAD_IGUAL_O_MAYOR_A_8 = 8000;
    private static final double PORCENTAJE_AUMENTO_FIN_DE_SEMANA = 10;

    private final ServicioCrearOrden servicioCrearOrden;
    private final ServicioCrearCliente servicioCrearCliente;
    private final ServicioGenerarTicket servicioGenerarTicket;
    private final ServicioCrearTicket servicioCrearTicket;

    public ServicioProcesarOrden(
            ServicioCrearOrden servicioCrearOrden,
            ServicioCrearCliente servicioCrearCliente,
            ServicioGenerarTicket servicioGenerarTicket,
            ServicioCrearTicket servicioCrearTicket) {


        this.servicioCrearOrden = servicioCrearOrden;
        this.servicioCrearCliente = servicioCrearCliente;
        this.servicioGenerarTicket = servicioGenerarTicket;
        this.servicioCrearTicket = servicioCrearTicket;
    }

    public DtoTicket ejecutar(Orden orden, Cliente cliente) {
        this.servicioCrearCliente.ejecutar(cliente);
        var ordenId = this.servicioCrearOrden.ejecutar(orden);
        var ticket = this.servicioGenerarTicket.ejecutar(orden, ordenId, obtenerCostoBase(cliente));

        return this.servicioCrearTicket.ejecutar(ticket);
    }

    private float obtenerCostoBase(Cliente cliente) {
        var fechaNacimiento = cliente.getFechaNacimiento();
        var fechaActual = LocalDate.now();
        var periodo = Period.between(fechaNacimiento, fechaActual);

        if(periodo.getYears() < 8) {
            return verificarAumentoCostoFinDeSemana(COSTO_BASE_EDAD_MENOR_A_8);
        }else {
            return verificarAumentoCostoFinDeSemana(COSTO_BASE_EDAD_IGUAL_O_MAYOR_A_8);
        }
    }

    private float verificarAumentoCostoFinDeSemana(float costo) {

        if(LocalDate.now().getDayOfWeek() == DayOfWeek.SATURDAY || LocalDate.now().getDayOfWeek() == DayOfWeek.SUNDAY) {
            costo += costo * (PORCENTAJE_AUMENTO_FIN_DE_SEMANA / 100);
        }

        return costo;
    }

}
