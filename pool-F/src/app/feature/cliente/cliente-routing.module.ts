import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { ClienteComponent } from "./components/cliente/cliente.component";
import { ListarOrdenesComponent } from "./components/listar-ordenes/listar-ordenes.component";

const routes: Routes = [
    {
      path: '',
      component: ClienteComponent,
      children: [
        {
          path: '',
          redirectTo: '/cliente/listar-ordenes',
          pathMatch: 'full'
        },
        {
          path: "listar-ordenes",
          component: ListarOrdenesComponent
        }
      ]
    }
  ];
  
  @NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
  })
  export class ClienteRoutingModule { }