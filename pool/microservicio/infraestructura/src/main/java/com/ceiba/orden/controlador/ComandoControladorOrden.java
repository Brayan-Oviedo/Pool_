package com.ceiba.orden.controlador;

import com.ceiba.orden.comando.ComandoOrden;
import com.ceiba.orden.comando.manejador.ManejadorEliminarOrden;
import com.ceiba.orden.comando.manejador.ManejadorProcesarOrden;
import com.ceiba.ticket.modelo.dto.DtoTicket;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ordenes")
@Api(tags = { "Controlador comando orden" })
public class ComandoControladorOrden {

    private final ManejadorProcesarOrden manejadorProcesarOrden;
    private final ManejadorEliminarOrden manejadorEliminarOrden;

    @Autowired
    public ComandoControladorOrden(ManejadorProcesarOrden manejadorProcesarOrden, ManejadorEliminarOrden manejadorEliminarOrden) {
        this.manejadorProcesarOrden = manejadorProcesarOrden;
        this.manejadorEliminarOrden = manejadorEliminarOrden;
    }

    @PostMapping
    @ApiOperation("Procesar Orden")
    public DtoTicket procesar(@RequestBody ComandoOrden comandoOrden) {
        return manejadorProcesarOrden.ejecutar(comandoOrden);
    }

    @DeleteMapping(value="/{id}")
    @ApiOperation("Eliminar Orden")
    public void eliminar(@PathVariable Long id) { manejadorEliminarOrden.ejecutar(id); }

}
