package com.eltropy.bank.ebank.model;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;


@Data
public class TransferRequest implements Serializable {

    private static final long serialVersionUID = 8157478795894819145L;

    private Integer fromAccountNumber;
    private Integer toAccountNumber;

    private Double transferAmount;

    private LocalDate when;

}
