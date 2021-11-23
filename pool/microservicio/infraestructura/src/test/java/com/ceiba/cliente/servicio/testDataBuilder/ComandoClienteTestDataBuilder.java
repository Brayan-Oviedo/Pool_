package com.ceiba.cliente.servicio.testDataBuilder;

import com.ceiba.cliente.comando.ComandoCliente;

import java.time.LocalDate;

public class ComandoClienteTestDataBuilder {

    private String identificacion;
    private LocalDate fechaNacimiento;

    public ComandoClienteTestDataBuilder() {
        identificacion = "123";
        fechaNacimiento = LocalDate.now().minusYears(1);
    }

    public ComandoClienteTestDataBuilder conIdentificacion(String identificacion) {
        this.identificacion = identificacion;
        return this;
    }

    public ComandoClienteTestDataBuilder conFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
        return this;
    }

    public ComandoCliente build() {
        return new ComandoCliente(identificacion, fechaNacimiento);
    }
}
