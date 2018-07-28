package com.springboot.employeeapiboot;

import com.springboot.employeeapiboot.Controllers.EmployeeController;
import com.springboot.employeeapiboot.Cores.Employee;
import com.springboot.employeeapiboot.Services.EmployeeService;
import org.hibernate.validator.internal.util.stereotypes.Immutable;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    EmployeeService employeeService;

    @Test
    public void getHello_returnHelloString() throws Exception{
        mockMvc.perform(get("/hello")).andExpect(status().isOk());
                //.andExpect(jsonPath("").value("hello"))
                //.andExpect();
    }
    @Test
    public void getEmployeeById_ReturnsEmployeesDetail() throws Exception{
        given(employeeService.getEmployeebyId(anyInt())).willReturn(new Employee(1,"小明",20,"男",6000));

        mockMvc.perform(get("/employees/1")).andExpect(status().isOk())
                .andExpect(jsonPath("id").value(1))
                .andExpect(jsonPath("name").value("小明"))
                .andExpect(jsonPath("age").value(20))
                .andExpect(jsonPath("gender").value("男"))
                .andExpect(jsonPath("salary").value(6000));
    }
    @Test
    public void getAllEmployees_ReturnEmloyeesList() throws Exception{
        //test data
        Employee employee1=new Employee(1,"小明",20,"男",6000);
        Employee employee2=new Employee(2,"小红",20,"女",8000);
        ArrayList<Employee> employees=new ArrayList<>();
        employees.add(employee1);
        employees.add(employee2);

        given(employeeService.getEmployees()).willReturn(employees);

        mockMvc.perform(get("/employees")).andExpect(status().isOk());

    }

}
