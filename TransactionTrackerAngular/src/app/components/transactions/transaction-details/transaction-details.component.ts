import {Component, OnInit} from '@angular/core';
import {Transaction} from "../../../api/transaction";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {TransactionService} from "../../../service/transactionservice";
import {ConfirmationService, MessageService} from "primeng/api";
import {DialogService} from "primeng/dynamicdialog";
import {TransactionDetailsDialogComponent} from "./transaction-details-dialog/transaction-details-dialog.component";
import {ActivatedRoute, Router} from "@angular/router";
import {TransactionDetailService} from "../../../service/transaction-detail.service";
import {TransactionDetail} from "../../../api/transaction-detail";
import {TransactionType} from "../../../api/transaction-type.enum";

@Component({
    selector: 'app-transaction-details',
    templateUrl: './transaction-details.component.html',
    styleUrls: ['./transaction-details.component.scss'],
    providers: [MessageService, ConfirmationService, DialogService]
})
export class TransactionDetailsComponent implements OnInit {

    transactionDetails: TransactionDetail[] = [];

    selectedTransactionDetails: TransactionDetail[];

    transactionTypes = [TransactionType.SALE, TransactionType.RECEIVE];

    transactionForm: FormGroup;

    transaction: Transaction;

    transactionDetail: TransactionDetail;

    disableTypeDropdown: boolean;

    constructor(private formBuilder: FormBuilder,
                private transactionService: TransactionService,
                private messageService: MessageService,
                private confirmationService: ConfirmationService,
                private dialogService: DialogService,
                private router: Router,
                private route: ActivatedRoute,
                private transactionDetailsService: TransactionDetailService) {
    }

    get transactionType() {
        return this.transactionForm.controls.transactionType;
    }

    get createdAt() {
        return this.transactionForm.controls.createdAt;
    }

    ngOnInit(): void {
        this.transaction = this.route.snapshot.data.transaction;
        this.transactionForm = this.formBuilder.group({
            createdAt: new FormControl({
                value: this.transaction ? this.transaction.createdAt : new Date(),
                disabled: !!this.transaction
            }),
            transactionType: new FormControl({
                value: this.transaction ? this.transaction.transactionType : "",
                disabled: !!this.transaction
            }, [Validators.required])
        });
        if (!!this.transaction) {
            this.transactionDetailsService.getTransactionDetailsByTransactionId(this.transaction.id).subscribe(
                data => {
                    this.transactionDetails = data;
                }
            );
        }
    }

    addNewTransactionDetail() {
        if (!this.transaction && !this.transactionForm.valid) {
            this.transactionForm.markAllAsTouched();
            return;
        }

        if (!this.transaction) {
            this.addTransactionDetailWithNewTransaction();
        } else {
            this.addTransactionDetail()
        }
    }

    addTransactionDetail() {
        const ref = this.openAddNewProductDialog();
        ref.onClose.subscribe(value => {
            if (!value) {
                return;
            }
            this.createTransactionDetail(value);
        });
    }

    addTransactionDetailWithNewTransaction() {
        const ref = this.openAddNewProductDialog();

        ref.onClose.subscribe(value => {
            if (!value) {
                return;
            }
            this.transactionService.create(this.transactionForm.value).subscribe(data => {
                this.transaction = data;
                this.transactionType.disable();
                this.createdAt.disable();
                value.transactionId = this.transaction.id;
                value.productId = value.product.id;
                this.createTransactionDetail(value);
            });
        });
    }

    createTransactionDetail(value) {
        this.transactionDetailsService.create({
            transactionId: this.transaction.id,
            productId: value.product.id,
            price: value.price,
            quantity: value.quantity
        }).subscribe(data => {
            this.transactionDetails.push(data);
            this.messageService.add({
                severity: "success",
                summary: "product created",
                detail: `${data.product.name} created`
            });
        });
    }

    openAddNewProductDialog() {
        return this.dialogService.open(TransactionDetailsDialogComponent, {
            header: 'Add a new product',
            data: {
                type: "new"
            },
            width: "600px"
        });
    }

    editTransactionDetail(transactionDetail: TransactionDetail) {
        const ref = this.dialogService.open(TransactionDetailsDialogComponent, {
            header: 'Edit a Transaction detail',
            data: {
                type: "edit",
                transactionDetail: transactionDetail
            },
            width: "600px"
        });
        console.log(transactionDetail);

        ref.onClose.subscribe(value => {
            if (!value) {
                return;
            }
            console.log(transactionDetail.id);
            this.transactionDetailsService.update({
                ...value,
                id: transactionDetail.id,
                productId: value.product.id,
                transactionId: this.transaction.id
            })
                .subscribe((data: TransactionDetail) => {
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

    deleteTransactionDetail(transactionDetail: TransactionDetail) {
        this.confirmationService.confirm({
            message: `Are you sure you want to delete ${transactionDetail.product.name}?`,
            header: 'Confirmation',
            icon: 'fa fa-question-circle',
            accept: () => {
                this.transactionDetailsService.delete(transactionDetail).subscribe(() => {
                    this.transactionDetails = this.transactionDetails.filter(p => p.id != transactionDetail.id);
                    this.messageService.add({
                        severity: "success",
                        summary: "product deleted",
                        detail: `${transactionDetail.product.name} has been deleted`
                    });
                })
            }
        });
    }

    submit() {

    }
}
