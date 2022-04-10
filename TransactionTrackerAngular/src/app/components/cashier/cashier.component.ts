import {Component, OnInit, ViewChild} from '@angular/core';
import {ConfirmationService, MessageService} from "primeng/api";
import {DialogService} from "primeng/dynamicdialog";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {Product} from "../../api/product";
import {TransactionService} from "../../service/transactionservice";
import {ProductService} from "../../service/productservice";
import {TransactionDetail} from "../../api/transaction-detail";
import {TransactionDetailService} from "../../service/transaction-detail.service";
import {Transaction} from "../../api/transaction";
import {Router} from "@angular/router";
import {ShiftService} from "../../service/shiftservice";
import {Shift} from "../../api/shift";
import {CashierDialogComponent} from "./cashier-dialog/cashier-dialog.component";

@Component({
    selector: 'app-cashier',
    templateUrl: './cashier.component.html',
    styleUrls: ['./cashier.component.scss'],
    providers: [MessageService, ConfirmationService, DialogService]
})
export class CashierComponent implements OnInit {

    @ViewChild("dt") dataTable: any;
    cashierForm: FormGroup;
    productResults: Product[];
    transactionDetails: TransactionDetail[] = [];
    transaction: Transaction;
    shift: Shift;

    constructor(private confirmationService: ConfirmationService,
                private formBuilder: FormBuilder,
                private transactionService: TransactionService,
                private transactionDetailsService: TransactionDetailService,
                private productService: ProductService,
                private messageService: MessageService,
                private router: Router,
                private shiftService: ShiftService,
                private dialogService: DialogService) {
    }

    get product() {
        return this.cashierForm.controls.product;
    }

    get quantity() {
        return this.cashierForm.controls.quantity;
    }

    get total() {
        return this.transactionDetails.reduce((prev, curr) => prev + curr.product.price * curr.quantity, 0);
    }

    ngOnInit(): void {
        this.cashierForm = this.formBuilder.group({
            product: new FormControl("", [Validators.required]),
            quantity: new FormControl("", [Validators.min(0), Validators.required])
        });

        this.shiftService.createShift().subscribe(data => {
            this.shift = data
        });

    }

    addProduct() {
        if (!this.cashierForm.valid) {
            this.cashierForm.markAllAsTouched();
            return;
        }
        const {quantity, product} = this.cashierForm.value;
        this.transactionDetails = [...this.transactionDetails, {
            quantity,
            product
        }];
    }

    deleteTransactionDetail(index) {
        this.confirmationService.confirm({
            message: "Are you sure you want to delete this product?",
            header: 'Confirmation',
            icon: 'fa fa-question-circle',
            accept: () => {
                this.transactionDetails.splice(index, 1);
                this.messageService.add({
                    severity: "success",
                    summary: "product deleted",
                    detail: "product has been deleted"
                })
            }
        });
    }

    searchProducts(event) {
        this.productService.getProducts(event.query).subscribe(data => {
            this.productResults = data;
        });
    }

    createTransactionDetails() {
        if (this.transactionDetails.length === 0) {
            return
        }
        this.confirmationService.confirm({
            message: "Are you sure you want to create this transaction?",
            header: 'Confirmation',
            icon: 'fa fa-question-circle',
            accept: () => {
                this.transactionDetailsService.createTransactionDetails(
                    {
                        shiftId: this.shift.id,
                        transactionDetailsDTOS:
                            this.transactionDetails.map(transactionDetail => {
                                return {
                                    ...transactionDetail,
                                    productId: transactionDetail.product.id,
                                    quantity: transactionDetail.quantity,
                                    price: transactionDetail.product.price,
                                }
                            }),
                    }).subscribe(() => {
                    this.messageService.add({
                        severity: "success",
                        summary: "transaction created",
                        detail: `transaction created`
                    });

                });
                this.transactionDetails = [];
            }
        });
    }

    clearProducts() {
        this.confirmationService.confirm({
            message: "Are you sure you want to clear all products?",
            header: 'Confirmation',
            icon: 'fa fa-question-circle',
            accept: () => {
                this.transactionDetails = [];
                this.messageService.add({
                    severity: "success",
                    summary: "products deleted",
                    detail: "all products have been deleted"
                })
            }
        });
    }

    endShift() {
        this.confirmationService.confirm({
            message: "Are you sure you want to clear all end your shift?",
            header: 'Confirmation',
            icon: 'fa fa-question-circle',
            accept: () => {
                this.shiftService.endShift().subscribe(data => {
                    this.shift = data;
                    let ref = this.dialogService.open(CashierDialogComponent, {
                        header: 'Shift Details',
                        data: {
                            shift: this.shift
                        },
                        width: "600px"
                    });
                    ref.onClose.subscribe(() => this.logout())
                });
                this.messageService.add({
                    severity: "success",
                    summary: "shift ended",
                    detail: "you have successfully ended your shift"
                })
            }
        });
    }


    logout() {
        window.sessionStorage.clear();
        setTimeout(() => {
            this.router.navigate(['/login']);
        }, 100);
    }

}
