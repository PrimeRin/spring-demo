package com.spring.demo.service.impl;

import com.spring.demo.dto.EmployeeDto;
import com.spring.demo.mapper.EmployeeMapper;
import com.spring.demo.model.Employee;
import com.spring.demo.repository.EmployeeRepository;
import com.spring.demo.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Employee saveEmployee = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(saveEmployee);
    }
}
