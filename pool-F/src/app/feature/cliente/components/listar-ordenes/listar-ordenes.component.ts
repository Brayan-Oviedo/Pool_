import { Component, OnInit } from '@angular/core';
import { ClienteService } from '@cliente/shared/service/cliente.service';
import { SwalService } from '@core/services/swal.service';
import { Orden } from '@orden/shared/model/orden';
import { OrdenService } from '@orden/shared/service/orden.service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-listar-ordenes',
  templateUrl: './listar-ordenes.component.html',
  styleUrls: ['./listar-ordenes.component.css']
})
export class ListarOrdenesComponent implements OnInit {


  titulo = 'Cliente';
  ordenes: Observable<Orden[]>;
  identificacion: string;

  constructor(
    protected clienteServicio: ClienteService,
    protected ordenService: OrdenService,
    protected swalService: SwalService
  ) { }

  ngOnInit(): void {
  }

  obtenerOrdenes(identificacion: string) {
    this.identificacion = identificacion;
    this.ordenes = this.clienteServicio.obtenerOrdenesPorCliente(identificacion);
  }

  eliminarOrden(orden: Orden) {
    this.ordenService.eliminar(orden).subscribe(() => {
      this.obtenerOrdenes(this.identificacion);
    }, fail => {
      this.swalService.error('No se logro eliminar la orden', fail.error.message);
    });
  }

}
