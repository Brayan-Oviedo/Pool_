import { by, element } from "protractor";


export class MenuPage {
    menu = element(by.id('menu'));
    menuOrden = element(by.id('menuOrden'));
    menuCliente = element(by.id('menuCliente'));
    menuCrearOrden = element(by.id('menuCrearOrden'));
    menuListarOrdenes = element(by.id('menuListarOrdenes'));

    async clickMenu() {
        await this.menu.click();
    }

    async clickMenuOrden() {
        await this.menuOrden.click();
    }

    async clickMenuCrearOrden() {
        await this.menuCrearOrden.click();
    }

    async clickMenuCliente() {
        await this.menuCliente.click();
    }

    async clickMenuListarOrdenes() {
        await this.menuListarOrdenes.click();
    }
}