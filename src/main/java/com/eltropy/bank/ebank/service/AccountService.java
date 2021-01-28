package com.eltropy.bank.ebank.service;

import com.eltropy.bank.ebank.exception.EntityNotFoundException;
import com.eltropy.bank.ebank.model.Account;
import com.eltropy.bank.ebank.model.Customer;
import com.eltropy.bank.ebank.model.TransferRequest;
import com.eltropy.bank.ebank.repository.AccountRepository;
import com.eltropy.bank.ebank.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {


    @Autowired
    AccountRepository accountRepository;

    @Autowired
    CustomerRepository customerRepository;


    public Account transferMoney(TransferRequest transferRequest) {
        Account fromAccount = accountRepository.findById(
                transferRequest.getFromAccountNumber())
                .orElseThrow(() -> new EntityNotFoundException("Account", "accountId", transferRequest.getFromAccountNumber()));

        Account toAccount = accountRepository.findById(
                transferRequest.getFromAccountNumber())
                .orElseThrow(() -> new EntityNotFoundException("Account", "accountId", transferRequest.getToAccountNumber()));

        if (transferRequest.getTransferAmount() >= 0 && fromAccount.getBalance() >= transferRequest.getTransferAmount()) {
            fromAccount.setBalance(fromAccount.getBalance() - transferRequest.getTransferAmount());
            accountRepository.save(fromAccount);
            toAccount.setBalance(toAccount.getBalance() + transferRequest.getTransferAmount());
            accountRepository.save(toAccount);
        }

        return fromAccount;
    }

    public ResponseEntity<?> deleteAccount(Integer customerId, Integer accountId) {
        if (!customerRepository.existsById(customerId)) {
            throw new EntityNotFoundException("Customer", "customerId", customerId);
        }

        return accountRepository.findById(accountId).map(account -> {
            accountRepository.delete(account);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new EntityNotFoundException("Account", "accountId", accountId));
    }

    public List<Account> getAllAccountsForCustomer(Integer customerId) {
        return accountRepository.findByCustomerCustomerId(customerId);
    }


    public Double getAllAccountBalancesForCustomer(Integer customerId, Integer accountId) {
        return accountRepository.findById(accountId).map(account -> account.getBalance())
                .orElseThrow(() -> new EntityNotFoundException("Account", "accountId", accountId));
    }


    public Account createAccount(Integer customerId, Account account) {


        return customerRepository.findById(customerId).map(customer -> {
            account.setCustomer(customer);
            return accountRepository.save(account);

        }).orElseThrow(() -> new EntityNotFoundException("Customer", "customerId", customerId));

    }

    public ResponseEntity<Account> updateAccount(Integer customerId, Integer accountId, Account newAccount) {

        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new EntityNotFoundException("Customer", "customerId", customerId));

        return accountRepository.findById(accountId).map(account -> {
            newAccount.setCustomer(customer);
            accountRepository.save(newAccount);
            return ResponseEntity.ok(newAccount);
        }).orElseThrow(() -> new EntityNotFoundException("Account", "accountId", accountId));


    }

}
