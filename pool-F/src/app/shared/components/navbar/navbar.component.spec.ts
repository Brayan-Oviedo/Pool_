import { CommonModule } from "@angular/common";
import { ComponentFixture, TestBed, waitForAsync } from "@angular/core/testing";
import { MaterialModule } from "@core/material.module";
import { MenuComponent } from "../menu/menu.component";
import { NavbarComponent } from "../navbar/navbar.component";

describe('NavbarComponent', () => {
    let component: NavbarComponent;
    let fixture: ComponentFixture<NavbarComponent>;
  
    beforeEach(waitForAsync(() => {
      TestBed.configureTestingModule({
        declarations: [ NavbarComponent, MenuComponent ],
        imports: [
          MaterialModule,
          CommonModule
        ]
      })
      .compileComponents();
    }));
  
    beforeEach(() => {
      fixture = TestBed.createComponent(NavbarComponent);
      component = fixture.componentInstance;
      fixture.detectChanges();
    });
  
    it('should be created', () => {
      expect(component).toBeTruthy();
    });
});