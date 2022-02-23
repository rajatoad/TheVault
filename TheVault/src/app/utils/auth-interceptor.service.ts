import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HTTP_INTERCEPTORS } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { GlobalStorageService } from '../_services/global-storage.service';

@Injectable()
export class AuthInterceptorService implements HttpInterceptor{

  constructor(
    private globalStorage: GlobalStorageService
  ) { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    let authReq = req;
    const token = this.globalStorage.getToken();
    if(token != null){
      authReq = req.clone({headers: req.headers.set('Authorization', 'Bearer ' + token)});
    }
    return next.handle(authReq);
  }

}
