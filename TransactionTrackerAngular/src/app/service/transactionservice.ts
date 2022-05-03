import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Transaction} from "../model/transaction";
import {map} from "rxjs/operators";


@Injectable()
export class TransactionService {
    baseUrl = "http://localhost:8080/api/transactions";

    constructor(private http: HttpClient) {
    }

    getTransactions() {
        return this.http.get<any>(this.baseUrl).pipe(
            map(transactions => {
                let newTransactions: any[] = [];
                if(!transactions) {
                    return newTransactions;
                }
                transactions.forEach(transaction => {
                        newTransactions.push(
                            {
                                ...transaction,
                                createdAt: new Date(transaction.createdAt).toLocaleString()
                            }
                        )
                    }
                )
                return newTransactions;
            })
        );
    }

    getById(id) {
        return this.http.get<any>(`${this.baseUrl}/${id}`).pipe(map(transaction => {
            return {
                ...transaction,
                createdAt: new Date(transaction.createdAt)
            }
        }));
    }

    create(transaction: Transaction) {
        return this.http.post<Transaction>(this.baseUrl, transaction);
    }

    delete(transaction: Transaction) {
        return this.http.delete<Transaction>(`${this.baseUrl}/${transaction.id}`);
    }

    update(transaction: Transaction) {
        return this.http.put<Transaction>(`${this.baseUrl}/${transaction.id}`, transaction);
    }

    deleteTransactions(listOfIds: String[]) {
        return this.http.post<Transaction>(`${this.baseUrl}/delete-transactions`, listOfIds);
    }

}
