package com.ceiba.orden.controlador;

import com.ceiba.orden.consulta.ManejadorListarOrdenes;
import com.ceiba.orden.modelo.dto.DtoOrden;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ordenes")
@Api(tags = "Controlador consulta orden")
public class ConsultaControladorOrden {

    private final ManejadorListarOrdenes manejadorListarOrdenes;

    public ConsultaControladorOrden(ManejadorListarOrdenes manejadorListarOrdenes) {
        this.manejadorListarOrdenes = manejadorListarOrdenes;
    }


    @GetMapping(value="/cliente/{identificacion}")
    @ApiOperation("Listar Ordenes")
    public List<DtoOrden> listar(@PathVariable String identificacion) {
        return manejadorListarOrdenes.ejecutar(identificacion);
    }
}
