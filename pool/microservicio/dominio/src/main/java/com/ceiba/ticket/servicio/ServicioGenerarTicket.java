package com.ceiba.ticket.servicio;

import com.ceiba.orden.modelo.entidad.Orden;
import com.ceiba.ticket.modelo.entidad.Ticket;

import java.time.LocalDateTime;

public class ServicioGenerarTicket {

    private static final int TIEMPO_BASE_SERVICIO = 4;
    private static final float COSTO_HORA_EXTRA = 1000;

    public Ticket ejecutar(Orden orden, Long ordenId, float costoBase) {
        return new Ticket(null,
                calcularCostoTotal(costoBase, orden.getTiempoExtra()),
                calcularFechaVencimiento(orden.getTiempoExtra()),
                ordenId
        );
    }

    private float calcularCostoTotal(float costoBase, int tiempoExtra) {
        var costo = costoBase;
        costo += tiempoExtra * COSTO_HORA_EXTRA;
        return costo;
    }

    private LocalDateTime calcularFechaVencimiento(int tiempoExtra) {
        return LocalDateTime.now().plusHours(TIEMPO_BASE_SERVICIO + tiempoExtra);
    }
}
