package com.spring.demo.service.employee;

import com.spring.demo.dto.EmployeeDto;
import com.spring.demo.mapper.EmployeeMapper;
import com.spring.demo.model.Employee;
import com.spring.demo.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
public class GetAllEmployee {

    private EmployeeRepository employeeRepository;

    public List<EmployeeDto> getAllEmployee() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map((employee) -> EmployeeMapper.mapToEmployeeDto(employee))
                .collect(Collectors.toList());
    }
}
