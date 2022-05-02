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
}
