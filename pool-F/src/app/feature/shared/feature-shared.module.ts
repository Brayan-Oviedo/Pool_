import { NgModule } from "@angular/core";
import { OrdenTarjetaComponent } from "./components/orden-tarjeta/orden-tarjeta.component";
import { SharedModule } from "@shared/shared.module";

@NgModule({
    declarations: [OrdenTarjetaComponent],
    imports: [SharedModule],
    exports: [
        OrdenTarjetaComponent,
        SharedModule
    ]
})
export class FeatureSharedModule { }