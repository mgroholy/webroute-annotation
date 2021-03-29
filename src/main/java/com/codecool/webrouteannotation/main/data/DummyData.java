package com.codecool.webrouteannotation.main.data;

import java.util.HashMap;
import java.util.Map;

public class DummyData {

    private Map<Integer, String> employeeDatabase;

    public DummyData() {
        this.employeeDatabase = fillDatabase();
    }

    private Map<Integer, String> fillDatabase(){
        Map<Integer, String> employees = new HashMap<>();
        employees.put(1, "Robert Kirkland");
        employees.put(2, "Jeremy Meyers");
        employees.put(3, "Sarah Harding");
        employees.put(4, "Stephanie Irons");
        employees.put(5, "Colin McDonald");
        employees.put(6, "Jack Benedict");
        employees.put(7, "Michelle Crown");
        employees.put(8, "Adam Porter");
        employees.put(9, "Hillary Smith");
        employees.put(10, "John Carpenter");
        return employees;
    }

    public void addEmployee(String name){
        employeeDatabase.put(employeeDatabase.size()+1, name);
    }

    public void updateEmployee(int id, String name){
        employeeDatabase.entrySet().stream().filter(employee -> employee.getKey() == id).forEach(employee -> employee.setValue(name));
    }

    public Map<Integer, String> getEmployees(){
        return employeeDatabase;
    }

    public void deleteEmployee(int id){
        employeeDatabase.remove(id);
    }
}
