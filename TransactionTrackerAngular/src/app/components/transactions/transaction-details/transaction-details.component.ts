import {Component, OnInit} from '@angular/core';
import {Transaction} from "../../../api/transaction";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {TransactionService} from "../../../service/transactionservice";
import {TransactionDialogComponent} from "../transaction-dialog/transaction-dialog.component";
import {ConfirmationService, MessageService} from "primeng/api";
import {DialogService} from "primeng/dynamicdialog";
import {Product} from "../../../api/product";
import {TransactionDetailsDialogComponent} from "./transaction-details-dialog/transaction-details-dialog.component";
import {Router} from "@angular/router";
import {TransactionDetailsService} from "../../../service/transaction-detailsservice";
import {TransactionDetails} from "../../../api/transaction-details";

@Component({
    selector: 'app-transaction-details',
    templateUrl: './transaction-details.component.html',
    styleUrls: ['./transaction-details.component.scss'],
    providers: [MessageService, ConfirmationService, DialogService]
})
export class TransactionDetailsComponent implements OnInit {

    transactionDetails: TransactionDetails[];

    selectedProducts: Product[];

    transactionForm: FormGroup;

    transaction: Transaction;

    constructor(private formBuilder: FormBuilder,
                private transactionService: TransactionService,
                private messageService: MessageService,
                private confirmationService: ConfirmationService,
                private dialogService: DialogService,
                private router: Router,
                private transactionDetailsService: TransactionDetailsService) {
    }

    ngOnInit(): void {
        this.transactionForm = this.formBuilder.group({
            createdAt: new FormControl(new Date())
        })
        this.transactionDetails = [];
    }


    addProductToTransaction() {
        if (!this.transactionForm.valid) {
            this.transactionForm.markAllAsTouched();
            return;
        }

        if (!this.transaction) {
            this.addProductNewTransaction();
        } else {
            this.addProduct()
        }
    }

    addProduct(){
        const ref = this.openAddNewProductDialog();
        ref.onClose.subscribe(value => {
            if(!value){
                return;
            }
            // value.transactionId = this.transaction.id;
            // value.productId = value.product.id;
            this.transactionDetailsService.create({
                transactionId: this.transaction.id,
                productId: value.product.id,
                price: value.price,
                quantity: value.quantity
            }).subscribe(data =>
            {
                console.log(data);
                this.transactionDetails.push(data);
                console.log(this.transactionDetails);
            });
        });
    }

    addProductNewTransaction(){
        const ref = this.openAddNewProductDialog();

        ref.onClose.subscribe(value => {
            if(!value){
                return;
            }
            this.transactionService.create(this.transactionForm.value).subscribe(data => {
                this.transaction = data;
                value.transactionId = this.transaction.id;
                value.productId = value.product.id;
                console.log(value);
                this.transactionDetailsService.create({
                    transactionId: this.transaction.id,
                    productId: value.product.id,
                    price: value.price,
                    quantity: value.quantity
                }).subscribe(data =>
                {
                    console.log(data);
                    this.transactionDetails.push(data);
                    console.log(this.transactionDetails);
                });
            });
        });
    }

    openAddNewProductDialog(){
        return this.dialogService.open(TransactionDetailsDialogComponent, {
            header: 'Add a new product',
            data: {
                type: "new"
            },
            width: "600px"
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
                        detail: `transaction edited`
                    });
                })
        })
    }

    submit() {

    }
}
