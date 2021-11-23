package com.ceiba.ticket.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.ticket.puerto.dao.DaoTicket;
import com.ceiba.ticket.puerto.repositorio.RepositorioTicket;
import com.ceiba.ticket.servicio.testDataBuilder.TicketTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ServicioCrearTicketTest {

    @Test
    @DisplayName("Deberia crear el ticket de manera correcta")
    void deberiaCrearElTicketDeManeraCorrecta() {

        // Arrange
        var ticket = new TicketTestDataBuilder().conId(1L).build();
        var ticketDto = new TicketTestDataBuilder().conId(1L).buildDto();
        var respositorioTicket = Mockito.mock(RepositorioTicket.class);
        var daoTicket = Mockito.mock(DaoTicket.class);
        Mockito.when(respositorioTicket.existePorId(Mockito.anyLong())).thenReturn(false);
        Mockito.when(respositorioTicket.crear(ticket)).thenReturn(1L);
        Mockito.when(daoTicket.obtenerPorId(1L)).thenReturn(ticketDto);
        var servicioCrearTicket = new ServicioCrearTicket(respositorioTicket, daoTicket);

        // Act
        var dtoTicket = servicioCrearTicket.ejecutar(ticket);

        // Assert
        assertEquals(ticketDto, dtoTicket);
        Mockito.verify(respositorioTicket, Mockito.times(1)).crear(ticket);
        Mockito.verify(daoTicket, Mockito.times(1)).obtenerPorId(1L);
    }

    @Test
    @DisplayName("Deberia lanzar una excepcion cuando se valide la exitencia del ticket")
    void deberiaLanzarUnaExcepconCuandoSeValideLaExistenciaDelTicket() {

        // Arrange
        var ticket = new TicketTestDataBuilder().conId(1L).build();
        var repositorioTicket = Mockito.mock(RepositorioTicket.class);
        var daoTicket = Mockito.mock(DaoTicket.class);
        Mockito.when(repositorioTicket.existePorId(Mockito.anyLong())).thenReturn(true);
        var servicioCrearTicket = new ServicioCrearTicket(repositorioTicket, daoTicket);

        // Act - Assert
        BasePrueba.assertThrows(() -> servicioCrearTicket.ejecutar(ticket), ExcepcionDuplicidad.class, "El ticket ya existe en el sistema");
    }
}
