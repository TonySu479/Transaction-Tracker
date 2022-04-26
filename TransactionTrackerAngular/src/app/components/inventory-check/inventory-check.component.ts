import {Component, OnInit} from '@angular/core';
import {ConfirmationService, MessageService} from "primeng/api";
import {DialogService} from "primeng/dynamicdialog";
import {ProductService} from "../../service/productservice";

class Products {
}

@Component({
    selector: 'app-inventory-check',
    templateUrl: './inventory-check.component.html',
    styleUrls: ['./inventory-check.component.scss'],
    providers: [MessageService, ConfirmationService, DialogService]
})
export class InventoryCheckComponent implements OnInit {

    products: Products[] = [];

    constructor(
        private productService: ProductService,
        private confirmationService: ConfirmationService
    ) {
    }

    ngOnInit(): void {
        this.productService.getProducts().subscribe(data => this.products = data.map(item => {
            return {
                ...item,
                quantity: 0
            }
        }));
    }

    inventoryCheck() {
        this.confirmationService.confirm({
            message: `Are you sure these are the amounts for the inventory check?`,
            header: 'Confirmation',
            icon: 'fa fa-question-circle',
            accept: () => {
                this.productService.inventoryCheck(this.products).subscribe(
                    data => console.log(data)
                );
            }
        });

    }

}
