import {ActivatedRouteSnapshot, Resolve, Router, RouterStateSnapshot} from "@angular/router";
import {Injectable} from "@angular/core";
import {Observable} from "rxjs";
import {TransactionService} from "../../service/transactionservice";

@Injectable({
    providedIn: 'root'
})

export class TransactionResolver implements Resolve<boolean> {
    constructor(private transactionService: TransactionService) {
    }

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
        const transactionId = route.params['id'];
        return this.transactionService.getById(transactionId);
    }

}
