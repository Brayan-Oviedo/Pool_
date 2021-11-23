import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Orden } from '@orden/shared/model/orden';

@Component({
  selector: 'app-orden-tarjeta',
  templateUrl: './orden-tarjeta.component.html',
  styleUrls: ['./orden-tarjeta.component.css']
})
export class OrdenTarjetaComponent implements OnInit {

  @Input() orden: Orden;

  @Output() eliminar = new EventEmitter<Orden>();

  constructor() { }

  ngOnInit(): void {
  }

  doEliminar() {
    this.eliminar.emit(this.orden);
  }

}
