import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './components/login-register/login/login.component';
import { RegisterComponent } from './components/login-register/register/register.component';
import { SelectComponent } from './components/account/select/select.component';
import { AccountMiniComponent } from './components/account/account-mini/account-mini.component';
import { AccountDetailComponent } from './components/account/account-detail/account-detail.component';
import { AccountViewComponent } from './components/account/account-view/account-view.component';
import { AccountInfoComponent } from './components/account/account-detail/account-info/account-info.component';
import { AppBootstrapModule } from './app-bootstrap/app-bootstrap.module';
import { AppBootstrapComponent } from './app-bootstrap/app-bootstrap.component';
import { TransactionHistoryComponent } from './components/account/account-transactions/history/transaction-history/transaction-history.component';
import { SelectTransactionComponent } from './components/transaction/select-transaction/select-transaction.component';
import { AccountProfileComponent } from './components/account-profile/account-profile.component';
import { WithdrawGenerateComponent } from './components/transaction/withdraw-generate/withdraw-generate.component';
import { DepositGenerateComponent } from './components/transaction/deposit-generate/deposit-generate.component';
import { DepositDetailComponent } from './components/transaction/deposit-detail/deposit-detail.component';
import { WithdrawDetailComponent } from './components/transaction/withdraw-detail/withdraw-detail.component';
import { EditProfileComponent } from './components/edit-profile/edit-profile.component';
import { TransactionSearchComponent } from './components/transaction/select-transaction/transaction-search/transaction-search.component';
import { Ng2SearchPipeModule} from 'ng2-search-filter';
import { CreateAccountComponent } from './components/account/create-account/create-account.component';
import { TransferGenerateComponent } from './components/transaction/transfer-generate/transfer-generate.component';

import { AuthInterceptorService } from './utils/auth-interceptor.service';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    SelectComponent,
    AccountMiniComponent,
    AccountDetailComponent,
    AccountViewComponent,
    AccountInfoComponent,
    AppBootstrapComponent,
    TransactionHistoryComponent,
    SelectTransactionComponent,
    AccountProfileComponent,
    WithdrawGenerateComponent,
    DepositGenerateComponent,
    DepositDetailComponent,
    WithdrawDetailComponent,
    EditProfileComponent,
    TransactionSearchComponent,
    CreateAccountComponent,
    WithdrawDetailComponent,
    TransferGenerateComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    FormsModule,
    HttpClientModule,
    AppBootstrapModule,
    Ng2SearchPipeModule
  ],
  providers: [{
    provide: HTTP_INTERCEPTORS,
    useClass: AuthInterceptorService,
    multi: true

  }],
  bootstrap: [AppComponent]
})
export class AppModule { }
platformBrowserDynamic().bootstrapModule(AppModule)
