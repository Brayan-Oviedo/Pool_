import { ComponentFixture, TestBed, waitForAsync } from "@angular/core/testing";
import { Cliente } from "@cliente/shared/model/cliente";
import { MaterialModule } from "@core/material.module";
import { Orden } from "@orden/shared/model/orden";
import { OrdenTarjetaComponent } from "./orden-tarjeta.component";


describe('OrdenTarjetaComponent', () => {
  let component: OrdenTarjetaComponent;
  let fixture: ComponentFixture<OrdenTarjetaComponent>;
  let cliente: Cliente = new Cliente('1234567890', '2001-01-01');
  let orden: Orden = new Orden(1, 1, cliente);

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ OrdenTarjetaComponent ],
      imports: [
        MaterialModule
      ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OrdenTarjetaComponent);
    component = fixture.componentInstance;
    component.orden = orden;
    spyOn(component.eliminar, 'emit');
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });

  it('deberia emitir el evento para eliminar una orden', () => {
    component.doEliminar();
    expect(component.eliminar.emit).toHaveBeenCalledTimes(1);
    expect(component.eliminar.emit).toHaveBeenCalledWith(orden);
  });
});
