import { by, element } from "protractor";


export class TicketDialogPage {
    ticketId = element(by.id('ticketId'));
    ticketCostoTotal = element(by.id('ticketCostoTotal'));
    ticketFechaVencimiento = element(by.id('ticketFechaVencimiento'));
    botonCerrarTicket = element(by.id('cerrarTicket'));

    async getTextoTicketId() {
        return this.ticketId.getText();
    }

    async getTextoTicketCostoTotal() {
        return this.ticketCostoTotal.getText();
    }

    async getTextoTicketFechaVencimiento() {
        return this.ticketFechaVencimiento.getText();
    }

    async clickBotonCerrarTicket() {
        await this.botonCerrarTicket.click();
    }
}