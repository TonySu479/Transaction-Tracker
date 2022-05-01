import {Component, OnInit} from '@angular/core';
import {DialogService, DynamicDialogConfig, DynamicDialogRef} from "primeng/dynamicdialog";
import {FormBuilder} from "@angular/forms";
import {ProductCategoryService} from "../../../service/product-category.service";
import {ProductDTO} from "../../../model/productDTO";
import {ConfirmationService, MessageService} from "primeng/api";

@Component({
    selector: 'app-inventory-check-dialog',
    templateUrl: './inventory-check-dialog.component.html',
    styleUrls: ['./inventory-check-dialog.component.scss'],
    providers: [MessageService, ConfirmationService, DialogService]
})
export class InventoryCheckDialogComponent implements OnInit {

    productQuantityDifferences: ProductDTO[] = [];

    constructor(private ref: DynamicDialogRef,
                private config: DynamicDialogConfig,
                private formBuilder: FormBuilder,
                private productCategoryService: ProductCategoryService,
                private confirmationService: ConfirmationService,
                private dialogService: DialogService) {
    }

    ngOnInit(): void {
        this.productQuantityDifferences = this.config.data.productReport;
    }

    confirmInventory() {
        this.confirmationService.confirm({
            message: 'Please note that confirming updates all the product amounts with the new values submitted in the inventory check, It is only recommended to click yes if you are satisfied with the results of the inventory check ',
            header: 'Confirmation',
            icon: 'fa fa-question-circle',
            accept: () => {
                this.ref.close(true);
            }
        });
    }

    FixInventoryCheck() {
        this.ref.close();
    }
}
