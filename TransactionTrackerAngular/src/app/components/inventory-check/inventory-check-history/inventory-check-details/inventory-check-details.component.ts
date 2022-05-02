import {Component, OnInit} from '@angular/core';
import {InventoryCheckDetail} from "../../../../model/inventory-check-detail";
import {ConfirmationService, MessageService} from "primeng/api";
import {DialogService} from "primeng/dynamicdialog";
import {InventoryCheck} from "../../../../model/inventory-check";
import {ActivatedRoute} from "@angular/router";
import {InventoryCheckDetailsService} from "../../../../service/inventory-check-details.service";
import jsPDF from 'jspdf'
import 'jspdf-autotable'

@Component({
    selector: 'app-inventory-check-details',
    templateUrl: './inventory-check-details.component.html',
    styleUrls: ['./inventory-check-details.component.scss'],
    providers: [MessageService, ConfirmationService, DialogService]
})
export class InventoryCheckDetailsComponent implements OnInit {

    inventoryCheckDetails: InventoryCheckDetail[] = [];

    inventoryCheck: InventoryCheck;

    cols: any[];

    exportColumns: any[];

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

        this.cols = [
            {field: 'code', header: 'Code'},
            {field: 'name', header: 'Name'},
            {field: 'category', header: 'Category'},
            {field: 'trackedQuantity', header: 'TrackedQuantity'},
            {field: 'inputtedQuantity', header: 'InputtedQuantity'},
            {field: 'difference', header: 'Difference'}
        ];

        this.exportColumns = this.cols.map(col => ({title: col.header, dataKey: col.field}));
    }

    exportPdf() {

        const doc = new jsPDF() as any;
        doc.autoTable(this.exportColumns, this.inventoryCheckDetails.map(detail => {
            return {
                ...detail,
                code: detail.product.code,
                name: detail.product.name,
                category: detail.product.category['name'],
                difference: detail.trackedQuantity - detail.inputtedQuantity
            }
        }));
        doc.save('inventory_check.pdf');

    }

}
