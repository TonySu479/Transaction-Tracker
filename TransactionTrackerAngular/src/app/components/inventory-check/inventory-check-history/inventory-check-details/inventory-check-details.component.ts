import {Component, OnInit} from '@angular/core';
import {InventoryCheckDetail} from "../../../../model/inventory-check-detail";
import {ConfirmationService, MessageService} from "primeng/api";
import {DialogService} from "primeng/dynamicdialog";
import {InventoryCheck} from "../../../../model/inventory-check";
import {ActivatedRoute} from "@angular/router";
import {InventoryCheckDetailsService} from "../../../../service/inventory-check-details.service";

@Component({
    selector: 'app-inventory-check-details',
    templateUrl: './inventory-check-details.component.html',
    styleUrls: ['./inventory-check-details.component.scss'],
    providers: [MessageService, ConfirmationService, DialogService]
})
export class InventoryCheckDetailsComponent implements OnInit {

    inventoryCheckDetails: InventoryCheckDetail[] = [];

    inventoryCheck: InventoryCheck;

    constructor(private messageService: MessageService,
                private confirmationService: ConfirmationService,
                private route: ActivatedRoute,
                private inventoryCheckDetailsService: InventoryCheckDetailsService) {
    }

    ngOnInit(): void {
        this.inventoryCheck = this.route.snapshot.data.inventoryCheck;
        this.inventoryCheckDetailsService.getInventoryCheckDetailsByInventoryCheckId(this.inventoryCheck.id).subscribe(
            data => this.inventoryCheckDetails = data
        );
    }

}
