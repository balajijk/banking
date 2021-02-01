package com.eltropy.bank.ebank.controller;

import com.eltropy.bank.ebank.exception.EntityNotFoundException;
import com.eltropy.bank.ebank.model.Customer;
import com.eltropy.bank.ebank.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping
    public Page<Customer> getCustomers(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    @GetMapping("{customerId}")
    public Customer getCustomer(@PathVariable Integer customerId) {
        return customerRepository.findById(customerId).orElseThrow(() -> new EntityNotFoundException("Customer", "customerId", customerId));
    }


    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Customer createCustomer(@Valid @RequestBody Customer customer) {
        return customerRepository.save(customer);
    }

    @PutMapping({"customerId"})
    public ResponseEntity<Customer> updateCustomer(@PathVariable Integer customerId, @Valid @RequestBody Customer newCustomer) {

        return customerRepository.findById(customerId).map(customer -> {
            customer.setFirstName(newCustomer.getFirstName());
            customer.setFirstName(newCustomer.getFirstName());
            customer.setLastName(newCustomer.getLastName());
            customer.setDateofBirth(newCustomer.getDateofBirth());
            customer.setPhoneNumber(newCustomer.getPhoneNumber());
            customerRepository.save(customer);
            return ResponseEntity.ok(customer);
        }).orElseThrow(() -> new EntityNotFoundException("Customer", "customerId", customerId));

    }

    @DeleteMapping("{customerId}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Integer customerId) {

        return customerRepository.findById(customerId).map(customer -> {
                    customerRepository.delete(customer);
                    return ResponseEntity.ok().build();
                }
        ).orElseThrow(() -> new EntityNotFoundException("Customer", "customerId", customerId));
    }
}