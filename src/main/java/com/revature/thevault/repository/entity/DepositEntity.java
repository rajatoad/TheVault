package com.revature.thevault.repository.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

@Entity
@Table(name = "deposit_table")
public class DepositEntity {
    @Id
    @Column(name = "pk_deposit_id")
    @GeneratedValue(generator = "deposit_table_pk_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(allocationSize = 1, name = "deposit_table_pk_id_seq", sequenceName = "deposit_table_pk_id_seq")
    int pk_deposit_id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pk_account_id")
    AccountEntity accountEntity;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "deposit_type_table")
    DepositTypeEntity depositTypeEntity;
    @Column(name = "reference")
    String reference;
    @Column(name = "date")
    LocalDate date_deposit;
    @Column(name = "amount")
    int amount;
	public DepositEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public DepositEntity(int pk_deposit_id, AccountEntity accountEntity, DepositTypeEntity depositTypeEntity,
			String reference, LocalDate date_deposit, int amount) {
		super();
		this.pk_deposit_id = pk_deposit_id;
		this.accountEntity = accountEntity;
		this.depositTypeEntity = depositTypeEntity;
		this.reference = reference;
		this.date_deposit = date_deposit;
		this.amount = amount;
	}


	public int getPk_deposit_id() {
		return pk_deposit_id;
	}

	public void setPk_deposit_id(int pk_deposit_id) {
		this.pk_deposit_id = pk_deposit_id;
	}

	public AccountEntity getAccountEntity() {
		return accountEntity;
	}

	public void setAccountEntity(AccountEntity accountEntity) {
		this.accountEntity = accountEntity;
	}

	public DepositTypeEntity getDepositTypeEntity() {
		return depositTypeEntity;
	}

	public void setDepositTypeEntity(DepositTypeEntity depositTypeEntity) {
		this.depositTypeEntity = depositTypeEntity;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public LocalDate getDate_deposit() {
		return date_deposit;
	}

	public void setDate_deposit(LocalDate date_deposit) {
		this.date_deposit = date_deposit;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "DepositEntity [pk_deposit_id=" + pk_deposit_id + ", accountEntity=" + accountEntity
				+ ", depositTypeEntity=" + depositTypeEntity + ", reference=" + reference + ", date_deposit="
				+ date_deposit + ", amount=" + amount + "]";
	}
	
    
}
