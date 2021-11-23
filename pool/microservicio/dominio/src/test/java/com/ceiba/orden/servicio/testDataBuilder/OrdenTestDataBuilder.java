package com.ceiba.orden.servicio.testDataBuilder;

import com.ceiba.orden.modelo.entidad.Orden;

public class OrdenTestDataBuilder {

    private Long id;
    private int tiempoExtra;
    private String clienteIdentificacion;

    public OrdenTestDataBuilder() {

        this.tiempoExtra = 1;
        this.clienteIdentificacion = "123";
    }

    public OrdenTestDataBuilder conId(Long id) {
        this.id = id;
        return this;
    }

    public OrdenTestDataBuilder conTiempoExtra(int tiempoExtra) {
        this.tiempoExtra = tiempoExtra;
        return this;
    }

    public OrdenTestDataBuilder conClienteIdentificacion(String clienteIdentificacion) {
        this.clienteIdentificacion = clienteIdentificacion;
        return this;
    }

    public Orden build() {
        return new Orden(id, tiempoExtra, clienteIdentificacion);
    }
}
