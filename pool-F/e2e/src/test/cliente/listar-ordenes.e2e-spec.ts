import { MenuPage } from "../../page/menu/menu.po";
import { AppPage } from "../../app.po";
import { NavbarPage } from "../../page/navbar/navbar.po";
import { CrearOrdenPage } from "../../page/orden/crear-orden/crear-orden.po";
import { TicketDialogPage } from "../../page/ticket/ticket-dialog.po";
import { ListarOrdenesPage } from "../../page/cliente/listar-ordenes.po";
import { OrdenTarjetaPage } from "../../page/orden/orden-tarjeta/orden-tarjeta.po";

describe('Listar ordenes', () => {

    let page: AppPage;
    let navbar: NavbarPage;
    let menu: MenuPage;
    let crearOrden: CrearOrdenPage;
    let ticketDialog: TicketDialogPage;
    let listarOrdenes: ListarOrdenesPage;
    let ordenTarjeta: OrdenTarjetaPage;

    const TIEMPO_EXTRA = 1;
    const IDENTIFICACION_CLIENTE = '1111111111';

    beforeEach(() => {
        page = new AppPage();
        navbar = new NavbarPage;
        menu = new MenuPage();
        crearOrden = new CrearOrdenPage();
        ticketDialog = new TicketDialogPage();
        listarOrdenes = new ListarOrdenesPage();
        ordenTarjeta = new OrdenTarjetaPage();
    });

    it('deberia mostrar la pagina actual', async () => {
        await page.navigateTo();
        await menu.clickMenu();
        await menu.clickMenuCliente();

        await menu.clickMenuListarOrdenes();

        expect(navbar.obtenerPaginaActual()).toBe('Cliente');
    });

    it('deberia listar ordenes del cliente', async () => {
        //Se crea una orden
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
        await ticketDialog.clickBotonCerrarTicket();

        await page.navigateTo();
        await menu.clickMenu();
        await menu.clickMenuCliente();
        await menu.clickMenuListarOrdenes();
        await navbar.asignarTextoInputBusqueda(IDENTIFICACION_CLIENTE);
        
        await navbar.clickBotonBusqueda();

        expect(listarOrdenes.contarOrdenes()).toBe(1);
        expect(ordenTarjeta.obtenerTextoOrdenId()).not.toBe('Orden');
        expect(ordenTarjeta.obtenerTextoTiempoExtra()).not.toBe('Tiempo extra:');
        expect(ordenTarjeta.obtenerTextoIdentificacionCliente()).not.toBe('');
        expect(ordenTarjeta.obtenerTextoFechaNacimientoCliente()).not.toBe('');
    });


    it('deberia eliminar una orden', async () => {
        await page.navigateTo();
        await menu.clickMenu();
        await menu.clickMenuCliente();
        await menu.clickMenuListarOrdenes();
        await navbar.asignarTextoInputBusqueda(IDENTIFICACION_CLIENTE);
        await navbar.clickBotonBusqueda();

        await ordenTarjeta.clickBotonEliminarOrden();

        expect(listarOrdenes.contarOrdenes()).toBe(0);
    })
});