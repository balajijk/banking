package com.eltropy.bank.ebank.service;


import com.eltropy.bank.ebank.model.User;
import com.eltropy.bank.ebank.model.UserDto;

import java.util.List;

public interface UserService {

    User save(UserDto user);

    List<User> findAll();

    void delete(long id);

    User findOne(String username);

    User findById(Long id);
}
