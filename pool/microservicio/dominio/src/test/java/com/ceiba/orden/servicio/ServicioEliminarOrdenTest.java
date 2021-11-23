package com.ceiba.orden.servicio;

import com.ceiba.orden.puerto.repositorio.RepositorioOrden;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ServicioEliminarOrdenTest {

    @Test
    @DisplayName("Deberia eliminar la orden llamando al repositorio")
    void deberiaEliminarLaOrdenLlamandoAlRepositorio() {

        // Arrange
        var repositorioOrden = Mockito.mock(RepositorioOrden.class);
        var servicioEliminarOrden = new ServicioEliminarOrden(repositorioOrden);

        // Act
        servicioEliminarOrden.ejecutar(1L);

        // Assert
        Mockito.verify(repositorioOrden, Mockito.times(1)).eliminar(1L);
    }
}
