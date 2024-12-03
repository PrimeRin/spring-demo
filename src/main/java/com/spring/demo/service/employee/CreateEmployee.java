package com.spring.demo.service.employee;

import com.spring.demo.dto.EmployeeDto;
import com.spring.demo.mapper.EmployeeMapper;
import com.spring.demo.model.Employee;
import com.spring.demo.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class CreateEmployee {

    private EmployeeRepository employeeRepository;

    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Employee saveEmployee = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(saveEmployee);
    }
}
