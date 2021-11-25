package com.ceiba.cliente.entidad;

import com.ceiba.BasePrueba;
import com.ceiba.cliente.servicio.testDataBuilder.ClienteTestDataBuilder;
import com.ceiba.dominio.excepcion.ExcepcionLongitudValor;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ClienteTest {

    @Test
    @DisplayName("Deberia crear correctamente el cliente")
    void deberiaCrearCorrectamenteElCliente() {

        // Arrange
        var fechaNacimiento = LocalDate.now().minusDays(1);

        //Act
        var cliente = new ClienteTestDataBuilder()
                .conIdentificacion("1234567890")
                .conFechaNacimiento(fechaNacimiento)
                .build();

        // Assert
        assertEquals("1234567890", cliente.getIdentificacion());
        assertEquals(fechaNacimiento, cliente.getFechaNacimiento());

    }

    @Test
    void deberiaFallarSinIdentificacionDelCliente() {

        // Arrange
        var clienteTestDataBuilder = new ClienteTestDataBuilder().conIdentificacion(null);

        // Act - Assert
        BasePrueba.assertThrows(() -> {
                clienteTestDataBuilder.build();
            },
            ExcepcionValorObligatorio.class, "Se debe ingresar la identificación");
    }

    @Test
    void deberiaFallarConIdentificacionDelClienteDemasiadoCorta() {

        // Arrange
        var clienteTestDataBuilder = new ClienteTestDataBuilder().conIdentificacion("123");

        // Act - Assert
        BasePrueba.assertThrows(() -> {
                    clienteTestDataBuilder.build();
                },
                ExcepcionLongitudValor.class, "La identificación debe tener una longitud minima de 10 digitos");
    }

    @Test
    void deberiaFallarSinFechaDeNacimiento() {

        // Arrange
        var clienteTestDataBuilder = new ClienteTestDataBuilder().conFechaNacimiento(null);

        // Act - Assert
        BasePrueba.assertThrows(() -> {
                clienteTestDataBuilder.build();
            },
            ExcepcionValorObligatorio.class, "Se debe ingresar la fecha de nacimiento");
    }

    @Test
    void deberiaFallarConFechaDeNacimientoNoValida() {

        // Arrange
        var fechaNacimiento = LocalDate.now().plusDays(1);
        var clienteTestDataBuilder = new ClienteTestDataBuilder().conFechaNacimiento(fechaNacimiento);

        // Act - Assert
        BasePrueba.assertThrows(() -> {
                clienteTestDataBuilder.build();
            },
            ExcepcionValorInvalido.class, "Se debe ingresar una fecha de nacimiento valida");
    }
}
