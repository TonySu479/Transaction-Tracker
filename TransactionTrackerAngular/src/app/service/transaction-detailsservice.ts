import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {TransactionDetails} from "../api/transaction-details";


@Injectable()
export class TransactionDetailsService {
    baseUrl = "http://localhost:8080/api/transaction-details";

    constructor(private http: HttpClient) { }

    getTransactionDetailsByTransactionId(transactionId= "") {
        return this.http.get<any>(this.baseUrl + `?transactionId=${transactionId}`);
    }

    create(transactionDetails: TransactionDetails){
        console.log("test");
        return this.http.post<TransactionDetails>(this.baseUrl, transactionDetails);
    }

    delete(transactionDetails: TransactionDetails){
        return this.http.delete<TransactionDetails>(`${this.baseUrl}/${transactionDetails.id}`);
    }

    update(transactionDetails: TransactionDetails){
        return this.http.put<TransactionDetails>(`${this.baseUrl}/${transactionDetails.id}`, transactionDetails);
    }

    deleteTransactions(listOfIds : String[]) {
        return this.http.post<TransactionDetails>(`${this.baseUrl}/delete-transaction-details`, listOfIds);
    }
}
