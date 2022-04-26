import {Component, OnInit} from '@angular/core';
import {DynamicDialogConfig, DynamicDialogRef} from "primeng/dynamicdialog";
import {FormBuilder} from "@angular/forms";
import {ProductCategoryService} from "../../../service/product-category.service";
import {ProductDTO} from "../../../model/productDTO";

@Component({
    selector: 'app-inventory-check-dialog',
    templateUrl: './inventory-check-dialog.component.html',
    styleUrls: ['./inventory-check-dialog.component.scss']
})
export class InventoryCheckDialogComponent implements OnInit {

    productQuantityDifferences: ProductDTO[] = [];

    constructor(private ref: DynamicDialogRef,
                private config: DynamicDialogConfig,
                private formBuilder: FormBuilder,
                private productCategoryService: ProductCategoryService) {
    }

    ngOnInit(): void {
        this.productQuantityDifferences = this.config.data.productReport;
        console.log("product report: " + this.config.data.productReport);
        console.log(this.productQuantityDifferences);
    }

    confirmInventory() {

    }
}
