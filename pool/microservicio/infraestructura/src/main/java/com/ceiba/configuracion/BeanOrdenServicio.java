package com.ceiba.configuracion;

import com.ceiba.cliente.servicio.ServicioCrearCliente;
import com.ceiba.orden.puerto.repositorio.RepositorioOrden;
import com.ceiba.orden.servicio.ServicioCrearOrden;
import com.ceiba.orden.servicio.ServicioEliminarOrden;
import com.ceiba.orden.servicio.ServicioProcesarOrden;
import com.ceiba.ticket.servicio.ServicioCrearTicket;
import com.ceiba.ticket.servicio.ServicioGenerarTicket;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanOrdenServicio {

    @Bean
    public ServicioProcesarOrden servicioProcesarOrden(
            ServicioCrearOrden servicioCrearOrden,
            ServicioCrearCliente servicioCrearCliente,
            ServicioGenerarTicket servicioGenerarTicket,
            ServicioCrearTicket servicioCrearTicket) {
        return new ServicioProcesarOrden(servicioCrearOrden, servicioCrearCliente, servicioGenerarTicket, servicioCrearTicket);
    }

    @Bean
    public ServicioCrearOrden servicioCrearOrden(RepositorioOrden repositorioOrden) {
        return new ServicioCrearOrden(repositorioOrden);
    }

    @Bean
    public ServicioEliminarOrden servicioEliminarOrden(RepositorioOrden repositorioOrden) {
        return new ServicioEliminarOrden(repositorioOrden);
    }
}
