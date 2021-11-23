import { HttpClientTestingModule, HttpTestingController } from "@angular/common/http/testing";
import { TestBed } from "@angular/core/testing";
import { HttpService } from "@core/services/http.service";
import { Orden } from "@orden/shared/model/orden";
import { environment } from "src/environments/environment";
import { Cliente } from "../model/cliente";
import { ClienteService } from "./cliente.service";


describe('ClienteService', () => {
    let httpMock: HttpTestingController;
    let service: ClienteService;
    const apiEndpointOrdenesClienteConsulta = `${environment.endpoint}/ordenes/cliente`;

    beforeEach(() => {
        const injector = TestBed.configureTestingModule({
            imports: [HttpClientTestingModule],
            providers: [ClienteService, HttpService]
        });
        httpMock = injector.inject(HttpTestingController);
        service = TestBed.inject(ClienteService);
    });

    it('should be created', () => {
        const clienteService: ClienteService = TestBed.inject(ClienteService);
        expect(clienteService).toBeTruthy();
    });

    it('deberia listar las ordenes del cliente', () => {
        const cliente = new Cliente('123', '2001-01-01');
        const ordenes = [
            new Orden(1, 1, cliente), new Orden(2, 2, cliente)
        ];
        service.obtenerOrdenesPorCliente(cliente.identificacion).subscribe(lista => {
            expect(lista.length).toBe(2);
            expect(lista).toEqual(ordenes);
        });
        const req = httpMock.expectOne(`${apiEndpointOrdenesClienteConsulta}/123`);
        expect(req.request.method).toBe('GET');
        req.flush(ordenes);
    });
  
});
