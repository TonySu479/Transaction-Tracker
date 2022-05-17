import {Component, OnInit} from '@angular/core';
import {ConfirmationService, MessageService} from "primeng/api";
import {DialogService} from "primeng/dynamicdialog";
import {ProductCategory} from "../../model/product-category";
import {ProductCategoryService} from "../../service/product-category.service";
import {ProductCategoryDialogComponent} from "./product-category-dialog/product-category-dialog.component";

@Component({
    selector: 'app-category-category',
    templateUrl: './product-category.component.html',
    styleUrls: ['./product-category.component.scss'],
    providers: [MessageService, ConfirmationService, DialogService]
})
export class ProductCategoryComponent implements OnInit {

    deleteCategoryDialog: boolean = false;

    deleteCategoriesDialog: boolean = false;

    categories: ProductCategory[];

    selectedProductCategories: ProductCategory[];

    statuses: any[];

    rowsPerPageOptions = [5, 10, 20];

    constructor(
        private categoryService: ProductCategoryService,
        private messageService: MessageService,
        private confirmationService: ConfirmationService,
        private dialogService: DialogService
    ) {
    }

    ngOnInit() {
        this.categoryService.getProductCategories().subscribe(data => this.categories = data);
    }

    openNew() {
        const ref = this.dialogService.open(ProductCategoryDialogComponent, {
            header: 'Create a new category',
            data: {
                type: "new"
            },
            width: "600px"
        });

        ref.onClose.subscribe(value => {
            if (!value) {
                return;
            }
            this.categoryService.create(value)
                .subscribe(data => {
                    this.categories.push(data);
                    this.messageService.add({
                        severity: "success",
                        summary: "category created",
                        detail: `${data.name} created`
                    });
                })
        });
    }

    deleteSelectedCategories() {
        this.confirmationService.confirm({
            message: `Are you sure you want to delete selected categories?`,
            header: 'Confirmation',
            icon: 'fa fa-question-circle',
            accept: () => {
                this.categoryService.deleteProductCategories(this.selectedProductCategories.map(category => category.id)).subscribe(() => {
                    this.categories = this.categories.filter(
                        (val) => !this.selectedProductCategories.includes(val)
                    );
                    this.selectedProductCategories = null;
                    this.messageService.add({
                        severity: "success",
                        summary: "category deleted",
                        detail: `Selected categories have been deleted`
                    });
                })
            }
        });
    }

    editProductCategory(productCategory: ProductCategory) {
        const ref = this.dialogService.open(ProductCategoryDialogComponent, {
            header: 'Edit category',
            data: {
                type: "edit",
                productCategory: productCategory
            },
            width: "600px"
        });

        ref.onClose.subscribe(value => {
            if (!value) {
                return;
            }
            this.categoryService.update({...value, id: productCategory.id})
                .subscribe((data: ProductCategory) => {
                    let index = this.categories.findIndex(productCategory => productCategory.id === data.id);
                    this.categories[index] = data;
                    this.messageService.add({
                        severity: "success",
                        summary: "category edited",
                        detail: `${data.name} edited`
                    });
                }, (error) => {
                    console.log(error);
                    this.messageService.add({
                        severity: "error",
                        summary: "Unable to delete categories",
                        detail: `${error.error.message || error.message}`
                    });
                })
        })

    }

    deleteProductCategory(category: ProductCategory) {
        this.confirmationService.confirm({
            message: `Are you sure you want to delete ${category.name}?`,
            header: 'Confirmation',
            icon: 'fa fa-question-circle',
            accept: () => {
                this.categoryService.delete(category).subscribe(() => {
                    this.categories = this.categories.filter(p => p.id != category.id);
                    this.messageService.add({
                        severity: "success",
                        summary: "category deleted",
                        detail: `${category.name} has been deleted`
                    });
                }, (error) => {
                    console.log(error);
                    this.messageService.add({
                        severity: "error",
                        summary: "Unable to delete category",
                        detail: `${error.error.message || error.message}`
                    });
                })
            }
        });
    }

    hideDialog() {

    }

    saveCategory() {

    }

    findIndexById(id: string): number {
        let index = -1;
        for (let i = 0; i < this.categories.length; i++) {
            if (this.categories[i].id === id) {
                index = i;
                break;
            }
        }

        return index;
    }

}
