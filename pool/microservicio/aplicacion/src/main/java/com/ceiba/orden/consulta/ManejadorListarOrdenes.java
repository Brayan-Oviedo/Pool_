package com.ceiba.orden.consulta;


import com.ceiba.orden.modelo.dto.DtoOrden;
import com.ceiba.orden.puerto.dao.DaoOrden;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManejadorListarOrdenes {

    private final DaoOrden daoOrden;

    public ManejadorListarOrdenes(DaoOrden daoOrden) {
        this.daoOrden = daoOrden;
    }

    public List<DtoOrden> ejecutar(String identificacionCliente) {
        return this.daoOrden.listarPorCliente(identificacionCliente);
    }
}
