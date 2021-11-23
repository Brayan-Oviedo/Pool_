package com.ceiba.orden.servicio.testDataBuilder;

import com.ceiba.cliente.comando.ComandoCliente;
import com.ceiba.cliente.servicio.testDataBuilder.ComandoClienteTestDataBuilder;
import com.ceiba.orden.comando.ComandoOrden;

public class ComandoOrdenTestDataBuilder {

    private Long id;
    private ComandoCliente cliente;
    private int tiempoExtra;

    public ComandoOrdenTestDataBuilder() {
        cliente = new ComandoClienteTestDataBuilder().build();
        tiempoExtra = 1;
    }

    public ComandoOrdenTestDataBuilder conCliente(ComandoCliente comandoCliente) {
        this.cliente = comandoCliente;
        return this;
    }

    public ComandoOrdenTestDataBuilder conTiempoExtra(int tiempoExtra) {
        this.tiempoExtra = tiempoExtra;
        return this;
    }

    public ComandoOrden build() {
        return new ComandoOrden(id, cliente, tiempoExtra);
    }
}
