package com.ceiba.orden.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.orden.puerto.repositorio.RepositorioOrden;
import com.ceiba.orden.servicio.testDataBuilder.OrdenTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ServicioCrearOrdenTest {

    @Test
    @DisplayName("Deberia crear la orden de manera correcta")
    void deberiaCrearLaOrdenDeManerCorrecta() {

        // Arrange
        var orden = new OrdenTestDataBuilder().conId(1L).build();
        var repositorioOrden = Mockito.mock(RepositorioOrden.class);
        Mockito.when(repositorioOrden.existePorId(Mockito.anyLong())).thenReturn(false);
        Mockito.when(repositorioOrden.crear(orden)).thenReturn(1L);
        var servicioCrearOrden = new ServicioCrearOrden(repositorioOrden);

        // Act
        var ordenId = servicioCrearOrden.ejecutar(orden);

        // Assert
        assertEquals(1L, ordenId);
        Mockito.verify(repositorioOrden, Mockito.times(1)).crear(orden);
    }

    @Test
    @DisplayName("Deberia lanzar una excepcion cuando se valide la exitencia de la orden")
    void deberiaLanzarUnaExcepconCuandoSeValideLaExistenciaDeLaOrden() {

        // Arrange
        var orden = new OrdenTestDataBuilder().conId(1L).build();
        var repositorioOrden = Mockito.mock(RepositorioOrden.class);
        Mockito.when(repositorioOrden.existePorId(Mockito.anyLong())).thenReturn(true);
        var servicioCrearOrden = new ServicioCrearOrden(repositorioOrden);

        // Act - Assert
        BasePrueba.assertThrows(() -> servicioCrearOrden.ejecutar(orden), ExcepcionDuplicidad.class, "La orden ya existe en el sistema");
    }
}
