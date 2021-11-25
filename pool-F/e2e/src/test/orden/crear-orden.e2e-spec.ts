import { CrearOrdenPage } from "../../page/orden/crear-orden/crear-orden.po";
import { AppPage } from "../../app.po";
import { MenuPage } from "../../page/menu/menu.po";
import { TicketDialogPage } from "../../page/ticket/ticket-dialog.po";
import { NavbarPage } from "../../page/navbar/navbar.po";

describe('Crear orden', () => {

    let page: AppPage;
    let menu: MenuPage;
    let navbar: NavbarPage;
    let crearOrden: CrearOrdenPage;
    let ticketDialogPage: TicketDialogPage;

    const TIEMPO_EXTRA = 1;
    const IDENTIFICACION_CLIENTE = '1234567890';
    const ORDEN_CREADA = 'Orden creada';

    beforeEach(() => {
        page = new AppPage();
        menu = new MenuPage();
        navbar = new NavbarPage();
        crearOrden = new CrearOrdenPage();
        ticketDialogPage = new TicketDialogPage();
    });

    it('deberia mostrar la pagina actual', async () => {
        await page.navigateTo();
        await menu.clickMenu();
        await menu.clickMenuOrden();

        await menu.clickMenuCrearOrden();

        expect(navbar.obtenerPaginaActual()).toBe('Orden');
    });

    it('deberia crear la orden', async () => {
        await page.navigateTo();
        await menu.clickMenu();
        await menu.clickMenuOrden();
        await menu.clickMenuCrearOrden();
        await crearOrden.asignarInputTiempoExtra(TIEMPO_EXTRA);
        await crearOrden.asignarInputIdentificacionCliente(IDENTIFICACION_CLIENTE);
        await crearOrden.clickPickerFechaNacimiento();
        await crearOrden.clickAniosPickerFechaNacimiento();
        await crearOrden.clickBotonAtrasAniosPicker();
        await crearOrden.clickBotonAnioPicker();
        await crearOrden.clickBotonMesPicker();
        await crearOrden.clickDiaPicker();

        await crearOrden.clickBotonCrearOrden();
        const alerta = await crearOrden.obtenerTextoSwal();
        expect(alerta).toEqual(ORDEN_CREADA);
        expect(ticketDialogPage.getTextoTicketId()).not.toBe('Ticket');
        expect(ticketDialogPage.getTextoTicketCostoTotal()).not.toBe('$');
        expect(ticketDialogPage.getTextoTicketFechaVencimiento()).not.toBe('Vence:');

        await ticketDialogPage.clickBotonCerrarTicket();
        expect(crearOrden.obtenerTextoInputTiempoExtra()).toBe('');
        expect(crearOrden.obtenerTextoInputIdentificacionCliente()).toBe('');
    });
});