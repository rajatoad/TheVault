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
@Table
public class UserProfile {
    @Id
    @Column
    @GeneratedValue
    @SequenceGenerator(allocationSize = 1, name = "placeholder", sequenceName = "placeholder")
    private int profileId;
    @OneToOne
    @JoinColumn(name = "pk_user_id", referencedColumnName = "pk_user_id")
    LoginCredentialEntity loginCredentialEntity;
    @Column
    String firstName;
    @Column
    String lastName;
    @Column
    String email;
}
