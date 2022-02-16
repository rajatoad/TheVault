package com.revature.thevault.service.dto;
import java.sql.Date;
import java.time.LocalDate;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DepositResponseObject {

int depositId;
 int accountId;
 String depositType;
 String reference;
 LocalDate dateDeposit;
 int amount;
public DepositResponseObject(int depositId, int accountId, String depositType, String reference, LocalDate localDate,
		int amount) {
	super();
	this.depositId = depositId;
	this.accountId = accountId;
	this.depositType = depositType;
	this.reference = reference;
	this.dateDeposit = localDate;
	this.amount = amount;
}
public int getDepositId() {
	return depositId;
}
public void setDepositId(int depositId) {
	this.depositId = depositId;
}
public int getAccountId() {
	return accountId;
}
public void setAccountId(int accountId) {
	this.accountId = accountId;
}
public String getDepositType() {
	return depositType;
}
public void setDepositType(String depositType) {
	this.depositType = depositType;
}
public String getReference() {
	return reference;
}
public void setReference(String reference) {
	this.reference = reference;
}
public LocalDate getDateDeposit() {
	return dateDeposit;
}
public void setDateDeposit(LocalDate dateDeposit) {
	this.dateDeposit = dateDeposit;
}
public int getAmount() {
	return amount;
}
public void setAmount(int amount) {
	this.amount = amount;
}

}
 

