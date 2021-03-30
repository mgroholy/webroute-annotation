package com.codecool.webrouteannotation.main.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DummyData {

    private List<Employee> employeeDatabase;

    public DummyData() {
        this.employeeDatabase = fillDatabase();
    }

    private List<Employee> fillDatabase(){
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "Robert Kirkland"));
        employees.add(new Employee(2, "Jeremy Meyers"));
        employees.add(new Employee(3, "Sarah Harding"));
        employees.add(new Employee(4, "Stephanie Irons"));
        employees.add(new Employee(5, "Colin McDonald"));
        employees.add(new Employee(6, "Jack Benedict"));
        employees.add(new Employee(7, "Michelle Crown"));
        employees.add(new Employee(8, "Adam Porter"));
        employees.add(new Employee(9, "Hillary Smith"));
        employees.add(new Employee(10, "John Carpenter"));

        return employees;
    }

    public void addEmployee(String name){
        int id = employeeDatabase.size() + 1;
        Employee newEmployee = new Employee(id, name);
        employeeDatabase.add(newEmployee);
    }

    public void updateEmployee(int id, String name){
        employeeDatabase.stream().filter(employee -> employee.getId() == id).findFirst().ifPresent(employeeToUpdate -> employeeToUpdate.setName(name));
    }

    public List<Employee> getEmployees(){
        return employeeDatabase;
    }

    public void deleteEmployee(int id){
        employeeDatabase.removeIf(employee -> employee.getId() == id);
    }
}
