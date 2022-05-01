import {Component, OnInit} from '@angular/core';
import {Transaction} from "../../model/transaction";
import {ConfirmationService, MessageService} from "primeng/api";
import {DialogService} from "primeng/dynamicdialog";
import {TransactionService} from "../../service/transactionservice";
import {Router} from "@angular/router";

@Component({
    selector: 'app-transactions',
    templateUrl: './transactions.component.html',
    styleUrls: ['./transactions.component.scss'],
    providers: [MessageService, ConfirmationService, DialogService]
})
export class TransactionsComponent implements OnInit {


    deleteTransactionDialog: boolean = false;

    deleteTransactionsDialog: boolean = false;

    transactions: Transaction[];

    selectedTransactions: Transaction[];

    statuses: any[];

    rowsPerPageOptions = [5, 10, 20];

    constructor(
        private transactionService: TransactionService,
        private messageService: MessageService,
        private confirmationService: ConfirmationService,
        private dialogService: DialogService,
        private router: Router
    ) {
    }

    ngOnInit() {
        this.transactionService.getTransactions().subscribe(data => {
            this.transactions = data
        });

    }

    deleteSelectedTransactions() {
        this.confirmationService.confirm({
            message: `Are you sure you want to delete selected transactions?`,
            header: 'Confirmation',
            icon: 'fa fa-question-circle',
            accept: () => {
                this.transactionService.deleteTransactions(this.selectedTransactions.map(transaction => transaction.id)).subscribe(() => {
                    this.transactions = this.transactions.filter(
                        (val) => !this.selectedTransactions.includes(val)
                    );
                    this.selectedTransactions = null;
                    this.messageService.add({
                        severity: "success",
                        summary: "transaction deleted",
                        detail: "Selected transactions have been deleted"
                    });
                })
            }
        });
    }

    editTransaction(transaction: Transaction) {
        this.router.navigate(['/', 'transaction-details', transaction.id]);
    }

    deleteTransaction(transaction: Transaction) {
        this.confirmationService.confirm({
            message: "Are you sure you want to delete this transaction?",
            header: 'Confirmation',
            icon: 'fa fa-question-circle',
            accept: () => {
                this.transactionService.delete(transaction).subscribe(() => {
                    this.transactions = this.transactions.filter(p => p.id != transaction.id);
                    this.messageService.add({
                        severity: "success",
                        summary: "transaction deleted",
                        detail: "transaction has been deleted"
                    });
                })
            }
        });
    }

    hideDialog() {

    }

    saveTransaction() {

    }

    findIndexById(id: string): number {
        let index = -1;
        for (let i = 0; i < this.transactions.length; i++) {
            if (this.transactions[i].id === id) {
                index = i;
                break;
            }
        }

        return index;
    }

}
