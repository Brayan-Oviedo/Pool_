package com.ceiba.cliente.modelo.entidad;


import lombok.Getter;

import java.time.LocalDate;

import static com.ceiba.dominio.ValidadorArgumento.*;

@Getter
public class Cliente {

    private static final String SE_DEBE_INGRESAR_LA_IDENTIFICACION = "Se debe ingresar la identificación";
    private static final String LA_IDENTIFICACION_DEBE_TENER_UNA_LONGITUD_MINIMA_DE_10_DIGITOS = "La identificación debe tener una longitud minima de 10 digitos";
    private static final String SE_DEBE_INGRESAR_LA_FECHA_DE_NACIMIENTO = "Se debe ingresar la fecha de nacimiento";
    private static final String SE_DEBE_INGRESAR_UNA_FECHA_DE_NACIMIENTO_VALIDA = "Se debe ingresar una fecha de nacimiento valida";


    private String identificacion;
    private LocalDate fechaNacimiento;

    public Cliente(String identificacion, LocalDate fechaNacimiento) {
        validarObligatorio(identificacion, SE_DEBE_INGRESAR_LA_IDENTIFICACION);
        validarLongitudMinima(identificacion, 10, LA_IDENTIFICACION_DEBE_TENER_UNA_LONGITUD_MINIMA_DE_10_DIGITOS);
        validarObligatorio(fechaNacimiento, SE_DEBE_INGRESAR_LA_FECHA_DE_NACIMIENTO);
        validarMenor(fechaNacimiento, LocalDate.now(), SE_DEBE_INGRESAR_UNA_FECHA_DE_NACIMIENTO_VALIDA);

        this.identificacion = identificacion;
        this.fechaNacimiento = fechaNacimiento;
    }
}
