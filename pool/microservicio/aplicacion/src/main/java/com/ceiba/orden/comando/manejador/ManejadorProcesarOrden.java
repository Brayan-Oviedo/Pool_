package com.ceiba.orden.comando.manejador;


import com.ceiba.cliente.comando.fabrica.FabricaCliente;
import com.ceiba.manejador.ManejadorComandoRespuesta;
import com.ceiba.orden.comando.ComandoOrden;
import com.ceiba.orden.comando.fabrica.FabricaOrden;
import com.ceiba.orden.servicio.ServicioProcesarOrden;
import com.ceiba.ticket.modelo.dto.DtoTicket;
import org.springframework.stereotype.Component;

@Component
public class ManejadorProcesarOrden implements ManejadorComandoRespuesta<ComandoOrden, DtoTicket> {

    private FabricaOrden fabricaOrden;
    private FabricaCliente fabricaCliente;
    private ServicioProcesarOrden servicioProcesarOrden;

    public ManejadorProcesarOrden(FabricaOrden fabricaOrden, FabricaCliente fabricaCliente, ServicioProcesarOrden servicioProcesarOrden) {
        this.fabricaOrden = fabricaOrden;
        this.fabricaCliente = fabricaCliente;
        this.servicioProcesarOrden = servicioProcesarOrden;
    }

    public DtoTicket ejecutar(ComandoOrden comandoOrden) {
        var orden = this.fabricaOrden.crear(comandoOrden);
        var cliente = this.fabricaCliente.crear(comandoOrden.getCliente());

        return this.servicioProcesarOrden.ejecutar(orden, cliente);
    }
}
