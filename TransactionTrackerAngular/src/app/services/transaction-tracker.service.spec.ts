import { TestBed } from '@angular/core/testing';

import { TransactionTrackerService } from './transaction-tracker.service';

describe('TransactionTrackerService', () => {
  let service: TransactionTrackerService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TransactionTrackerService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
