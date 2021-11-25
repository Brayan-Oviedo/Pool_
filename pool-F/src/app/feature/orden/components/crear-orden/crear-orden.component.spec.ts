import { CommonModule } from "@angular/common";
import { HttpClientTestingModule } from "@angular/common/http/testing";
import { ComponentFixture, TestBed, waitForAsync } from "@angular/core/testing";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { MatDialog } from "@angular/material/dialog";
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { MaterialModule } from "@core/material.module";
import { HttpService } from "@core/services/http.service";
import { SwalService } from "@core/services/swal.service";
import { OrdenService } from "@orden/shared/service/orden.service";
import { FechaService } from "@shared/services/fecha.service";
import { Ticket } from "@ticket/shared/model/ticket";
import { of } from "rxjs";
import { CrearOrdenComponent } from "./crear-orden.component";


describe('CrearOrdenComponent', () => {
    let component: CrearOrdenComponent;
    let fixture: ComponentFixture<CrearOrdenComponent>;
    let ordenService: OrdenService;
    let matDialog: MatDialog;
    let swalService: SwalService;
    const ticket: Ticket = new Ticket(1, 1000, '2001-01-01 HH:mm:ss');

    beforeEach(waitForAsync(() => {
        TestBed.configureTestingModule({
            declarations: [CrearOrdenComponent],
            imports: [
                CommonModule,
                MaterialModule,
                FormsModule,
                ReactiveFormsModule,
                HttpClientTestingModule,
                BrowserAnimationsModule
            ],
            providers: [OrdenService, FechaService, HttpService, SwalService],
        })
            .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(CrearOrdenComponent);
        component = fixture.componentInstance;
        ordenService = TestBed.inject(OrdenService);
        matDialog = TestBed.inject(MatDialog);
        swalService = TestBed.inject(SwalService);
        spyOn(ordenService, 'crear').and.returnValue(
            of(ticket)
        );
        spyOn(matDialog, 'open');
        spyOn(swalService, 'success');
        fixture.detectChanges();
    });

    it('should be created', () => {
        expect(component).toBeTruthy();
    });

    it('formularios son invalidos cuando estan vacios', () => {
        expect(component.ordenForm.valid).toBeTruthy();
        expect(component.clienteForm.valid).toBeFalsy();
    });

    it('deberia registrar una orden', () => {
        expect(component.ordenForm.valid).toBeTruthy();
        expect(component.clienteForm.valid).toBeFalsy();
        component.ordenForm.controls.tiempoExtra.setValue(0);
        component.clienteForm.controls.identificacion.setValue('1234567890');
        component.clienteForm.controls.fechaNacimiento.setValue('2001-01-01');
        expect(component.clienteForm.valid).toBeTruthy();
        expect(component.ordenForm.valid).toBeTruthy();

        component.crear();

        expect(component.orden.tiempoExtra).toBe(0);
        expect(component.orden.cliente.identificacion).toBe('1234567890');
        expect(component.orden.cliente.fechaNacimiento).toBe('2001-01-01');
        expect(ordenService.crear).toHaveBeenCalledTimes(1);
        expect(matDialog.open).toHaveBeenCalledTimes(1);
        expect(swalService.success).toHaveBeenCalledTimes(1);
    });
})
