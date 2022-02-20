
package com.revature.thevault.repository.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

@Entity
@Table(name = "withdraw_table")
public class WithdrawEntity {
    @Id
    @Column(name = "pk_withdraw_id")
    @GeneratedValue(generator = "withdraw_table_pk_withdraw_id_seq", strategy = GenerationType.IDENTITY)
    @SequenceGenerator(allocationSize = 1, name = "withdraw_table_pk_withdraw_id_seq", sequenceName = "withdraw_table_pk_withdraw_id_seq")
    int pk_withdraw_id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_account_id")
    AccountEntity accountentity;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_request_type_id")
    RequestTypeEntity requesttypeentity;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_request_status_id")
    RequestStatusEntity requeststatusentity;
    @Column(name = "reference")
    String reference;
    @Column(name = "date_withdraw")
    Date date_withdraw;
    @Column(name = "amount")
    float amount;

}
