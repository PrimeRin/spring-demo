package com.spring.demo.controller.v1;

import com.spring.demo.dto.EmployeeDto;
import com.spring.demo.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class EmployeeControllerTest {

    private MockMvc mockMvc;

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    private EmployeeDto employeeDto;

    @BeforeEach
    void setUp() {
        // Initialize Mockito and inject mocks
        MockitoAnnotations.openMocks(this);

        // Build MockMvc with the controller
        mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();

        // Sample test data
        employeeDto = new EmployeeDto(1L, "John", "Doe", "john.doe@example.com");
    }

    @Test
    void createEmployee() throws Exception {
        when(employeeService.createEmployee(any(EmployeeDto.class))).thenReturn(employeeDto);

        String requestBody = """
                {
                    "id": 1,
                    "firstName": "John",
                    "lastName": "Doe",
                    "email": "john.doe@example.com"
                }
                """;

        mockMvc.perform(post("/api/v1/employee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Doe"))
                .andExpect(jsonPath("$.email").value("john.doe@example.com"));

        verify(employeeService, times(1)).createEmployee(any(EmployeeDto.class));
    }

    @Test
    void getEmployeeById() throws Exception {
        when(employeeService.getEmployeeById(1L)).thenReturn(employeeDto);

        mockMvc.perform(get("/api/v1/employee/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Doe"))
                .andExpect(jsonPath("$.email").value("john.doe@example.com"));

        verify(employeeService, times(1)).getEmployeeById(1L);
    }

    @Test
    void getAllEmployees() throws Exception {
        List<EmployeeDto> employeeList = Arrays.asList(
                new EmployeeDto(1L, "John", "Doe", "john.doe@example.com"),
                new EmployeeDto(2L, "Jane", "Smith", "jane.smith@example.com")
        );
        when(employeeService.getAllEmployee()).thenReturn(employeeList);

        mockMvc.perform(get("/api/v1/employee"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].firstName").value("John"))
                .andExpect(jsonPath("$[1].firstName").value("Jane"));

        verify(employeeService, times(1)).getAllEmployee();
    }

    @Test
    void updateEmployee() throws Exception {
        EmployeeDto updatedEmployee = new EmployeeDto(1L, "Johnny", "Doe", "johnny.doe@example.com");
        when(employeeService.updateEmployee(eq(1L), any(EmployeeDto.class))).thenReturn(updatedEmployee);

        String requestBody = """
                {
                    "id": 1,
                    "firstName": "Johnny",
                    "lastName": "Doe",
                    "email": "johnny.doe@example.com"
                }
                """;

        mockMvc.perform(put("/api/v1/employee/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Johnny"))
                .andExpect(jsonPath("$.lastName").value("Doe"))
                .andExpect(jsonPath("$.email").value("johnny.doe@example.com"));

        verify(employeeService, times(1)).updateEmployee(eq(1L), any(EmployeeDto.class));
    }

    @Test
    void deleteEmployee() throws Exception {
        doNothing().when(employeeService).deleteEmployee(1L);

        mockMvc.perform(delete("/api/v1/employee/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Employee deleted Successfully!."));

        verify(employeeService, times(1)).deleteEmployee(1L);
    }
}
