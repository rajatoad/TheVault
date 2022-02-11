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
	LoginCredentialEntity loginCredentials;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_account_type_id")
	AccountTypeEntity accountTypeEntity;

	@Column(name = "available_balance")
	int available_balance;
	@Column(name = "pending_balance")
	int pending_balance;
}
