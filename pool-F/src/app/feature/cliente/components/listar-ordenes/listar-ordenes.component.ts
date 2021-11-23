import { Component, OnInit } from '@angular/core';
import { ClienteService } from '@cliente/shared/service/cliente.service';
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

  constructor(
    protected clienteServicio: ClienteService,
    protected ordenService: OrdenService
  ) { }

  ngOnInit(): void {
  }

  obtenerOrdenes(identificacion: string) {
    this.ordenes = this.clienteServicio.obtenerOrdenesPorCliente(identificacion);
  }

  eliminarOrden(orden: Orden) {
    this.ordenService.eliminar(orden);
  }

}
