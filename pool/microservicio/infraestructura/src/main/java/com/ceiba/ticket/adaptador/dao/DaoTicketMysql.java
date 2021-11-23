package com.ceiba.ticket.adaptador.dao;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.ticket.modelo.dto.DtoTicket;
import com.ceiba.ticket.puerto.dao.DaoTicket;
import com.ceiba.ticket.puerto.repositorio.RepositorioTicket;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

@Component
public class DaoTicketMysql implements DaoTicket {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;
    private final RepositorioTicket repositorioTicket;

    @SqlStatement(namespace = "ticket", value = "obtenerPorId")
    private static String sqlObtenerPorId;

    public DaoTicketMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate, RepositorioTicket repositorioTicket) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
        this.repositorioTicket = repositorioTicket;
    }

    @Override
    public DtoTicket obtenerPorId(Long id) {
        var paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", id);
        var existe = repositorioTicket.existePorId(id);

        if(existe) {
            return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlObtenerPorId, paramSource, new MapeoTicket());
        }

        return null;
    }
}
