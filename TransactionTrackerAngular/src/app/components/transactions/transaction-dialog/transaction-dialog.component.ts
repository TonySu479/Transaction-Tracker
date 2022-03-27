import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {TransactionService} from "../../../service/transactionservice";
import {DynamicDialogConfig, DynamicDialogRef} from "primeng/dynamicdialog";

@Component({
    selector: 'app-transaction-dialog',
    templateUrl: './transaction-dialog.component.html',
    styleUrls: ['./transaction-dialog.component.scss']
})
export class TransactionDialogComponent implements OnInit {


    transactionForm: FormGroup;

    constructor(private ref: DynamicDialogRef,
                private transactionService: TransactionService,
                private config: DynamicDialogConfig,
                private formBuilder: FormBuilder
    ) {
    }

    get name() {
        return this.transactionForm.controls.name;
    }

    get description() {
        return this.transactionForm.controls.description;
    }

    ngOnInit(): void {
        console.log(this.config.data.type);
        this.transactionForm = this.formBuilder.group({
            name: new FormControl(this.config.data.type === "new" ? "" : this.config.data.transaction.name,
                [Validators.maxLength(20), Validators.required]),
            createdAt: new FormControl(this.config.data.transaction.createdAt,
                [Validators.required]),
        });
    }

    submit() {
        if (!this.transactionForm.valid) {
            this.transactionForm.markAllAsTouched();
            return;
        }

        this.ref.close(this.transactionForm.value);
    }

}
