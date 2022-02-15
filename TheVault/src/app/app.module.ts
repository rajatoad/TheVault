import { HttpClientModule } from '@angular/common/http';
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


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    SelectComponent,
    AccountMiniComponent,
    AccountDetailComponent,
    AccountViewComponent,
    AccountInfoComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
platformBrowserDynamic().bootstrapModule(AppModule)