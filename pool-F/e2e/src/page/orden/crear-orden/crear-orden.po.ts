import { by, element } from "protractor";


export class CrearOrdenPage {
    private inputTiempoExtraOrden = element(by.id('tiempoExtraOrden'));
    private inputIdentificacionCliente = element(by.id('identificacionCliente'));
    private inputFechaNacimientoCliente = element(by.id('fechaNacimientoCliente'));
    private pickerFechaNacimientoCliente = element(by.id('pickerFechaNacimiento'));
    private botonCrearOrden = element(by.id('crearOrden'));
    private swal = element(by.className('swal2-title'));

    private botonAniosPicker = element(by.xpath('html/body/div[1]/div[2]/div/mat-datepicker-content/div[2]/mat-calendar/mat-calendar-header/div/div/button[1]'));
    private botonAtrasAniosPicker = element(by.xpath('html/body/div[1]/div[2]/div/mat-datepicker-content/div[2]/mat-calendar/mat-calendar-header/div/div/button[2]'));
    private botonAnioPicker = element(by.xpath('html/body/div[1]/div[2]/div/mat-datepicker-content/div[2]/mat-calendar/div/mat-multi-year-view/table/tbody/tr[3]/td[2]'));
    private botonMesPicker = element(by.xpath('html/body/div[1]/div[2]/div/mat-datepicker-content/div[2]/mat-calendar/div/mat-year-view/table/tbody/tr[3]/td[2]'));
    private botonDiaPicker = element(by.xpath('html/body/div[1]/div[2]/div/mat-datepicker-content/div[2]/mat-calendar/div/mat-month-view/table/tbody/tr[5]/td[4]'));


    async obtenerTextoInputTiempoExtra() {
        return this.inputTiempoExtraOrden.getText();
    }

    async asignarInputTiempoExtra(tiempoExtra: number) {
        await this.inputTiempoExtraOrden.clear();
        await this.inputTiempoExtraOrden.sendKeys(tiempoExtra);
    }

    async obtenerTextoInputIdentificacionCliente() {
        return this.inputIdentificacionCliente.getText();
    }

    async asignarInputIdentificacionCliente(identificacion: string) {
        await this.inputIdentificacionCliente.clear();
        await this.inputIdentificacionCliente.sendKeys(identificacion);
    }

    async obtenerTextoInputFechaNacimiento() {
        return this.inputFechaNacimientoCliente.getText();
    }

    async clickPickerFechaNacimiento() {
        await this.pickerFechaNacimientoCliente.click();
    }

    async clickAniosPickerFechaNacimiento() {
        await this.botonAniosPicker.click();
    }

    async clickBotonAtrasAniosPicker() {
        await this.botonAtrasAniosPicker.click();
    }

    async clickBotonAnioPicker() {
        await this.botonAnioPicker.click();
    }

    async clickBotonMesPicker() {
        await this.botonMesPicker.click();
    }

    async clickDiaPicker() {
        await this.botonDiaPicker.click();
    }

    async clickBotonCrearOrden() {
        await this.botonCrearOrden.click();
    }

    async obtenerTextoSwal(): Promise<string> {
        return await this.swal.getText();
    }
}