package com.revature.thevault.repository.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.sql.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "withdraw_table")
public class WithdrawEntity {
	@Id
	@Column(name="pk_withdraw_id")
	@GeneratedValue(generator = "withdraw_table_pk_withdraw_id_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(allocationSize = 1, name = "withdraw_table_pk_withdraw_id_seq", sequenceName = "withdraw_table_pk_withdraw_id_seq")
	int pk_withdraw_id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_account_id")
	AccountEntity account;

	@ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "fk_request_type_id")
	RequestTypeEntity requestType  ;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_request_status_id")
	RequestStatusEntity requestStatus;

	@Column(name = "reference")
	String reference;

	@Column(name = "date_withdraw")
	Date date_withdraw;

	@Column(name = "amount")
	long amount;

}
	
	
