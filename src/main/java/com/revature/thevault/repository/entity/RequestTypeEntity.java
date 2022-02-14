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
@Table(name = "request_type_table")
public class RequestTypeEntity {

    @Id
    @Column(name = "pk_request_type_id")
    @GeneratedValue(generator = "request_type_table_pk_request_type_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(allocationSize = 1, name = "request_type_table_pk_request_type_id_seq", sequenceName = "request_type_table_pk_request_type_id_seq")
    int pk_request_type_id;
    @Column(name = "name")
    String name;
}
