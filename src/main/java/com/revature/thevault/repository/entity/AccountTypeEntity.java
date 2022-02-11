package com.revature.thevault.repository.entity;

import javax.persistence.*;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "accounttypetable")
public class AccountTypeEntity {
	@Id
	@Column(name = "pkaccounttypeid")
	@GeneratedValue(generator = "accounttypetable_pkaccounttypeid_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(allocationSize = 1, name = "accounttypetable_pkaccounttypeid_seq", sequenceName = "accounttypetable_pkaccounttypeid_seq")
	int pkaccounttypeid;
	@Column(name = "name")
	String name;
}
