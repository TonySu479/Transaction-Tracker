import {Component, OnInit} from '@angular/core';
import {ConfirmationService, MessageService} from 'primeng/api';
import {Product} from 'src/app/api/product';
import {ProductService} from 'src/app/service/productservice';
import {DialogService} from "primeng/dynamicdialog";
import {ProductDialogComponent} from "./product-dialog/product-dialog.component";

@Component({
    selector: 'app-products',
    templateUrl: './products.component.html',
    styleUrls: ['./products.component.scss'],
    providers: [MessageService, ConfirmationService, DialogService]
})
export class ProductsComponent implements OnInit {

    deleteProductDialog: boolean = false;

    deleteProductsDialog: boolean = false;

    products: Product[];

    selectedProducts: Product[];

    statuses: any[];

    rowsPerPageOptions = [5, 10, 20];

    constructor(
        private productService: ProductService,
        private messageService: MessageService,
        private confirmationService: ConfirmationService,
        private dialogService: DialogService
    ) {
    }

    ngOnInit() {
        this.productService.getProducts().subscribe(data => this.products = data);
    }

    openNew() {
        const ref = this.dialogService.open(ProductDialogComponent, {
            header: 'Create a new product',
            data: {
                type: "new"
            },
            width: "600px"
        });

        ref.onClose.subscribe(value => {
            console.log("hello");
            if(!value){
                return;
            }
            this.productService.create(value)
                .subscribe(data => {
                    this.products.push(data);
                    this.messageService.add({severity:"success", summary:"product created", detail:`${data.name} created`});
                })
        });
    }

    deleteSelectedProducts() {
        this.confirmationService.confirm({
            message: `Are you sure you want to delete selected products?`,
            header: 'Confirmation',
            icon: 'fa fa-question-circle',
            accept: () => {
                this.productService.deleteProducts(this.selectedProducts.map(product => product.id)).subscribe(() => {
                        this.products = this.products.filter(
                            (val) => !this.selectedProducts.includes(val)
                        );
                        this.selectedProducts = null;
                    this.messageService.add({severity:"success", summary:"product deleted", detail:`Selected products have been deleted`});
                })
            }
        });
    }

    editProduct(product: Product) {
        const ref = this.dialogService.open(ProductDialogComponent, {
            header: 'Edit a product',
            data: {
                type: "edit",
                product: product
            },
            width: "600px"
        });

        ref.onClose.subscribe(value => {
            if(!value){
                return;
            }
            this.productService.update({...value, id: product.id})
                .subscribe((data: Product) => {
                    let index = this.products.findIndex(product => product.id === data.id);
                    this.products[index] = data;
                    this.messageService.add({severity:"success", summary:"product edited", detail:`${data.name} edited`});
                })
        })
    }

    deleteProduct(product: Product) {
        this.confirmationService.confirm({
            message: `Are you sure you want to delete ${product.name}?`,
            header: 'Confirmation',
            icon: 'fa fa-question-circle',
            accept: () => {
                this.productService.delete(product).subscribe(() => {
                    this.products = this.products.filter(p => p.id != product.id);
                    this.messageService.add({severity:"success", summary:"product deleted", detail:`${product.name} has been deleted`});
                })
            }
        });
    }

    hideDialog() {

    }

    saveProduct() {

    }

    findIndexById(id: string): number {
        let index = -1;
        for (let i = 0; i < this.products.length; i++) {
            if (this.products[i].id === id) {
                index = i;
                break;
            }
        }

        return index;
    }

}
