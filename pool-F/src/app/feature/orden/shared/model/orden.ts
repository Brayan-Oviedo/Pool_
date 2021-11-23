import { Cliente } from "src/app/feature/cliente/shared/model/cliente";

export class Orden {

    id: number;
    tiempoExtra: number;
    cliente: Cliente;

    constructor(id: number, tiempoExtra: number, cliente: Cliente) {
        this.id = id;
        this.tiempoExtra = tiempoExtra;
        this.cliente = cliente;
    }
}
