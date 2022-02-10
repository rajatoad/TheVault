package com.revature.thevault.repository.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

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
	@JoinColumn(name = "pk_user_id")
	LoginCredentialEntity loginCredentials;
	
//	@ManyToMany(fetch = FetchType.EAGER)
//	@JoinTable(
//			name = "account_type_bridge_table",
//			joinColumns = @JoinColumn(name = "fk_account_id"),
//			inverseJoinColumns = @JoinColumn(name = "fk_account_type_id"))
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "account_type_table")
	AccountTypeEntity accountType;
	
	@Column(name = "available_balance")
	int available_balance;
	@Column(name = "pending_balance")
	int pending_balance;
}
