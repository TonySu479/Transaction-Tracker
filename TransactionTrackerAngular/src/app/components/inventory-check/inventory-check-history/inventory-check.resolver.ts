import {Injectable} from "@angular/core";
import {ActivatedRouteSnapshot, Resolve, RouterStateSnapshot} from "@angular/router";
import {Observable} from "rxjs";
import {InventoryCheckService} from "../../../service/inventory-check.service";

@Injectable({
    providedIn: 'root'
})

export class InventoryResolver implements Resolve<boolean> {
    constructor(private inventoryCheckService: InventoryCheckService) {
    }

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
        const inventoryId = route.params['id'];
        return this.inventoryCheckService.getById(inventoryId);
    }
}
