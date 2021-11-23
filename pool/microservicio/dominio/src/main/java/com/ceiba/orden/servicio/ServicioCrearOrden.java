package com.ceiba.orden.servicio;

import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.orden.modelo.entidad.Orden;
import com.ceiba.orden.puerto.repositorio.RepositorioOrden;

public class ServicioCrearOrden {

    private static final String LA_ORDEN_YA_EXISTE_EN_EL_SISTEMA = "La orden ya existe en el sistema";

    private final RepositorioOrden repositorioOrden;

    public ServicioCrearOrden(RepositorioOrden repositorioOrden) {
        this.repositorioOrden = repositorioOrden;
    }

    public Long ejecutar(Orden orden) {
        validarExistenciaPrevia(orden);
        return this.repositorioOrden.crear(orden);
    }

    private void validarExistenciaPrevia(Orden orden) {
        boolean existe = this.repositorioOrden.existePorId(orden.getId());
        if(existe) {
            throw new ExcepcionDuplicidad(LA_ORDEN_YA_EXISTE_EN_EL_SISTEMA);
        }
    }
}
