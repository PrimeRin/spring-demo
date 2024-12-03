package com.spring.demo.service;

import com.spring.demo.dto.EmployeeDto;
import com.spring.demo.service.employee.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeService {
    private CreateEmployee createEmployee;
    private DeleteEmployeeById deleteEmployeeById;
    private GetEmployeeById getEmployeeById;
    private GetAllEmployee getAllEmployee;
    private UpdateEmployee updateEmployee;

    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        return  createEmployee.createEmployee(employeeDto);
    }

    public List<EmployeeDto> getAllEmployee() {
       return getAllEmployee.getAllEmployee();
    }

    public void deleteEmployee(Long employeeId) {
        deleteEmployeeById.deleteEmployee(employeeId);
    }

    public EmployeeDto getEmployeeById(Long employeeId) {
        return getEmployeeById.getEmployeeById(employeeId);
    }

    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updateEmployeeDto) {
        return updateEmployee.updateEmployee(employeeId, updateEmployeeDto);
    }

}
