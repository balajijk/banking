
package com.eltropy.bank.ebank.controller;

import com.eltropy.bank.ebank.exception.EntityNotFoundException;
import com.eltropy.bank.ebank.model.Employee;
import com.eltropy.bank.ebank.repository.EmployeeRepository;
import com.eltropy.bank.ebank.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public Page<Employee> getEmployees(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }

    @GetMapping("{employeeId}")
    public Employee getEmployee(@PathVariable Integer employeeId) {
        return employeeRepository.findById(employeeId).
                orElseThrow(() -> new EntityNotFoundException("Employee", "employeeId", employeeId));
    }


    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Employee addEmployee(@Valid @RequestBody Employee employee) {
        return employeeService.addEmployee(employee);
    }

    @PutMapping({"employeeId"})
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Integer employeeId, @Valid @RequestBody Employee newEmployee) {

        return employeeRepository.findById(employeeId).map(employee -> {
            employee.setFirstName(newEmployee.getFirstName());
            employee.setLastName(newEmployee.getLastName());
            employee.setDateofBirth(newEmployee.getDateofBirth());
            employee.setPhoneNumber(newEmployee.getPhoneNumber());
            employeeRepository.save(employee);
            return ResponseEntity.ok(employee);
        }).orElseThrow(() -> new EntityNotFoundException("Employee", "employeeId", employeeId));

    }

    @DeleteMapping("{employeeId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteEmployee(@PathVariable Integer employeeId) {

        return employeeRepository.findById(employeeId).map(customer -> {
                    employeeRepository.delete(customer);
                    return ResponseEntity.ok().build();
                }
        ).orElseThrow(() -> new EntityNotFoundException("Employee", "employeeId", employeeId));
    }
}