package com.eltropy.bank.ebank.controller;

import com.eltropy.bank.ebank.model.Account;
import com.eltropy.bank.ebank.model.TransferRequest;
import com.eltropy.bank.ebank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class AccountsController {

    @Autowired
    private AccountService accountService;


    @GetMapping(value = "/customers/{customerId}/accounts")
    public List<Account> getAllAccountsForCustomer(@PathVariable Integer customerId, Pageable pageable) {
        return accountService.getAllAccountsForCustomer(customerId);
    }

    //This is non standard I wouldn't recommend this, Graph QL or some other option can be used
    @GetMapping(value = "/customers/{customerId}/accounts/{accountId}/balance")
    public Double getAllAccountBalancesForCustomer(@PathVariable Integer customerId, @PathVariable Integer accountId) {
        return accountService.getAllAccountBalancesForCustomer(customerId, accountId);
    }

    @PostMapping(value = "/customers/{customerId}/accounts")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Account createAccount(@PathVariable Integer customerId, @Valid @RequestBody Account account) {

        return accountService.createAccount(customerId, account);
    }

    @PutMapping(value = "/customers/{customerId}/accounts/{accountId}")
    public ResponseEntity<Account> updateAccount(@PathVariable Integer customerId, @PathVariable Integer accountId, @RequestBody Account newAccount) {
        return accountService.updateAccount(customerId, accountId, newAccount);
    }

    @PatchMapping(value = "/customers/{customerId}/accounts/transfer")
    public Account transferMoney(@PathVariable Integer customerId,
                                 @RequestBody TransferRequest transferRequest) {

        return accountService.transferMoney(transferRequest);
    }

    @DeleteMapping(value = "/customers/{customerId}/accounts/{accountId}")
    public ResponseEntity<?> deleteAccount(@PathVariable Integer customerId, @PathVariable Integer accountId) {

        return accountService.deleteAccount(customerId, accountId);

    }

}
