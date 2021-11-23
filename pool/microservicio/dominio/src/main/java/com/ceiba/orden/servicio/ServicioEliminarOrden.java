package com.ceiba.orden.servicio;

import com.ceiba.orden.puerto.repositorio.RepositorioOrden;

public class ServicioEliminarOrden {

    private final RepositorioOrden repositorioOrden;

    public ServicioEliminarOrden(RepositorioOrden repositorioOrden) {
        this.repositorioOrden = repositorioOrden;
    }

    public void ejecutar(Long id) { this.repositorioOrden.eliminar(id); }
}
