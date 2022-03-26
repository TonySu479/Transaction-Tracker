import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {DynamicDialogConfig, DynamicDialogRef} from "primeng/dynamicdialog";
import {TransactionService} from "../../../../service/transactionservice";
import {Product} from "../../../../api/product";
import {ProductService} from "../../../../service/productservice";

@Component({
  selector: 'app-transaction-details-dialog',
  templateUrl: './transaction-details-dialog.component.html',
  styleUrls: ['./transaction-details-dialog.component.scss']
})
export class TransactionDetailsDialogComponent implements OnInit {

    transactionDetailsForm: FormGroup;
    product: Product;
    selectedProduct: Product;
    results: Product[];

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

    search(event) {
        this.productService.getProducts(event.query).subscribe(data => {
            this.results = data;
        });
    }

    ngOnInit(): void {
        // this.transactionService.getTransactions().subscribe(data => this.products = data.products);

        this.transactionDetailsForm = this.formBuilder.group({
            product: new FormControl()
            // name: new FormControl(this.config.data.type === "new" ? "" : this.config.data.transaction.name,
            //     [Validators.maxLength(20), Validators.required]),
            // description: new FormControl(this.config.data.type === "new" ? "" : this.config.data.transaction.description,
            //     [Validators.maxLength(400), Validators.required]),
            // createdAt: new FormControl(new Date())
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
