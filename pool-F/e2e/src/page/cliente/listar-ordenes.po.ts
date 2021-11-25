import { by, element } from "protractor";

export class ListarOrdenesPage {

    listaOrdenes = element.all(by.id('listaOrdenes'));

    async contarOrdenes() {
        return this.listaOrdenes.count();
    }
}