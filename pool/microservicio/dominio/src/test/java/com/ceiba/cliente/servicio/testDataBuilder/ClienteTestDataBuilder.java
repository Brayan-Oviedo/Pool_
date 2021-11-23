package com.ceiba.cliente.servicio.testDataBuilder;

import com.ceiba.cliente.modelo.entidad.Cliente;

import java.time.LocalDate;

public class ClienteTestDataBuilder {

    private String identificacion;
    private LocalDate fechaNacimiento;

    public ClienteTestDataBuilder() {
        this.identificacion = "123";
        this.fechaNacimiento = LocalDate.now().minusDays(1);
    }

    public ClienteTestDataBuilder conIdentificacion(String identificacion) {
        this.identificacion = identificacion;
        return this;
    }

    public ClienteTestDataBuilder conFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
        return this;
    }

    public Cliente build() { return new Cliente(identificacion, fechaNacimiento); }
}
