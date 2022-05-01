import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InventoryCheckHistoryComponent } from './inventory-check-history.component';

describe('InventoryCheckHistoryComponent', () => {
  let component: InventoryCheckHistoryComponent;
  let fixture: ComponentFixture<InventoryCheckHistoryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ InventoryCheckHistoryComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(InventoryCheckHistoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
