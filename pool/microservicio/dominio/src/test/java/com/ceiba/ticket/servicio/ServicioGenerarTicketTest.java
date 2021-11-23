package com.ceiba.ticket.servicio;

import com.ceiba.orden.servicio.testDataBuilder.OrdenTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ServicioGenerarTicketTest {

    @Test
    @DisplayName("Deberia generar el ticket de manera correcta")
    void deberiaGenerarElTicketDeManeraCorrecta() {

        // Arrange
        var orden = new OrdenTestDataBuilder().conId(1L).conTiempoExtra(1).build();
        var fechaVencimiento = LocalDateTime.now().plusHours(5);
        var servicioGenerarTicket = new ServicioGenerarTicket();

        // Act
        var ticket = servicioGenerarTicket.ejecutar(orden, 1L, 4000);

        // Assert
        assertEquals(5000, ticket.getCostoTotal());
        assertEquals(fechaVencimiento, ticket.getFechaVencimiento().withNano(fechaVencimiento.getNano()));
        assertEquals(1L, ticket.getOrdenId());
    }

   /* @Test
    @DisplayName("Deberia calcular el costo total del ticket de manera correcta")
    void deberiaCalcularElCostoTotalDelTicketDeManeraCorrecta() {

        // Arrange
        var orden = new OrdenTestDataBuilder().build();
        var servicioGenerarTicket = new ServicioGenerarTicket();

        // Act
        var ticket = servicioGenerarTicket.ejecutar(orden, 1L, 4000);

        // Assert
        assertEquals(5000, ticket.getCostoTotal());
    }

    @Test
    @DisplayName("Deberia calcular la fecha del ticket de manera correcta")
    void deberiaCalcularLaFechaDelTicketDeManeraCorrecta() {

        // Arrange
        var orden = new OrdenTestDataBuilder().conTiempoExtra(1).build();
        var fechaVencimiento = LocalDateTime.now().plusHours(5);
        var servicioGenerarTicket = new ServicioGenerarTicket();

        // Act
        var ticket = servicioGenerarTicket.ejecutar(orden, 1L, 4000);

        // Assert
        assertEquals(fechaVencimiento, ticket.getFechaVencimiento().withNano(fechaVencimiento.getNano()));
    } */
}
