import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddDetailsComponent } from './components/add-details/add-details.component';
import { AddTransactionComponent } from './components/add-transaction/add-transaction.component';
import { TransactionsListComponent } from './components/transactions-list/transactions-list.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';

const routes: Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full'},
  { path: 'home', component: HomeComponent },
  { path: 'transactions', component: TransactionsListComponent },
  { path: 'transactions/:id', component: AddDetailsComponent },
  { path: 'add', component: AddTransactionComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent }

];

@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash : true })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
