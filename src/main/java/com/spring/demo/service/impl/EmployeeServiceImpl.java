package com.spring.demo.service.impl;

import com.spring.demo.dto.EmployeeDto;
import com.spring.demo.exception.ResourceNotFoundException;
import com.spring.demo.mapper.EmployeeMapper;
import com.spring.demo.model.Employee;
import com.spring.demo.repository.EmployeeRepository;
import com.spring.demo.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee does not exist with given id : " + employeeId));

        return EmployeeMapper.mapToEmployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployee() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map((employee) -> EmployeeMapper.mapToEmployeeDto(employee))
                .collect(Collectors.toList());
    }
}
