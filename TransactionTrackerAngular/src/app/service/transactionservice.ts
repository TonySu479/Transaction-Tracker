import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Transaction} from "../api/transaction";


@Injectable()
export class TransactionService {
    baseUrl = "http://localhost:8080/api/transactions";

    constructor(private http: HttpClient) { }

    getTransactions(name= "") {
        if(name != ""){
            return this.http.get<any>(this.baseUrl + `?name=${name}`);
        }
        return this.http.get<any>(this.baseUrl);

    }

    create(transaction: Transaction){
        return this.http.post<Transaction>(this.baseUrl, transaction);
    }

    delete(transaction: Transaction){
        return this.http.delete<Transaction>(`${this.baseUrl}/${transaction.id}`);
    }

    update(transaction: Transaction){
        return this.http.put<Transaction>(`${this.baseUrl}/${transaction.id}`, transaction);
    }

    deleteTransactions(listOfIds : String[]) {
        return this.http.post<Transaction>(`${this.baseUrl}/delete-products`, listOfIds);
    }

}
