package com.ceiba.orden.comando.manejador;

import com.ceiba.manejador.ManejadorComando;
import com.ceiba.orden.servicio.ServicioEliminarOrden;
import org.springframework.stereotype.Component;

@Component
public class ManejadorEliminarOrden implements ManejadorComando<Long> {

    private final ServicioEliminarOrden servicioEliminarOrden;

    public ManejadorEliminarOrden(ServicioEliminarOrden servicioEliminarOrden) {
        this.servicioEliminarOrden = servicioEliminarOrden;
    }

    public void ejecutar(Long idOrden) { this.servicioEliminarOrden.ejecutar(idOrden); }
}
