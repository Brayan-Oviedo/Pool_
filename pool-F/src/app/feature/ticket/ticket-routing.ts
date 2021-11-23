import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { TicketComponent } from "./component/ticket/ticket.component";

const routes: Routes = [
    {
      path: '',
      component: TicketComponent,
      children: []
    }
  ];
  
  @NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
  })
  export class TicketRoutingModule { }