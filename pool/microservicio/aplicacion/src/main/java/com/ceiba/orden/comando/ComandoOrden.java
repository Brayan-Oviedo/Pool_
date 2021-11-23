package com.ceiba.orden.comando;


import com.ceiba.cliente.comando.ComandoCliente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComandoOrden {

    private Long id;
    private ComandoCliente cliente;
    private int tiempoExtra;
}
