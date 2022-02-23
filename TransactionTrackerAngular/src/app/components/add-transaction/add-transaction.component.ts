import { Component, OnInit } from '@angular/core';
import { Transaction } from 'src/app/models/transaction.model';
import { TransactionTrackerService } from 'src/app/services/transaction-tracker.service';

@Component({
  selector: 'app-add-transaction',
  templateUrl: './add-transaction.component.html',
  styleUrls: ['./add-transaction.component.scss']
})
export class AddTransactionComponent implements OnInit {
  transaction: Transaction = {
    title: '',
    description: ''
  };
  submitted = false;
  constructor(private transactionTrackerService: TransactionTrackerService) { }

  ngOnInit(): void {
  }

  saveTransaction(): void {
    const data = {
      title: this.transaction.title,
      description: this.transaction.description
    };
    this.transactionTrackerService.create(data)
      .subscribe({
        next: (res) => {
          console.log(res);
          this.submitted = true;
        },
        error: (e) => console.error(e)
      });
  }

  newTransaction(): void {
    this.submitted = false;
    this.transaction = {
      title: '',
      description: ''
    };
  }

}
