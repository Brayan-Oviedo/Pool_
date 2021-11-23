package com.ceiba.ticket.adaptador.dao;

import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.ticket.modelo.dto.DtoTicket;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MapeoTicket implements RowMapper<DtoTicket>, MapperResult {

    @Override
    public DtoTicket mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        var id = resultSet.getLong("id");
        var costoTotal = resultSet.getFloat("costo_total");
        var fechaVencimiento = extraerLocalDateTime(resultSet, "fecha_vencimiento");

        return new DtoTicket(id, costoTotal, fechaVencimiento);
    }
}
