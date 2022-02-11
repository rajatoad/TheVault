package com.revature.thevault.repository.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Table(name = "logincredentialtable")
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginCredentialEntity {
    @Id
    @Column(name = "pkuserid")
    @GeneratedValue(generator = "logincredentialtable_pkuserid_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(allocationSize = 1, name = "logincredentialtable_pkuserid_seq", sequenceName = "logincredentialtable_pkuserid_seq")
    int pkuserid;
    @Column(name = "userusername")
    String username;
    @Column(name = "userpassword")
    String password;
}
