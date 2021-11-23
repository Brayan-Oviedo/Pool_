package com.ceiba.orden.entidad;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.orden.servicio.testDataBuilder.OrdenTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrdenTest {

    @Test
    @DisplayName("Deberia crear correctamente la orden")
    void deberiaCrearCorrectamenteLaOrden() {

        // Arrange - Act
        var orden = new OrdenTestDataBuilder()
                .conId(1L)
                .conTiempoExtra(1)
                .conClienteIdentificacion("123")
                .build();

        // Assert
        assertEquals(1L, orden.getId());
        assertEquals(1, orden.getTiempoExtra());
        assertEquals("123", orden.getClienteIdentificacion());

    }

    @Test
    void deberiaFallarSinLaIdentificacionDelCliente() {

        // Arrange
        var ordenTestDataBuilder = new OrdenTestDataBuilder().conId(1L).conClienteIdentificacion(null);

        // Act - Assert
        BasePrueba.assertThrows(() -> {
                    ordenTestDataBuilder.build();
                },
                ExcepcionValorObligatorio.class, "Se debe ingresar la identificación del cliente");
    }

    @Test
    void deberiaFallarConTiempoExtraNegativo() {

        // Arrange
        var ordenTestDataBuilder = new OrdenTestDataBuilder().conId(1L).conTiempoExtra(-1);

        // Act - Assert
        BasePrueba.assertThrows(() -> {
                    ordenTestDataBuilder.build();
                },
                ExcepcionValorInvalido.class, "Se debe ingresar un tiempo extra positivo");
    }

    @Test
    void deberiaFallarConTiempoMayorAlPermitido() {

        // Arrange
        var ordenTestDataBuilder = new OrdenTestDataBuilder().conId(1L).conTiempoExtra(11);

        // Act - Assert
        BasePrueba.assertThrows(() -> {
                    ordenTestDataBuilder.build();
                },
                ExcepcionValorInvalido.class, "El tiempo extra maximo permitido es de 10 horas");
    }
}
