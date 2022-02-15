package com.revature.thevault.repository.entity;

import com.revature.thevault.repository.entity.references.RequestStatus;
import com.revature.thevault.repository.entity.references.RequestType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

@Entity
@Table(name = "withdraw_table")
public class WithdrawEntity {
    @Id
    @Column(name = "pkWithdrawID")
    @GeneratedValue(generator = "auto_increment", strategy = GenerationType.IDENTITY)
    @SequenceGenerator(allocationSize = 1, name = "pk_withdraw_id_seq", sequenceName = "pk_withdraw_id_seq")
    int pkWithdrawID;
    @OneToMany(fetch = FetchType.EAGER)
    Set<AccountEntity> accountEntities;
    @OneToOne(cascade = CascadeType.ALL)
    RequestType requestType;
    @OneToOne(cascade = CascadeType.ALL)
    RequestStatus requestStatus;
    @Column
    String reference;
    @Column
    LocalDate dateWithdraw;
    @Column
    double amount;

}
