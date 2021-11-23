import { ChangeDetectionStrategy, ChangeDetectorRef, Component, Input } from '@angular/core';

@Component({
  templateUrl: './error-campos-plantilla.component.html',
  styleUrls: ['./error-campos-plantilla.component.css'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class ErrorCamposPlantillaComponent {
  mensajeError;
  ocultar = true;

  @Input()
  set text(value) {
    if (value !== this.mensajeError) {
      this.mensajeError = value;
      this.ocultar = !value;
      this.cdr.detectChanges();
    }
  }

  constructor(private cdr: ChangeDetectorRef) {}
}

