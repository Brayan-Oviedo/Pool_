package com.ceiba.orden.adaptador.dao;

import com.ceiba.cliente.modelo.dto.DtoCliente;
import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.orden.modelo.dto.DtoOrden;
import com.ceiba.ticket.modelo.dto.DtoTicket;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class MapeoOrden implements RowMapper<DtoOrden>, MapperResult {

    @Override
    public DtoOrden mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        var id = resultSet.getLong("id");
        var tiempoExtra = resultSet.getInt("tiempo_extra");

        // Ticket
        var ticketId = resultSet.getLong("ticket_id");
        var costoTotal = resultSet.getFloat("costo_total");
        var fechaVencimiento = extraerLocalDateTime(resultSet, "fecha_vencimiento");

        // Cliente
        var identificacion = resultSet.getString("cliente_identificacion");
        var fechaNacimiento = extraerLocalDate(resultSet, "fecha_nacimiento");


        var ticket = new DtoTicket(ticketId, costoTotal, fechaVencimiento);
        var cliente = new DtoCliente(identificacion, fechaNacimiento);

        return new DtoOrden(id, tiempoExtra, ticket, cliente);
    }
}
