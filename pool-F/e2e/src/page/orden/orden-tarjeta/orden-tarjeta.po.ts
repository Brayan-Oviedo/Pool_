import { by, element } from "protractor";


export class OrdenTarjetaPage {
    
    private ordenId = element(by.id('ordenId'));
    private tiempoExtra = element(by.id('tiempoExtra'));
    private identificacionCliente = element(by.id('identificacionCliente'));
    private fechaNacimientoCliente = element(by.id('fechaNacimientoCliente'));
    private botonEliminarOrden = element(by.id('eliminarOrden'));

    async obtenerTextoOrdenId() {
        return this.ordenId.getText();
    }    

    async obtenerTextoTiempoExtra() {
        return this.tiempoExtra.getText();
    }

    async obtenerTextoIdentificacionCliente() {
        return this.identificacionCliente.getText();
    }

    async obtenerTextoFechaNacimientoCliente() {
        return this.fechaNacimientoCliente.getText();
    }

    async clickBotonEliminarOrden() {
        await this.botonEliminarOrden.click();
    }
}