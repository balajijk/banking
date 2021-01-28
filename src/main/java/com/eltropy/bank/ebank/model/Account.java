package com.eltropy.bank.ebank.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;


@Entity
@Table(name = "ACCOUNT")
@Data
public class Account implements Serializable{


    private static final long serialVersionUID = 8157478795894819145L;
    @Id
    private Integer accountNumber;

    private String accountName;

    private Double balance;

    private LocalDate openingDate;
    private AccountType type;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customerId", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Customer customer;

}
