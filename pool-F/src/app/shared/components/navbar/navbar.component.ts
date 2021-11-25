import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-navbar',
  templateUrl: 'navbar.component.html',
  styleUrls: ['./navbar.component.css'],
})
export class NavbarComponent implements OnInit {

  @Input() paginaActual: string;
  @Input() busqueda = false;
  @Input() tituloBusqueda: string;

  @Output() buscar = new EventEmitter<string>();

  item: string;

  constructor() { }

  ngOnInit() {
  }

  doBuscar() {
    this.buscar.emit(this.item);
  }
}