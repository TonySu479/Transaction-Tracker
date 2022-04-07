import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {TransactionDetail} from "../api/transaction-detail";


@Injectable()
export class TransactionDetailService {
    baseUrl = "http://localhost:8080/api/transaction-details";

    constructor(private http: HttpClient) { }

    getTransactionDetailsByTransactionId(transactionId) {
        return this.http.get<any>(this.baseUrl + `?id=${transactionId}`);
    }

    create(transactionDetails: TransactionDetail){
        return this.http.post<TransactionDetail>(this.baseUrl, transactionDetails);
    }

    createTransactionDetails(transactionDetails: TransactionDetail[]){
        return this.http.post<any>(`${this.baseUrl}/cashier`, transactionDetails);
    }


    delete(transactionDetails: TransactionDetail){
        return this.http.delete<TransactionDetail>(`${this.baseUrl}/${transactionDetails.id}`);
    }

    update(transactionDetails: TransactionDetail){
        return this.http.put<TransactionDetail>(`${this.baseUrl}/${transactionDetails.id}`, transactionDetails);
    }

    deleteTransactions(listOfIds : String[]) {
        return this.http.post<TransactionDetail>(`${this.baseUrl}/delete-transaction-details`, listOfIds);
    }
}
