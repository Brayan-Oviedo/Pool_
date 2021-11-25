import { HttpResponse } from "@angular/common/http";
import { HttpClientTestingModule, HttpTestingController } from "@angular/common/http/testing";
import { TestBed } from "@angular/core/testing";
import { Cliente } from "@cliente/shared/model/cliente";
import { HttpService } from "@core/services/http.service";
import * as moment from "moment";
import { Ticket } from "src/app/feature/ticket/shared/model/ticket";
import { environment } from "src/environments/environment";
import { Orden } from "../model/orden";
import { OrdenService } from "./orden.service";


describe('OrdenService', () => {
    let httpMock: HttpTestingController;
    let service: OrdenService;
    const apiEndpointOrden = `${environment.endpoint}/ordenes`;

    beforeEach(() => {
        const injector = TestBed.configureTestingModule({
            imports: [HttpClientTestingModule],
            providers: [OrdenService, HttpService]
        });
    
        httpMock = injector.inject(HttpTestingController);
        service = injector.inject(OrdenService);
    });

    it('should be created', () => {
        const ordenService = TestBed.inject(OrdenService);
        expect(ordenService).toBeTruthy(); 
    });

    it('deberia crear una orden', () => {
        const ticket = new Ticket(1, 1000, moment().toDate().toString());
        const cliente = new Cliente('123', '2001-01-01');
        const orden = new Orden(1, 1, cliente);
        service.crear(orden).subscribe(respuesta => {
            expect(respuesta).toEqual(ticket);
        });
        const req = httpMock.expectOne(apiEndpointOrden);
        expect(req.request.method).toBe('POST');
        req.flush(ticket);
    });

    it('deberia eliminar una orden', () => {
        const cliente = new Cliente('123', '2001-01-01');
        const orden = new Orden(1, 1, cliente);
        service.eliminar(orden).subscribe(resultado => {
            expect(resultado).toEqual(true);
        });
        const req = httpMock.expectOne(`${apiEndpointOrden}/1`);
        expect(req.request.method).toBe('DELETE');
        req.event(new HttpResponse<boolean>({body: true}));
    });
 
});
