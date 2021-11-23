export class Ticket {

    id: number;
    costoTotal: number;
    fechaVencimiento: string;

    constructor(id: number, costoTotal: number, fechaVencimiento: string) {
        this.id = id;
        this.costoTotal = costoTotal;
        this.fechaVencimiento = fechaVencimiento;
    }
}
