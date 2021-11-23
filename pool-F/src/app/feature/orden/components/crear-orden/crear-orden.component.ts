import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { Orden } from '@orden/shared/model/orden';
import { OrdenService } from '@orden/shared/service/orden.service';
import * as moment from 'moment';
import { Observable } from 'rxjs';
import { TicketDialogComponent } from 'src/app/feature/ticket/component/ticket-dialog/ticket-dialog.component';
import { Ticket } from 'src/app/feature/ticket/shared/model/ticket';

@Component({
  selector: 'app-crear-orden',
  templateUrl: './crear-orden.component.html',
  styleUrls: ['./crear-orden.component.css']
})
export class CrearOrdenComponent implements OnInit {

  formularioOrden: FormGroup;
  formularioCliente: FormGroup;


  constructor(
    protected formBuilder: FormBuilder,
    protected ordenService: OrdenService,
    protected dialogo: MatDialog
  ) { }

  ngOnInit(): void {

    this.crearFormularioOrden();
  }

  crear() {
    let orden = this.obtenerOrdenDelFormulario();
    let ticket = this.ordenService.crear(orden);
    this.mostrarTicket(ticket);
  }


  private mostrarTicket(ticket: Observable<Ticket>) {
    ticket.subscribe(
      ticket => {
        this.dialogo.open(TicketDialogComponent, { data: ticket });
      }
    );
  }


  private obtenerOrdenDelFormulario() {
    let orden: Orden = this.formularioOrden.value;
    orden.cliente = this.formularioCliente.value;
    orden.cliente.fechaNacimiento = this.formatearFecha(new Date(orden.cliente.fechaNacimiento));
    return orden;
  }


  private crearFormularioOrden() {
    this.formularioOrden = this.formBuilder.group({
      tiempoExtra: [0, [Validators.required, Validators.max(20)]]
    });

    this.formularioCliente = this.formBuilder.group({
      identificacion: ['', [Validators.required, Validators.minLength(10)]],
      fechaNacimiento: [this.obtenerFechaActual(), [Validators.required]]
    });
  }


  private obtenerFechaActual() {
    return this.formatearFecha(moment().toDate());
  }

  private formatearFecha(fecha: Date) {
    let fechaNueva = moment(fecha).format('YYYY-MM-DD');
    return fechaNueva;
  }

}
