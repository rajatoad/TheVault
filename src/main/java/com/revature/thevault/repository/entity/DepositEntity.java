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
    @GeneratedValue(generator = "deposit_table_pk_deposit_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(allocationSize = 1, name = "deposit_table_pk_deposit_id_seq", sequenceName = "deposit_table_pk_deposit_id_seq")
    int pk_deposit_id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_account_id")
    AccountEntity accountentity;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_deposit_type_id")
    DepositTypeEntity deposittypeentity;
    @Column(name = "reference")
    String reference;
    @Column(name = "date")
    LocalDate date_deposit;
    @Column(name = "amount")
    int amount;
}
