package com.eltropy.bank.ebank.model;

import lombok.Data;

@Data
public class UserDto {

    private String username;
    private String password;
    private int age;
    private int salary;
    private  RoleEnum roleEnum;

}
