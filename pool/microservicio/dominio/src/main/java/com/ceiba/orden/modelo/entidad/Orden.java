package com.ceiba.orden.modelo.entidad;

import lombok.Getter;

import static com.ceiba.dominio.ValidadorArgumento.*;


@Getter
public class Orden {

    private static final String SE_DEBE_INGRESAR_LA_IDENTIFICACION_DEL_CLIENTE = "Se debe ingresar la identificación del cliente";
    private static final String SE_DEBE_INGRESAR_UN_TIEMPO_EXTRA_POSITIVO = "Se debe ingresar un tiempo extra positivo";
    private static final String EL_TIEMPO_EXTRA_MAXIMO_PERMITIDO_ES_DE_10_HORAS = "El tiempo extra maximo permitido es de 10 horas";

    private static final Long TIEMPO_EXTRA_MAXIMO = 10L;

    private Long id;
    private int tiempoExtra;
    private String clienteIdentificacion;

    public Orden(Long id, int tiempoExtra, String clienteIdentificacion) {
        validarObligatorio(clienteIdentificacion, SE_DEBE_INGRESAR_LA_IDENTIFICACION_DEL_CLIENTE);
        validarPositivoIncluidoElCero((double) tiempoExtra, SE_DEBE_INGRESAR_UN_TIEMPO_EXTRA_POSITIVO);
        validarMenor(Long.valueOf(tiempoExtra), TIEMPO_EXTRA_MAXIMO, EL_TIEMPO_EXTRA_MAXIMO_PERMITIDO_ES_DE_10_HORAS);

        this.id = id;
        this.tiempoExtra = tiempoExtra;
        this.clienteIdentificacion = clienteIdentificacion;
    }
}
