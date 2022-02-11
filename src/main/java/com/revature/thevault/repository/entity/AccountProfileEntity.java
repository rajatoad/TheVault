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
@Table(name = "accountprofiletable")
public class AccountProfileEntity {
    @Id
    @Column(name = "pkprofileid")
    @GeneratedValue(generator = "accountprofiletable_pkprofileid_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(allocationSize = 1, name = "accountprofiletable_pkprofileid_seq", sequenceName = "accountprofiletable_pkprofileid_seq")
    int pkprofileid;
    @OneToOne
    @JoinColumn(name = "pkuserid")
    LoginCredentialEntity loginCredential;
    @Column(name = "firstname")
    String firstname;
    @Column(name = "lastname")
    String lastname;
    @Column(name = "email")
    String email;
    @Column(name = "phonenumber")
    long phonenumber;
    @Column(name = "address")
    String address;
}
