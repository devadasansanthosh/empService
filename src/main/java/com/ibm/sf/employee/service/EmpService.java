package com.ibm.sf.employee.service;

import java.util.List;

import com.ibm.sf.employee.exception.EmployeeAlreadyExistsException;
import com.ibm.sf.employee.exception.EmployeeNotFoundException;
import com.ibm.sf.employee.model.Employee;

public interface EmpService {
	public Employee createEmployee(Employee employee)throws EmployeeAlreadyExistsException;
	public List<Employee> getAllEmployees();
	public Employee updateEmployee(Employee employee)throws EmployeeNotFoundException;
	public Employee getEmployee(Integer id) throws EmployeeNotFoundException;
	public boolean deleteEmployee(Integer id) throws EmployeeNotFoundException;
	public List<Employee> findByFirstName(String firstName);

}
