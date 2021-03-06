import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {DynamicDialogConfig, DynamicDialogRef} from "primeng/dynamicdialog";
import {TransactionService} from "../../../../service/transactionservice";
import {Product} from "../../../../model/product";
import {ProductService} from "../../../../service/productservice";

@Component({
  selector: 'app-transaction-details-dialog',
  templateUrl: './transaction-details-dialog.component.html',
  styleUrls: ['./transaction-details-dialog.component.scss']
})
export class TransactionDetailsDialogComponent implements OnInit {

    transactionDetailsForm: FormGroup;
    selectedProduct: Product;
    productResults: Product[];

    constructor(private ref: DynamicDialogRef,
                private transactionService: TransactionService,
                private config: DynamicDialogConfig,
                private formBuilder: FormBuilder,
                private productService: ProductService
    ) {
    }

    get name() {
        return this.transactionDetailsForm.controls.name;
    }

    get description() {
        return this.transactionDetailsForm.controls.description;
    }

    get product() {
        return this.transactionDetailsForm.controls.product;
    }

    get price() {
        return this.transactionDetailsForm.controls.price;
    }

    get quantity() {
        return this.transactionDetailsForm.controls.quantity;
    }

    searchProducts(event) {
        this.productService.getProducts(event.query).subscribe(data => {
            this.productResults = data;
        });
    }

    setDefaultValues(event) {
        if(!!event) {
            this.price.setValue(event.price);
        }
    }

    ngOnInit(): void {
        this.transactionDetailsForm = this.formBuilder.group({
            product: new FormControl(this.config.data.type === "new" ? "" : this.config.data.transactionDetail.product, [Validators.required]),
            price: new FormControl(this.config.data.type === "new" ? "" : this.config.data.transactionDetail.price,
                [Validators.min(0), Validators.required]),
            quantity: new FormControl(this.config.data.type === "new" ? "" : this.config.data.transactionDetail.quantity,
                [Validators.min(0), Validators.required])
        });
    }

    submit() {
        if (!this.transactionDetailsForm.valid) {
            this.transactionDetailsForm.markAllAsTouched();
            return;
        }

        this.ref.close(this.transactionDetailsForm.value);
    }

}
