import { Injectable } from '@angular/core';
import { InMemoryDbService } from 'angular-in-memory-web-api';

@Injectable({
  providedIn: 'root'
})
export class InMemoryDataService implements InMemoryDbService {
createDb() {
const transactions = [
{depositid: 1, accountid: 1, reference: "check",depositDate: 1644901200000, amount: 5000},
{depositid: 2, accountid: 1, reference: "check 2",depositDate: 1644901200000, amount: 3000 },
{depositid: 1, accountid: 1, reference: "crypto",depositDate: 1644901200000, amount: 3423 },
{depositid: 1, accountid: 1, reference: "alimony",depositDate: 1644901200000, amount: 5332 },
{depositid: 1, accountid: 1, reference: "settlement",depositDate: 1644901200000, amount: 235324 },
{depositid: 1, accountid: 1, reference: "Lottery",depositDate: 1644901200000, amount: 23432432}



]
  return {transactions};
}
  constructor() { }
}
