package com.springboot.employeeapiboot;

import com.springboot.employeeapiboot.Cores.Employee;
import com.springboot.employeeapiboot.Services.EmployeeService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.not;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;

public class EmployeeAPITest {
    //@Autowired
    EmployeeService employeeService=new EmployeeService();

    @Test
    public void should_get_employee_when_call_getEmployeebyId(){
        Employee employee=employeeService.getEmployeebyId(1);
        assertThat(employee.getId(),is(1));
    }
    @Test
    public void should_get_employees_when_call_getEmployeebyGender(){
        List <Employee>employees=employeeService.getEmployeebyGender();
        assertThat(employees.get(0).getGender(),is("男"));
        //assertThat(employees.get(1),is("男"));
    }
    @Test
    public void should_add_one_employee_when_post_employee(){

        Employee putemployee= new Employee(1,"小明",20,"男",6000);
        employeeService.add(putemployee);
        Employee mployeegetout=employeeService.getEmployees().get(employeeService.getEmployees().size()-1);
        assertThat(mployeegetout,is(putemployee));
    }
    @Test
    public void should_filter_employees_when_call_getEmployeesbyPagesize(){
        employeeService.add(new Employee(3,"小明",20,"男",6000));
        employeeService.add(new Employee(4,"小红",20,"女",8000));

        List<Employee> employees=employeeService.getEmployeesbyPagesize(1,2);
        assertThat(employees.get(0).getId(),is(1));
        assertThat(employees.size(),is(2));
    }

    @Test
    public void should_change_employee_when_call_put(){
        employeeService.add(new Employee(3,"小明",20,"男",6000));
        employeeService.add(new Employee(4,"小红",20,"女",8000));

        List<Employee> employees=employeeService.put(3,new Employee(3,"小蓝",20,"男",5000));
        assertThat(employees.get(2).getName(),is("小蓝"));
        assertThat(employees.get(2).getSalary(),is(5000L));
    }

    @Test
    public void should_delete_employee_when_call_delete(){
        employeeService.add(new Employee(3,"小明",20,"男",6000));
        employeeService.add(new Employee(4,"小红",20,"女",8000));

        List<Employee> employees=employeeService.delete(3);
        assertNotEquals(employees.get(2).getName(),"小蓝");
        assertNotEquals(employees.get(2).getId(),3);
    }

}
