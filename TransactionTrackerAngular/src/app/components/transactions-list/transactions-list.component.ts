import { Component, OnInit } from '@angular/core';
import { Transaction } from 'src/app/models/transaction.model';
import { TransactionTrackerService } from 'src/app/services/transaction-tracker.service';

@Component({
  selector: 'app-transactions-list',
  templateUrl: './transactions-list.component.html',
  styleUrls: ['./transactions-list.component.scss']
})
export class TransactionsListComponent implements OnInit {

transactions?: Transaction[];
  currentTransaction: Transaction = {};
  currentIndex = -1;
  title = '';
  constructor(private transactionTrackerService: TransactionTrackerService) { }
  ngOnInit(): void {
    this.retrieveTutorials();
  }
  retrieveTutorials(): void {
    this.transactionTrackerService.getAll()
      .subscribe({
        next: (data) => {
          this.transactions = data;
          console.log(data);
        },
        error: (e) => console.error(e)
      });
  }
  refreshList(): void {
    this.retrieveTutorials();
    this.currentTransaction = {};
    this.currentIndex = -1;
  }
  setActiveTransaction(transaction: Transaction, index: number): void {
    this.currentTransaction = transaction;
    this.currentIndex = index;
  }
  removeAllTransactions(): void {
    this.transactionTrackerService.deleteAll()
      .subscribe({
        next: (res) => {
          console.log(res);
          this.refreshList();
        },
        error: (e) => console.error(e)
      });
  }
  searchTitle(): void {
    this.currentTransaction = {};
    this.currentIndex = -1;
    this.transactionTrackerService.findByTitle(this.title)
      .subscribe({
        next: (data) => {
          this.transactions = data;
          console.log(data);
        },
        error: (e) => console.error(e)
      });
  }

}
