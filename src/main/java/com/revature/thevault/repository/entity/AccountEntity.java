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
@Table(name = "accounttable")
public class AccountEntity {

	@Id
	@Column(name = "pkaccountid")
	@GeneratedValue(generator = "accounttable_pkaccountid_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(allocationSize = 1, name = "accounttable_pkaccountid_seq", sequenceName = "accounttable_pkaccountid_seq")
	int pkaccountid;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "pkuserid")
	LoginCredentialEntity loginCredentials;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "accounttypetable")
	AccountTypeEntity accountTypeEntity;

	@Column(name = "availablebalance")
	int availablebalance;
	@Column(name = "pendingbalance")
	int pendingbalance;
}
