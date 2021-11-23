package com.ceiba.orden.adaptador.dao;


import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.orden.modelo.dto.DtoOrden;
import com.ceiba.orden.puerto.dao.DaoOrden;
import com.ceiba.orden.puerto.repositorio.RepositorioOrden;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DaoOrdenMysql implements DaoOrden {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;
    private final RepositorioOrden repositorioOrden;

    @SqlStatement(namespace = "orden", value = "obtenerPorId")
    private static String sqlBuscarPorId;

    @SqlStatement(namespace = "orden", value = "listarPorCliente")
    private static String sqlListarPorCliente;


    public DaoOrdenMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate, RepositorioOrden repositorioOrden) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
        this.repositorioOrden = repositorioOrden;
    }

    @Override
    public List<DtoOrden> listarPorCliente(String identificacion) {
        var paramSource = new MapSqlParameterSource();
        paramSource.addValue("identificacion", identificacion);

        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlListarPorCliente, paramSource, new MapeoOrden());
    }

    @Override
    public DtoOrden obtenerPorId(Long id) {
        var paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", id);
        var existe = this.repositorioOrden.existePorId(id);

        if(existe) {
            return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlBuscarPorId, paramSource, new MapeoOrden());
        }

        return null;
    }
}
