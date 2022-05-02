import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Product} from "../model/product";
import {ProductDTO} from "../model/productDTO";

@Injectable()
export class InventoryCheckDetailsService {
    baseUrl = "http://localhost:8080/api/inventory-check-details";

    constructor(private http: HttpClient) {
    }

    inventoryCheck(products: Product[]) {
        return this.http.post<ProductDTO[]>(`${this.baseUrl}`, products)
    }

    getInventoryCheckDetailsByInventoryCheckId(inventoryCheckId) {
        return this.http.get<any>(this.baseUrl + `?id=${inventoryCheckId}`);
    }

//     create(transactionDetails: TransactionDetail){
//         return this.http.post<TransactionDetail>(this.baseUrl, transactionDetails);
//     }
//
//     createTransactionDetails(transactionDetailsListDTO: TransactionDetailsListDTO){
//         return this.http.post<any>(`${this.baseUrl}/cashier`, transactionDetailsListDTO);
//     }
//
//
//     delete(transactionDetails: TransactionDetail){
//         return this.http.delete<TransactionDetail>(`${this.baseUrl}/${transactionDetails.id}`);
//     }
//
//     update(transactionDetails: TransactionDetail){
//         return this.http.put<TransactionDetail>(`${this.baseUrl}/${transactionDetails.id}`, transactionDetails);
//     }
//
//     deleteTransactions(listOfIds : String[]) {
//         return this.http.post<TransactionDetail>(`${this.baseUrl}/delete-transaction-details`, listOfIds);
//     }
}