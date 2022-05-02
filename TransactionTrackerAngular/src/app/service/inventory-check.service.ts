import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {map} from "rxjs/operators";

@Injectable()
export class InventoryCheckService {
    baseUrl = "http://localhost:8080/api/inventory-check";

    constructor(private http: HttpClient) {
    }

    getInventoryChecks() {
        return this.http.get<any>(this.baseUrl).pipe(
            map(inventoryChecks => {
                let newInventoryChecks: any[] = [];
                inventoryChecks.forEach(inventoryChecks => {
                    newInventoryChecks.push(
                            {
                                ...inventoryChecks,
                                createdAt: new Date(inventoryChecks.createdAt).toLocaleString()
                            }
                        )
                    }
                )
                return newInventoryChecks;
            })
        );
    }


    getById(id) {
        return this.http.get<any>(`${this.baseUrl}/${id}`).pipe(map(inventoryCheck => {
            return {
                ...inventoryCheck,
                createdAt: new Date(inventoryCheck.createdAt)
            }
        }));
    }

}
