import {RouterModule} from '@angular/router';
import {NgModule} from '@angular/core';
import {EmptyComponent} from './components/empty/empty.component';
import {AppMainComponent} from './app.main.component';
import {LoginComponent} from './components/login/login.component';
import {ProductsComponent} from './components/products/products.component';
import {RegisterComponent} from "./components/register/register.component";
import {
    AuthGuardService as AuthGuard
} from './service/auth-guard.service';
import {ProductCategoryComponent} from "./components/product-category/product-category.component";
import {TransactionsComponent} from "./components/transactions/transactions.component";
import {TransactionDetailsComponent} from "./components/transactions/transaction-details/transaction-details.component";
import {TransactionResolver} from "./components/transactions/transaction.resolver";
import {CashierComponent} from "./components/cashier/cashier.component";
import {InventoryCheckComponent} from "./components/inventory-check/inventory-check.component";
import {
    InventoryCheckHistoryComponent
} from "./components/inventory-check/inventory-check-history/inventory-check-history.component";

@NgModule({
    imports: [
        RouterModule.forRoot([
            {
                path: '', component: AppMainComponent, canActivate: [AuthGuard],
                children: [
                    {path: 'pages/empty', component: EmptyComponent},
                    {path: 'products', component: ProductsComponent},
                    {path: 'product-categories', component: ProductCategoryComponent},
                    {path: 'transactions', component: TransactionsComponent},
                    {path: 'transaction-details', component: TransactionDetailsComponent},
                    {path: 'transaction-details/:id', component: TransactionDetailsComponent, resolve: {transaction: TransactionResolver }},
                    {path: 'inventory-check', component: InventoryCheckComponent },
                    {path: 'inventory-check-history', component: InventoryCheckHistoryComponent },
                ],
            },
            {path: 'login', component: LoginComponent},
            {path: 'register', component: RegisterComponent},
            {path: 'cashier', component: CashierComponent },
            {path: '**', redirectTo: 'pages/notfound'},
        ], {scrollPositionRestoration: 'enabled', anchorScrolling: 'enabled'})
    ],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
