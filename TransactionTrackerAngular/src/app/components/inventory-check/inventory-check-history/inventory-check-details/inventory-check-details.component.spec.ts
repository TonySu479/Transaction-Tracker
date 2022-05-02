import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InventoryCheckDetailsComponent } from './inventory-check-details.component';

describe('InventoryCheckDetailsComponent', () => {
  let component: InventoryCheckDetailsComponent;
  let fixture: ComponentFixture<InventoryCheckDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ InventoryCheckDetailsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(InventoryCheckDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
