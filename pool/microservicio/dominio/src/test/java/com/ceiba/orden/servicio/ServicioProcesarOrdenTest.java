package com.ceiba.orden.servicio;

import com.ceiba.cliente.servicio.ServicioCrearCliente;
import com.ceiba.cliente.servicio.testDataBuilder.ClienteTestDataBuilder;
import com.ceiba.orden.servicio.testDataBuilder.OrdenTestDataBuilder;
import com.ceiba.ticket.servicio.ServicioCrearTicket;
import com.ceiba.ticket.servicio.ServicioGenerarTicket;
import com.ceiba.ticket.servicio.testDataBuilder.TicketTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.DayOfWeek;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ServicioProcesarOrdenTest {

    @Test
    @DisplayName("Deberia procesar la orden de manera correcta con un menor de 8 años")
    void deberiaProcesarLaOrdenParaClienteMenorDeOchoAniosDeManeraCorrecta() {

        // Arrange
        var orden = new OrdenTestDataBuilder().build();
        var cliente = new ClienteTestDataBuilder().conFechaNacimiento(LocalDate.now().minusYears(1)).build();
        var ticket = new TicketTestDataBuilder().build();
        var ticketDto = new TicketTestDataBuilder().conId(1L).buildDto();
        var servicioCrearCliente = Mockito.mock(ServicioCrearCliente.class);
        var servicioCrearOrden = Mockito.mock(ServicioCrearOrden.class);
        var servicioGenerarTicket = Mockito.mock(ServicioGenerarTicket.class);
        var servicioCrearTicket = Mockito.mock(ServicioCrearTicket.class);
        Mockito.when(servicioCrearOrden.ejecutar(orden)).thenReturn(1L);
        Mockito.when(servicioGenerarTicket.ejecutar(orden, 1L, verificarAumentoFinDeSemana(4000))).thenReturn(ticket);
        Mockito.when(servicioCrearTicket.ejecutar(ticket)).thenReturn(ticketDto);
        var servicioProcesarOrden = new ServicioProcesarOrden(servicioCrearOrden, servicioCrearCliente, servicioGenerarTicket, servicioCrearTicket);

        // Act
        var dtoTicket = servicioProcesarOrden.ejecutar(orden, cliente);

        // Assert
        assertEquals(ticketDto, dtoTicket);
        Mockito.verify(servicioCrearCliente, Mockito.times(1)).ejecutar(cliente);
        Mockito.verify(servicioCrearOrden, Mockito.times(1)).ejecutar(orden);
        Mockito.verify(servicioGenerarTicket, Mockito.times(1)).ejecutar(orden, 1L, verificarAumentoFinDeSemana(4000));
        Mockito.verify(servicioCrearTicket, Mockito.times(1)).ejecutar(ticket);
    }

    @Test
    @DisplayName("Deberia procesar la orden de manera correcta con un menor de 8 años")
    void deberiaProcesarLaOrdenParaClienteConEdadMayorOIgualAOchoAniosDeManeraCorrecta() {

        // Arrange
        var orden = new OrdenTestDataBuilder().build();
        var cliente = new ClienteTestDataBuilder().conFechaNacimiento(LocalDate.now().minusYears(8)).build();
        var ticket = new TicketTestDataBuilder().build();
        var ticketDto = new TicketTestDataBuilder().conId(1L).buildDto();
        var servicioCrearCliente = Mockito.mock(ServicioCrearCliente.class);
        var servicioCrearOrden = Mockito.mock(ServicioCrearOrden.class);
        var servicioGenerarTicket = Mockito.mock(ServicioGenerarTicket.class);
        var servicioCrearTicket = Mockito.mock(ServicioCrearTicket.class);
        Mockito.when(servicioCrearOrden.ejecutar(orden)).thenReturn(1L);
        Mockito.when(servicioGenerarTicket.ejecutar(orden, 1L, verificarAumentoFinDeSemana(8000))).thenReturn(ticket);
        Mockito.when(servicioCrearTicket.ejecutar(ticket)).thenReturn(ticketDto);
        var servicioProcesarOrden = new ServicioProcesarOrden(servicioCrearOrden, servicioCrearCliente, servicioGenerarTicket, servicioCrearTicket);

        // Act
        var dtoTicket = servicioProcesarOrden.ejecutar(orden, cliente);

        // Assert
        assertEquals(ticketDto, dtoTicket);
        Mockito.verify(servicioCrearCliente, Mockito.times(1)).ejecutar(cliente);
        Mockito.verify(servicioCrearOrden, Mockito.times(1)).ejecutar(orden);
        Mockito.verify(servicioGenerarTicket, Mockito.times(1)).ejecutar(orden, 1L, verificarAumentoFinDeSemana(8000));
        Mockito.verify(servicioCrearTicket, Mockito.times(1)).ejecutar(ticket);
    }

    public static float verificarAumentoFinDeSemana(float costo) {
        if(LocalDate.now().getDayOfWeek() == DayOfWeek.SATURDAY || LocalDate.now().getDayOfWeek() == DayOfWeek.SUNDAY) {
            costo += costo * (0.10);
        }

        return costo;
    }
}
