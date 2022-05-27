import {Component, OnInit} from '@angular/core';
import {DynamicDialogConfig, DynamicDialogRef} from "primeng/dynamicdialog";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
    selector: 'app-edit-transactiondetail-dialog',
    templateUrl: './edit-transactiondetail-dialog.component.html',
    styleUrls: ['./edit-transactiondetail-dialog.component.scss']
})
export class EditTransactiondetailDialogComponent implements OnInit {

    transactionDetailForm: FormGroup;

    constructor(private ref: DynamicDialogRef,
                private config: DynamicDialogConfig,
                private formBuilder: FormBuilder) {
    }

    get quantity() {
        return this.transactionDetailForm.controls.quantity;
    }

    ngOnInit(): void {
        console.log(this.config.data.transactionDetail);
        this.transactionDetailForm = this.formBuilder.group({
            quantity: new FormControl(this.config.data.transactionDetail.quantity,
                [Validators.min(0), Validators.required])
        });
    }

    submit() {
        if (!this.transactionDetailForm.valid) {
            this.transactionDetailForm.markAllAsTouched();
            return;
        }

        this.ref.close(this.transactionDetailForm.value);
    }

}
