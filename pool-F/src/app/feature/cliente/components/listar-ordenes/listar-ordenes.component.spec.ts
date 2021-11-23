import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { Cliente } from '@cliente/shared/model/cliente';
import { ClienteService } from '@cliente/shared/service/cliente.service';
import { MaterialModule } from '@core/material.module';
import { HttpService } from '@core/services/http.service';
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
  //let ordenService: OrdenService;
  let clienteService: ClienteService;
  const cliente: Cliente = new Cliente('123', '2001-01-01'); 
  const ordenes: Orden[] = [new Orden(1, 1, cliente), new Orden(2, 2, cliente)];

  beforeEach(async () => {
    TestBed.configureTestingModule({
      declarations: [ ListarOrdenesComponent, OrdenTarjetaComponent, NavbarComponent, MenuComponent ],
      imports: [
        CommonModule,
        MaterialModule,
        HttpClientModule,
        BrowserAnimationsModule
      ],
      providers: [ 
        OrdenService,
        ClienteService,
        HttpService
      ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ListarOrdenesComponent);
    component = fixture.componentInstance;
    //ordenService = TestBed.inject(OrdenService);
    clienteService = TestBed.inject(ClienteService);
    spyOn(clienteService, 'obtenerOrdenesPorCliente').and.returnValue(
        of(ordenes)
    );
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('Obteniendo ordenes de cliente', () => {
    component.obtenerOrdenes(cliente.identificacion);
    component.ordenes.subscribe(resultado => {
        expect(resultado.length).toBe(2);
        expect(resultado).toEqual(ordenes);
    });
  });
});
