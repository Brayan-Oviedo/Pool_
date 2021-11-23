package com.ceiba.cliente.servicio;

import com.ceiba.cliente.puerto.repositorio.RepositorioCliente;
import com.ceiba.cliente.servicio.testDataBuilder.ClienteTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ServicioCrearClienteTest {

    @Test
    @DisplayName("Deberia crear el cliente de manera correcta")
    void deberiaCrearElClienteDeManeraCorrecta() {

        // Arrange
        var cliente = new ClienteTestDataBuilder().build();
        var repositorioCliente = Mockito.mock(RepositorioCliente.class);
        Mockito.when(repositorioCliente.existePorIdentificacion(Mockito.anyString())).thenReturn(false);
        Mockito.when(repositorioCliente.crear(cliente)).thenReturn(1L);
        ServicioCrearCliente servicioCrearCliente = new ServicioCrearCliente(repositorioCliente);

        // Act
        servicioCrearCliente.ejecutar(cliente);

        // Assert
        Mockito.verify(repositorioCliente, Mockito.times(1)).crear(cliente);
    }

    @Test
    @DisplayName("Deberia no crear el cliente")
    void deberiaNoCrearElCliente() {

        // Arrange
        var cliente = new ClienteTestDataBuilder().build();
        var repositorioCliente = Mockito.mock(RepositorioCliente.class);
        Mockito.when(repositorioCliente.existePorIdentificacion(Mockito.anyString())).thenReturn(true);
        Mockito.when(repositorioCliente.crear(cliente)).thenReturn(1L);
        ServicioCrearCliente servicioCrearCliente = new ServicioCrearCliente(repositorioCliente);

        // Act
        servicioCrearCliente.ejecutar(cliente);

        // Assert
        Mockito.verify(repositorioCliente, Mockito.times(1)).existePorIdentificacion(Mockito.anyString());
        Mockito.verify(repositorioCliente, Mockito.times(0)).crear(cliente);
    }
}
