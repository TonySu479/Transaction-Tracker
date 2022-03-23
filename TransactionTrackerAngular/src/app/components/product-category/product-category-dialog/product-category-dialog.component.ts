import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {DynamicDialogConfig, DynamicDialogRef} from "primeng/dynamicdialog";

@Component({
  selector: 'app-product-category-dialog',
  templateUrl: './product-category-dialog.component.html',
  styleUrls: ['./product-category-dialog.component.scss']
})
export class ProductCategoryDialogComponent implements OnInit {

    productCategoryForm: FormGroup;

    constructor(private ref: DynamicDialogRef,
                private config: DynamicDialogConfig,
                private formBuilder: FormBuilder) {
    }

    get name() {
        return this.productCategoryForm.controls.name;
    }

    get image() {
        return this.productCategoryForm.controls.image;
    }

    ngOnInit(): void {
        this.productCategoryForm = this.formBuilder.group({
            name: new FormControl(this.config.data.type === "new" ? "" : this.config.data.productCategory.name,
                [Validators.maxLength(50), Validators.required]),
            image: new FormControl()
        });
    }

    submit() {
        if (!this.productCategoryForm.valid) {
            this.productCategoryForm.markAllAsTouched();
            return;
        }

        this.ref.close(this.productCategoryForm.value);
    }

}
