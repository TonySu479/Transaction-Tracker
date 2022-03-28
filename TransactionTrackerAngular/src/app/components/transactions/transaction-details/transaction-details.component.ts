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

    selectedTransactionDetails: TransactionDetails[];

    transactionForm: FormGroup;

    transaction: Transaction;

    transactionDetail: TransactionDetails;

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
                    this.transactionDetails.push(data);
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

    editTransactionDetail(transactionDetail: TransactionDetails) {
        const ref = this.dialogService.open(TransactionDetailsDialogComponent, {
            header: 'Edit a Transaction detail',
            data: {
                type: "edit",
                transactionDetail: transactionDetail
            },
            width: "600px"
        });

        ref.onClose.subscribe(value => {
            if (!value) {
                return;
            }

            this.transactionDetailsService.update({...value, id: transactionDetail.id, productId: value.product.id, transactionId: this.transaction.id })
                .subscribe((data:TransactionDetails) => {
                    let index = this.transactionDetails.findIndex(transactionDetail => transactionDetail.id === data.id);
                    this.transactionDetails[index] = data;
                    this.messageService.add({
                        severity: "success",
                        summary: "transaction-detail edited",
                        detail: `transaction-detail edited`
                    });
                })
        })
    }

    submit() {

    }
}
