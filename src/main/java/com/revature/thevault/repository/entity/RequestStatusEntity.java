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
@Table(name = "request_status_table")
public class RequestStatusEntity {
    @Id
    @Column(name = "pk_request_status_id")
    @GeneratedValue(generator = "request_status_table_pk_request_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(allocationSize = 1, name = "request_status_table_pk_request_id_seq", sequenceName = "request_status_table_pk_request_id_seq")
    int pk_request_status_id;
    @Column(name = "name")
    String name;

}
