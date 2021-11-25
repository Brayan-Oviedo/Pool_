import { by, element } from 'protractor';

export class NavbarPage {
    private paginaActual = element(by.id('paginaActual'));
    private inputBusqueda = element(by.id('itemBusqueda'));
    private botonBusqueda = element(by.id('botonBusqueda'));

    async obtenerPaginaActual() {
        return this.paginaActual.getText();
    }

    async clickBotonBusqueda() {
        return this.botonBusqueda.click();
    }

    async obtenerTextoInputBusqueda() {
        return this.inputBusqueda.getText();
    }

    async asignarTextoInputBusqueda(texto: string) {
        return this.inputBusqueda.sendKeys(texto);
    }
}
