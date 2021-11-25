import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { Cliente } from '@cliente/shared/model/cliente';
import { ClienteService } from '@cliente/shared/service/cliente.service';
import { MaterialModule } from '@core/material.module';
import { HttpService } from '@core/services/http.service';
import { SwalService } from '@core/services/swal.service';
import { Orden } from '@orden/shared/model/orden';
import { OrdenService } from '@orden/shared/service/orden.service';
import { MenuComponent } from '@shared/components/menu/menu.component';
import { NavbarComponent } from '@shared/components/navbar/navbar.component';
import { of } from 'rxjs';
import { OrdenTarjetaComponent } from 'src/app/feature/shared/components/orden-tarjeta/orden-tarjeta.component';

import { ListarOrdenesComponent } from './listar-ordenes.component';

describe('ListarOrdenesComponent', () => {
  let component: ListarOrdenesComponent;
  let fixture: ComponentFixture<ListarOrdenesComponent>;
  let ordenService: OrdenService;
  let clienteService: ClienteService;
  const cliente: Cliente = new Cliente('123', '2001-01-01');
  const ordenes: Orden[] = [new Orden(1, 1, cliente), new Orden(2, 2, cliente)];

  beforeEach(async () => {
    TestBed.configureTestingModule({
      declarations: [ListarOrdenesComponent, OrdenTarjetaComponent, NavbarComponent, MenuComponent],
      imports: [
        CommonModule,
        FormsModule,
        MaterialModule,
        HttpClientModule,
        BrowserAnimationsModule
      ],
      providers: [
        OrdenService,
        ClienteService,
        HttpService,
        SwalService
      ]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ListarOrdenesComponent);
    component = fixture.componentInstance;
    ordenService = TestBed.inject(OrdenService);
    clienteService = TestBed.inject(ClienteService);

    
    spyOn(ordenService, 'eliminar').and.returnValue(
      of(true)
    );

    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('deberia obtener las ordenes del cliente', () => {
    spyOn(clienteService, 'obtenerOrdenesPorCliente').and.returnValue(
      of(ordenes)
    );

    component.obtenerOrdenes(cliente.identificacion);
    component.ordenes.subscribe(resultado => {
      expect(resultado.length).toBe(2);
      expect(resultado).toEqual(ordenes);
    });
    expect(component.identificacion).toEqual(cliente.identificacion);
  });

  it('deberia eliminar una orden', () => {
    let orden = ordenes[1];
    let listaOrdenes: Orden[] = [orden];
    
    spyOn(clienteService, 'obtenerOrdenesPorCliente').and.returnValue(
      of(listaOrdenes)
    );

    component.eliminarOrden(orden);
    component.ordenes.subscribe(resultado => {
      expect(resultado.length).toBe(1);
      expect(resultado).toEqual(listaOrdenes);
    });
    expect(ordenService.eliminar).toHaveBeenCalledWith(orden);
    expect(clienteService.obtenerOrdenesPorCliente).toHaveBeenCalledTimes(1);
  });

});
