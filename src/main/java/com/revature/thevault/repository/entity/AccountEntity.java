package com.revature.thevault.repository.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "account_table")
public class AccountEntity {

	@Id
	@Column(name = "pk_account_id")
	@GeneratedValue(generator = "account_table_pk_account_id_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(allocationSize = 1, name = "account_table_pk_account_id_seq", sequenceName = "account_table_pk_account_id_seq")
	int pk_account_id;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_user_id")
	LoginCredentialEntity logincredentials;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_account_type_id")
	AccountTypeEntity accountTypeEntity;

	@Column(name = "available_balance")
	int available_balance;
	@Column(name = "pending_balance")
	int pending_balance;
	public AccountEntity(int pk_account_id, LoginCredentialEntity logincredentials, AccountTypeEntity accountTypeEntity,
			int available_balance, int pending_balance) {
		super();
		this.pk_account_id = pk_account_id;
		this.logincredentials = logincredentials;
		this.accountTypeEntity = accountTypeEntity;
		this.available_balance = available_balance;
		this.pending_balance = pending_balance;
	}
	public int getPk_account_id() {
		return pk_account_id;
	}
	public void setPk_account_id(int pk_account_id) {
		this.pk_account_id = pk_account_id;
	}
	public LoginCredentialEntity getLogincredentials() {
		return logincredentials;
	}
	public void setLogincredentials(LoginCredentialEntity logincredentials) {
		this.logincredentials = logincredentials;
	}
	public AccountTypeEntity getAccountTypeEntity() {
		return accountTypeEntity;
	}
	public void setAccountTypeEntity(AccountTypeEntity accountTypeEntity) {
		this.accountTypeEntity = accountTypeEntity;
	}
	public int getAvailable_balance() {
		return available_balance;
	}
	public void setAvailable_balance(int available_balance) {
		this.available_balance = available_balance;
	}
	public int getPending_balance() {
		return pending_balance;
	}
	public void setPending_balance(int pending_balance) {
		this.pending_balance = pending_balance;
	}
	
}
