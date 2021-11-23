package com.ceiba.cliente.modelo.entidad;


import lombok.Getter;

import java.time.LocalDate;

import static com.ceiba.dominio.ValidadorArgumento.validarMenor;
import static com.ceiba.dominio.ValidadorArgumento.validarObligatorio;

@Getter
public class Cliente {

    private static final String SE_DEBE_INGRESAR_LA_IDENTIFICACION = "Se debe ingresar la identificación";
    private static final String SE_DEBE_INGRESAR_LA_FECHA_DE_NACIMIENTO = "Se debe ingresar la fecha de nacimiento";
    private static final String SE_DEBE_INGRESAR_UNA_FECHA_DE_NACIMIENTO_VALIDA = "Se debe ingresar una fecha de nacimiento valida";


    private String identificacion;
    private LocalDate fechaNacimiento;

    public Cliente(String identificacion, LocalDate fechaNacimiento) {
        validarObligatorio(identificacion, SE_DEBE_INGRESAR_LA_IDENTIFICACION);
        validarObligatorio(fechaNacimiento, SE_DEBE_INGRESAR_LA_FECHA_DE_NACIMIENTO);
        validarMenor(fechaNacimiento, LocalDate.now(), SE_DEBE_INGRESAR_UNA_FECHA_DE_NACIMIENTO_VALIDA);

        this.identificacion = identificacion;
        this.fechaNacimiento = fechaNacimiento;
    }
}
