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
		@Column(name = "pk_depositt_type_id")
		@GeneratedValue(generator = "deposit_type_table_pk_deposit_type_id_seq", strategy = GenerationType.SEQUENCE)
		@SequenceGenerator(allocationSize = 1, name = "deposit_type_table_pk_deposit_type_id_seq", sequenceName = "deposit_type_table_pk_deposit_type_id_seq")
		int pk_deposit_type_id;
		@Column(name = "name")
		String name;
		public DepositTypeEntity() {
			super();
			// TODO Auto-generated constructor stub
		}
		public DepositTypeEntity(int pk_deposit_type_id, String name) {
			super();
			this.pk_deposit_type_id = pk_deposit_type_id;
			this.name = name;
		}
		public int getPk_deposit_type_id() {
			return pk_deposit_type_id;
		}
		public void setPk_deposit_type_id(int pk_deposit_type_id) {
			this.pk_deposit_type_id = pk_deposit_type_id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
	

}

