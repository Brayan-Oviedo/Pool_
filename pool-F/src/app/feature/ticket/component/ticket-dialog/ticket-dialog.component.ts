import { Component, Inject, OnInit } from '@angular/core';
import { MatDialog, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Ticket } from '../../shared/model/ticket';

@Component({
  selector: 'app-ticket-dialog',
  templateUrl: './ticket-dialog.component.html',
  styleUrls: ['./ticket-dialog.component.css']
})
export class TicketDialogComponent implements OnInit {

  ticket: Ticket;

  constructor(@Inject (MAT_DIALOG_DATA) ticket: Ticket, protected dialogo: MatDialog) {
    this.ticket = ticket;
  }

  ngOnInit(): void {
  }

  cerrar() {
    this.dialogo.closeAll();
  }

}
