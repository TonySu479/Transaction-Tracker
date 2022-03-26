import {Component, OnInit} from '@angular/core';
import {Transaction} from "../../../api/transaction";
import {FormBuilder, FormControl, FormGroup} from "@angular/forms";
import {TransactionService} from "../../../service/transactionservice";
import {TransactionDialogComponent} from "../transaction-dialog/transaction-dialog.component";
import {ConfirmationService, MessageService} from "primeng/api";
import {DialogService} from "primeng/dynamicdialog";
import {Product} from "../../../api/product";
import {ProductDialogComponent} from "../../products/product-dialog/product-dialog.component";
import {TransactionDetailsDialogComponent} from "./transaction-details-dialog/transaction-details-dialog.component";

@Component({
    selector: 'app-transaction-details',
    templateUrl: './transaction-details.component.html',
    styleUrls: ['./transaction-details.component.scss'],
    providers: [MessageService, ConfirmationService, DialogService]
})
export class TransactionDetailsComponent implements OnInit {

    products: Product[];

    selectedProducts: Product[];

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

    addNewTransaction() {
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

    addNewProductToTransaction() {
        const ref = this.dialogService.open(TransactionDetailsDialogComponent, {
            header: 'Add a new product',
            data: {
                type: "new"
            },
            width: "600px"
        });

        ref.onClose.subscribe(value => {
            if(!value){
                return;
            }
            console.log(value);
            // this.transactionService.create(value)
            //     .subscribe(data => {
            //         console.log(this.products);
            //         this.products.push(data);
            //         this.messageService.add({severity:"success", summary:"product created", detail:`${data.name} created`});
            //     })
        });
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
            console.log(value);
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
