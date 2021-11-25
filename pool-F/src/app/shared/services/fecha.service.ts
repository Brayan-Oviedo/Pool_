import { Injectable } from '@angular/core';
import * as moment from 'moment';

@Injectable({
  providedIn: 'root'
})
export class FechaService {

  constructor() { }

  obtenerFechaActual() {
    return moment().toDate();
  }

  formatearFechaSinHora(fecha: Date) {
    let fechaNueva = moment(fecha).format('YYYY-MM-DD');
    return fechaNueva;
  }

  formatearFechaConHora(fecha: Date) {
    let fechaNueva = moment(fecha).format('YYYY-MM-DD HH:mm:ss');
    return fechaNueva;
  }
}
