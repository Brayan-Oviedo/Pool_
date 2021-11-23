import { NgModule } from "@angular/core";
import { OrdenService } from "@orden/shared/service/orden.service";
import { FeatureSharedModule } from "../shared/feature-shared.module";
import { ClienteRoutingModule } from "./cliente-routing.module";
import { ClienteComponent } from "./components/cliente/cliente.component";
import { ListarOrdenesComponent } from './components/listar-ordenes/listar-ordenes.component';
import { ClienteService } from "./shared/service/cliente.service";

@NgModule({
    declarations: [
        ClienteComponent,
        ListarOrdenesComponent
    ],
    imports: [
        ClienteRoutingModule,
        FeatureSharedModule
    ],
    providers: [ClienteService, OrdenService]
})
export class ClienteModule { }