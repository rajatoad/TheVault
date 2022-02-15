import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AccountDetailComponent } from './components/account/account-detail/account-detail.component';
import { AccountViewComponent } from './components/account/account-view/account-view.component';
import { SelectComponent } from './components/account/select/select.component';
import { LoginComponent } from './components/login-register/login/login.component';
import { RegisterComponent } from './components/login-register/register/register.component';

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
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
