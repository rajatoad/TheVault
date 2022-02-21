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
	float amount;
}
 

