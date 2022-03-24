import {RouterModule} from '@angular/router';
import {NgModule} from '@angular/core';
import {DashboardComponent} from './components/dashboard/dashboard.component';
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

@NgModule({
    imports: [
        RouterModule.forRoot([
            {
                path: '', component: AppMainComponent, canActivate: [AuthGuard],
                children: [
                    {path: '', component: DashboardComponent},
                    {path: 'pages/empty', component: EmptyComponent},
                    {path: 'products', component: ProductsComponent},
                    {path: 'product-categories', component: ProductCategoryComponent},
                    {path: 'transactions', component: TransactionsComponent}
                ],
            },
            {path: 'login', component: LoginComponent},
            {path: 'register', component: RegisterComponent},
            {path: '**', redirectTo: 'pages/notfound'},
        ], {scrollPositionRestoration: 'enabled', anchorScrolling: 'enabled'})
    ],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
