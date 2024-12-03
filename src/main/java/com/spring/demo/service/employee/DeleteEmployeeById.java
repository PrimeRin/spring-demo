package com.spring.demo.service.employee;

import com.spring.demo.exception.ResourceNotFoundException;
import com.spring.demo.model.Employee;
import com.spring.demo.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteEmployeeById {

    private EmployeeRepository employeeRepository;

    public void deleteEmployee(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee Not found with given id: " + employeeId)
                );

        employeeRepository.deleteById(employeeId);
    }
}
