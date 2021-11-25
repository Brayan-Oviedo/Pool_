import { ComponentFixture, TestBed, waitForAsync } from "@angular/core/testing";
import { MatDialog, MAT_DIALOG_DATA } from "@angular/material/dialog";
import { BrowserDynamicTestingModule } from "@angular/platform-browser-dynamic/testing";
import { MaterialModule } from "@core/material.module";
import { TicketDialogComponent } from "./ticket-dialog.component";


describe('TicketDialogComponent', () => {
    let component: TicketDialogComponent;
    let fixture: ComponentFixture<TicketDialogComponent>;
    let matDialog: MatDialog;

    beforeEach(waitForAsync(() => {
        TestBed.configureTestingModule({
            declarations: [TicketDialogComponent],
            imports: [
                MaterialModule,
                BrowserDynamicTestingModule
            ],
            providers: [
                MatDialog,
                { provide: MAT_DIALOG_DATA, useValue: {} }
            ]
        })
            .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(TicketDialogComponent);
        component = fixture.componentInstance;
        matDialog = TestBed.inject(MatDialog);
        spyOn(matDialog, 'closeAll');
        fixture.detectChanges();
    });

    it('should be created', () => {
        expect(component).toBeTruthy();
        expect(component.ticket).not.toEqual(null);
    });

    it('deberia cerrarse el dialogo', () => {
        component.cerrar();
        expect(matDialog.closeAll).toHaveBeenCalledTimes(1);
    });

});
