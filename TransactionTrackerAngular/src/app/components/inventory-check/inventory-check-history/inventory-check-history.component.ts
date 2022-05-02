import {Component, OnInit} from '@angular/core';
import {InventoryCheck} from "../../../model/inventory-check";
import {InventoryCheckService} from "../../../service/inventory-check.service";
import {ConfirmationService, MessageService} from "primeng/api";
import {DialogService} from "primeng/dynamicdialog";
import {Router} from "@angular/router";

@Component({
    selector: 'app-inventory-check-history',
    templateUrl: './inventory-check-history.component.html',
    styleUrls: ['./inventory-check-history.component.scss'],
    providers: [MessageService, ConfirmationService, DialogService]
})
export class InventoryCheckHistoryComponent implements OnInit {

    inventoryChecks: InventoryCheck[];

    constructor(private inventoryCheckService: InventoryCheckService,
                private messageService: MessageService,
                private confirmationService: ConfirmationService,
                private router: Router) {
    }

    ngOnInit(): void {
        this.inventoryCheckService.getInventoryChecks().subscribe(data => {
            this.inventoryChecks = data;
        })
    }

    viewInventoryCheckDetail(inventoryCheck): void {
        this.router.navigate(['/', 'inventory-check-details', inventoryCheck.id]);
    }

}
