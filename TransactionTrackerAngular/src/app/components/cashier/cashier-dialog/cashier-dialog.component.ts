import {Component, OnInit} from '@angular/core';
import {Shift} from "../../../model/shift";
import {DynamicDialogConfig} from "primeng/dynamicdialog";

@Component({
    selector: 'app-cashier-dialog',
    templateUrl: './cashier-dialog.component.html',
    styleUrls: ['./cashier-dialog.component.scss']
})
export class CashierDialogComponent implements OnInit {

    constructor(private config: DynamicDialogConfig) {
    }

    shift: Shift;

    ngOnInit(): void {
        this.shift = this.config.data.shift;
    }

}
