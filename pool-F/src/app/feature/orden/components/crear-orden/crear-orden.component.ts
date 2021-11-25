import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { SwalService } from '@core/services/swal.service';
import { Orden } from '@orden/shared/model/orden';
import { OrdenService } from '@orden/shared/service/orden.service';
import { FechaService } from '@shared/services/fecha.service';
import { Ticket } from '@ticket/shared/model/ticket';
import * as moment from 'moment';
import { TicketDialogComponent } from 'src/app/feature/ticket/component/ticket-dialog/ticket-dialog.component';

@Component({
  selector: 'app-crear-orden',
  templateUrl: './crear-orden.component.html',
  styleUrls: ['./crear-orden.component.css']
})
export class CrearOrdenComponent implements OnInit {

  ordenForm: FormGroup;
  clienteForm: FormGroup;
  orden: Orden;


  constructor(
    protected formBuilder: FormBuilder,
    protected ordenService: OrdenService,
    protected dialogo: MatDialog,
    protected swalService: SwalService,
    protected fechaService: FechaService
  ) { }

  ngOnInit(): void {

    this.crearFormularios();
  }

  crear() {
    this.obtenerOrdenDelFormulario();
    this.ordenService.crear(this.orden).subscribe(
      ticket => {
        this.swalService.success('Orden creada', '');
        this.mostrarTicket(ticket);
      }, fail => {
        this.swalService.error('No se logro crear el ticket', fail.error.mensaje);
      }
    );
  }

  private mostrarTicket(ticket: Ticket) {
    this.dialogo.open(TicketDialogComponent, { data: ticket }).afterClosed()
      .subscribe(() => {
        this.crearFormularios();
      });
  }


  private obtenerOrdenDelFormulario() {
    this.orden = this.ordenForm.value;
    this.orden.cliente = this.clienteForm.value;
    this.orden.cliente.fechaNacimiento = this.fechaService.formatearFechaSinHora(moment(this.orden.cliente.fechaNacimiento).toDate());
  }


  private crearFormularios() {
    this.ordenForm = this.formBuilder.group({
      tiempoExtra: [0, [Validators.required, Validators.max(20)]]
    });

    this.clienteForm = this.formBuilder.group({
      identificacion: ['', [Validators.required, Validators.minLength(10)]],
      fechaNacimiento: [this.fechaService.obtenerFechaActual(), [Validators.required]]
    });
  }

}
