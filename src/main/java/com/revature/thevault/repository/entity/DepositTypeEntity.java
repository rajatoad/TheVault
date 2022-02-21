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
@Table(name = "deposit_type_table")
public class DepositTypeEntity {
		@Id
		@Column(name = "pk_deposit_type_id")
		@GeneratedValue(generator = "deposit_type_table_pk_deposit_type_id_seq", strategy = GenerationType.SEQUENCE)
		@SequenceGenerator(allocationSize = 1, name = "deposit_type_table_pk_deposit_type_id_seq", sequenceName = "deposit_type_table_pk_deposit_type_id_seq")
		int pk_deposit_type_id;
		@Column(name = "name")
		String name;
}

