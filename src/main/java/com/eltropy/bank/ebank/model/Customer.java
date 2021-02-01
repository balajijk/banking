package com.eltropy.bank.ebank.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "CUSTOMER")
@Data
public class Customer implements Serializable {


    private static final long serialVersionUID = -4044654709156319362L;

    @Id
    @GeneratedValue
    private Integer customerId;

    private String firstName;
    private String lastName;

    private LocalDate dateofBirth;
    private String phoneNumber;
}
