import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Router } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import * as e from 'express';
import { Account } from 'src/app/models/account/account.model';
import { RoutingAllocatorService } from 'src/app/_services/app_control/routing-allocator.service';
import { GlobalStorageService } from 'src/app/_services/global-storage.service';

import { AccountMiniComponent } from './account-mini.component';

export class MockRouter extends RoutingAllocatorService{
  public override login(): void {
    console.log("Yo");
  }
}

export class MockGlobalStorage extends GlobalStorageService{
  public override getActiveAccount(): Account {
    let account:Account = new Account(
      1,
      1,
      "Checking",
      111,
      111
    );
    return account; 
  };

  public override setActiveAccount(account: Account): void {
      return;
  }
}

describe('AccountMiniComponent', () => {
  let component: AccountMiniComponent;
  let fixture: ComponentFixture<AccountMiniComponent>;
  let accountMiniSpy: any;
  let mockGlobalStorage: MockGlobalStorage;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AccountMiniComponent ],
      imports: [HttpClientTestingModule, RouterTestingModule],
      providers: [
        {
          provide: GlobalStorageService,
          userClass: MockGlobalStorage
        },
        {
          provide: RoutingAllocatorService,
          userClass: MockRouter
        }
      ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AccountMiniComponent);
    component = fixture.componentInstance;
    component.account = new Account(
              1,
              1,
              "Checking",
              111,
              111
            );
    fixture.detectChanges();

  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should call setActiveAccount', () => {
    const fixture = TestBed.createComponent(AccountMiniComponent);
    const app = fixture.componentInstance;
    let globalStorage = fixture.debugElement.injector.get(GlobalStorageService);
    let globalSpy = spyOn(globalStorage, "setActiveAccount").and.stub();
    
    let account: Account = new Account(1, 1, "Checking", 1, 1);
    app.goToDetail(account);
    expect(globalSpy).toHaveBeenCalled();
  });

  it('should go to detail page on goToDetail function call', () => {
    const fixture = TestBed.createComponent(AccountMiniComponent);
    const app = fixture.componentInstance;

    let routingAllocator = fixture.debugElement.injector.get(RoutingAllocatorService);
    let routeSpy = spyOn(routingAllocator, "detail").and.stub();
    
    let account: Account = new Account(1, 1, "Checking", 1, 1);
    app.goToDetail(account);
    expect(routeSpy).toHaveBeenCalled();   
  })
});
