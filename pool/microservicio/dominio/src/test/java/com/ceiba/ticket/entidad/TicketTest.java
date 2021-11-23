package com.ceiba.ticket.entidad;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.ticket.servicio.testDataBuilder.TicketTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TicketTest {

    @Test
    @DisplayName("Deberia crear correctamente el ticket")
    void deberiaCrearCorrectamenteElTicket() {

        // Arrange
        var costoTotal = 5000;
        var costoHoraExtra = 1000;
        var fechaVencimiento = LocalDateTime.now().plusHours(5);

        // Act
        var ticket = new TicketTestDataBuilder()
                .conId(1L)
                .conCostoTotal(costoTotal)
                .conFechaVencimiento(fechaVencimiento)
                .conOrdenId(1L)
                .build();

        // Assert
        assertEquals(1L, ticket.getId());
        assertEquals(costoTotal, ticket.getCostoTotal());
        assertEquals(fechaVencimiento, ticket.getFechaVencimiento().withNano(fechaVencimiento.getNano()));
        assertEquals(1L, ticket.getOrdenId());
    }

    @Test
    void deberiaFallarConCostoTotalNoValido() {

        // Arrange
        var ticketTestDataBuilder = new TicketTestDataBuilder().conId(1L).conCostoTotal(0);

        // Act - Assert
        BasePrueba.assertThrows(() -> {
                ticketTestDataBuilder.build();
            },
            ExcepcionValorInvalido.class, "El costo total debe ser mayor a cero");
    }

    @Test
    void deberiaFallarSinFechaDeVencimiento() {

        // Arrange
        var ticketTestDataBuilder = new TicketTestDataBuilder().conId(1L).conFechaVencimiento(null);

        // Act - Assert
        BasePrueba.assertThrows(() -> {
                    ticketTestDataBuilder.build();
                },
                ExcepcionValorObligatorio.class, "Se debe ingresar una fecha de vencimiento");
    }

    @Test
    void deberiaFallarConFechaDeVencimientoNoValida() {

        // Arrange
        var ticketTestDataBuilder = new TicketTestDataBuilder().conId(1L)
                .conFechaVencimiento(LocalDateTime.now().minusHours(1));

        // Act - Assert
        BasePrueba.assertThrows(() -> {
                    ticketTestDataBuilder.build();
                },
                ExcepcionValorInvalido.class, "Se debe ingresar una fecha de vencimiento posterior");
    }

    @Test
    void deberiaFallarSinIdDeLaOrden() {

        // Arrange
        var ticketTestDataBuilder = new TicketTestDataBuilder().conId(1L).conOrdenId(null);

        // Act - Assert
        BasePrueba.assertThrows(() -> {
                    ticketTestDataBuilder.build();
                },
                ExcepcionValorObligatorio.class, "Se debe ingresar el id de la orden");
    }
}
