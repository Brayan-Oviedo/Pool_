import { Injectable } from "@angular/core";
import Swal from "sweetalert2";

@Injectable()
export class SwalService {

    success(title: string, text: string): void {
        Swal.fire({
            title: title,
            text: text,
            icon: 'success',
            timer: 1500,
            timerProgressBar: false,
            position: 'bottom' ,
            showConfirmButton: false
        });
    }


    error(tittle: string, text: string): void {
        Swal.fire({
            title: tittle,
            text: text,
            icon: 'error',
            position: 'center',
            confirmButtonColor: '#ff00ff80'
        });
    }
}