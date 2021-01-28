package com.eltropy.bank.ebank.repository;

import com.eltropy.bank.ebank.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    List<Account> findByCustomerCustomerId(Integer customerId);

}

