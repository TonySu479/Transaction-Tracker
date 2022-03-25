import {Component, OnInit} from '@angular/core';
import {Transaction} from "../../../api/transaction";
import {FormBuilder, FormControl, FormGroup} from "@angular/forms";
import {TransactionService} from "../../../service/transactionservice";
import {TransactionDialogComponent} from "../transaction-dialog/transaction-dialog.component";
import {ConfirmationService, MessageService} from "primeng/api";
import {DialogService} from "primeng/dynamicdialog";

@Component({
    selector: 'app-transaction-details',
    templateUrl: './transaction-details.component.html',
    styleUrls: ['./transaction-details.component.scss'],
    providers: [MessageService, ConfirmationService, DialogService]
})
export class TransactionDetailsComponent implements OnInit {

    transactions: Transaction[];

    selectedTransactions: Transaction[];

    transactionForm: FormGroup;

    transaction: Transaction;

    constructor(private formBuilder: FormBuilder,
                private transactionService: TransactionService,
                private messageService: MessageService,
                private confirmationService: ConfirmationService,
                private dialogService: DialogService) {
    }

    ngOnInit(): void {
        this.transactionForm = this.formBuilder.group({
            date: new FormControl(new Date())
        })
    }

    addNew() {
        if (!this.transactionForm.valid) {
            this.transactionForm.markAllAsTouched();
            return;
        }

        if (!this.transaction) {
            this.transactionService.create(this.transactionForm.value).subscribe(data => {
                this.transaction = data;
                this.editTransaction(data);
            });
        }
    }

    editTransaction(transaction: Transaction) {
        const ref = this.dialogService.open(TransactionDialogComponent, {
            header: 'Edit a transaction',
            data: {
                type: "edit",
                transaction: transaction
            },
            width: "600px"
        });

        ref.onClose.subscribe(value => {
            if (!value) {
                return;
            }
            this.transactionService.update({...value, id: transaction.id})
                .subscribe((data: Transaction) => {
                    this.messageService.add({
                        severity: "success",
                        summary: "transaction edited",
                        detail: `${data.name} edited`
                    });
                })
        })
    }

    submit() {

    }
}
