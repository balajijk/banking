package com.eltropy.bank.ebank.service;


import com.eltropy.bank.ebank.model.Employee;
import com.eltropy.bank.ebank.model.RoleEnum;
import com.eltropy.bank.ebank.model.UserDto;
import com.eltropy.bank.ebank.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private UserService userService;

    public Employee addEmployee(Employee employee) {
        Employee employee1 = employeeRepository.save(employee);
        UserDto userDto = new UserDto();
        userDto.setUsername(employee1.getFirstName()+employee1.getEmployeeId());
        userDto.setPassword("password1");
        userDto.setRoleEnum(RoleEnum.BANKEMPLOYEE);

        userService.save(userDto);
        return employee1;
    }
}
