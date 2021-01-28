package com.eltropy.bank.ebank.model;

public enum AccountType {

    CHECKING, SAVINGS, MMA, CD, MORTGAGE, AUTO;


    @Override
    public String toString() {
        return this.name();
    }
}
