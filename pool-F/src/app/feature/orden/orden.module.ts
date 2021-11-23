import { NgModule } from "@angular/core";
import { FeatureSharedModule } from "../shared/feature-shared.module";
import { CrearOrdenComponent } from "./components/crear-orden/crear-orden.component";
import { OrdenComponent } from "./components/orden/orden.component";
import { OrdenRoutingModule } from "./orden-routing.module";
import { OrdenService } from "./shared/service/orden.service";

@NgModule({
    declarations: [
        OrdenComponent,
        CrearOrdenComponent
    ],
    imports: [
        OrdenRoutingModule,
        FeatureSharedModule
    ],
    providers: [OrdenService]
})
export class OrdenModule { }