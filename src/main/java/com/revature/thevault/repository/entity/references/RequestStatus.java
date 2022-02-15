package com.revature.thevault.repository.entity.references;

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

@Table(name = "request_status_table")
@Entity
public class RequestStatus {
    @Id
    @Column(name = "pk_request_status_id")
    @GeneratedValue(generator = "auto_increment ", strategy = GenerationType.IDENTITY)
    @SequenceGenerator(allocationSize = 1, name = "pk_request_status_id_seq ", sequenceName = " " +
            "pk_request_status_id_seq")
    int pkRequestStatusId;
    @Column
    String name;
}
