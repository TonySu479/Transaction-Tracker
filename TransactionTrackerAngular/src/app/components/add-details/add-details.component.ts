import { Component, Input, OnInit } from '@angular/core';
import { TransactionTrackerService } from 'src/app/services/transaction-tracker.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Transaction } from 'src/app/models/transaction.model';

@Component({
  selector: 'app-add-details',
  templateUrl: './add-details.component.html',
  styleUrls: ['./add-details.component.scss']
})
export class AddDetailsComponent implements OnInit {

  @Input() viewMode = false;
  @Input() currentTransaction: Transaction = {
    title: '',
    description: ''
  };
  message = '';
  constructor(
    private transactionService: TransactionTrackerService,
    private route: ActivatedRoute,
    private router: Router) { }
  ngOnInit(): void {
    if (!this.viewMode) {
      this.message = '';
      this.getTransaction(this.route.snapshot.params["id"]);
    }
  }
  getTransaction(id: string): void {
    this.transactionService.get(id)
      .subscribe({
        next: (data) => {
          this.currentTransaction = data;
          console.log(data);
        },
        error: (e) => console.error(e)
      });
  }
  updateTransaction(): void {
    this.message = '';
    this.transactionService.update(this.currentTransaction.id, this.currentTransaction)
      .subscribe({
        next: (res) => {
          console.log(res);
          this.message = res.message ? res.message : 'This transaction was updated successfully!';
        },
        error: (e) => console.error(e)
      });
  }
  deleteTransaction(): void {
    this.transactionService.delete(this.currentTransaction.id)
      .subscribe({
        next: (res) => {
          console.log(res);
          this.router.navigate(['/transactions']);
        },
        error: (e) => console.error(e)
      });
  }

}
