import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppBootstrapComponent } from './app-bootstrap/app-bootstrap.component';
import { AccountProfileComponent } from './components/account-profile/account-profile.component';
import { AccountDetailComponent } from './components/account/account-detail/account-detail.component';
// import { AccountTransactionsComponent } from './components/account/account-transactions/account-transactions.component';
import { TransactionHistoryComponent } from './components/account/account-transactions/history/transaction-history/transaction-history.component';
import { AccountViewComponent } from './components/account/account-view/account-view.component';
import { CreateAccountComponent } from './components/account/create-account/create-account.component';
import { SelectComponent } from './components/account/select/select.component';
import { EditProfileComponent } from './components/edit-profile/edit-profile.component';
import { LoginComponent } from './components/login-register/login/login.component';
import { RegisterComponent } from './components/login-register/register/register.component';
import { DepositGenerateComponent } from './components/transaction/deposit-generate/deposit-generate.component';
import { TransferGenerateComponent } from './components/transaction/transfer-generate/transfer-generate.component';
import { WithdrawGenerateComponent } from './components/transaction/withdraw-generate/withdraw-generate.component';

const routes: Routes = [
  {
    component: LoginComponent,
    path: ''
  },
  {
    component: RegisterComponent,
    path: 'register'
  },
  {
    component: AccountViewComponent,
    path: 'account-view'
  },
  {
    component: AccountDetailComponent,
    path: 'account-detail'
  },
  {
    component: AppBootstrapComponent,
    path: 'account-test'
  },
  // {
  //   component: AccountTransactionsComponent,
  //   path: 'account-withdraw'
  // },
  {
    component: TransactionHistoryComponent,
    path: 'account-history'
  },
  {
    component: AccountProfileComponent,
    path: 'profile'
  },
  {
    component: DepositGenerateComponent,
    path: 'deposit'
  },
  {
    component: WithdrawGenerateComponent,
    path: 'withdraw-test'
  },
  {
    component: EditProfileComponent,
    path: 'edit-profile'
  },
  {
    component: TransactionHistoryComponent,
    path:'rickRoll'
  },
  {
    component: TransferGenerateComponent,
    path: 'account-transfer'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
