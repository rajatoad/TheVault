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
@Table(name = "deposit_table")
public class DepositEntity {
    @Id
    @Column(name = "pk_account_id")
    @GeneratedValue(generator = "auto_increment", strategy = GenerationType.IDENTITY)
    int pkAccountID;
    @Column
    int fk_user_id;
    @Column
}
