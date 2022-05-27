import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditTransactiondetailDialogComponent } from './edit-transactiondetail-dialog.component';

describe('EditTransactiondetailDialogComponent', () => {
  let component: EditTransactiondetailDialogComponent;
  let fixture: ComponentFixture<EditTransactiondetailDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EditTransactiondetailDialogComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EditTransactiondetailDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
