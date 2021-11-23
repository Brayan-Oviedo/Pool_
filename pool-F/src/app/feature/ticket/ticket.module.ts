import { NgModule } from "@angular/core";
import { SharedModule } from "@shared/shared.module";
import { TicketDialogComponent } from "./component/ticket-dialog/ticket-dialog.component";
import { TicketComponent } from "./component/ticket/ticket.component";
import { TicketRoutingModule } from "./ticket-routing";

@NgModule({
    declarations: [
        TicketComponent,
        TicketDialogComponent
    ],
    imports: [
        TicketRoutingModule,
        SharedModule
    ]
})
export class TicketModule { }