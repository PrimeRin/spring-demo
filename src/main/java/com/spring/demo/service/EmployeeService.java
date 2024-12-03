package com.spring.demo.service;

import com.spring.demo.dto.EmployeeDto;
import com.spring.demo.service.employee.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeService {
    private Creator employeeCreator;
    private Deletor employeeDeletor;
    private Gettor employeeGettor;
    private Updator employeeUpdator;

    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        return  employeeCreator.createEmployee(employeeDto);
    }

    public List<EmployeeDto> getAllEmployee() {
       return employeeGettor.getAllEmployee();
    }

    public void deleteEmployee(Long employeeId) {
        employeeDeletor.deleteEmployee(employeeId);
    }

    public EmployeeDto getEmployeeById(Long employeeId) {
        return employeeGettor.getEmployeeById(employeeId);
    }

    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updateEmployeeDto) {
        return employeeUpdator.updateEmployee(employeeId, updateEmployeeDto);
    }

}
