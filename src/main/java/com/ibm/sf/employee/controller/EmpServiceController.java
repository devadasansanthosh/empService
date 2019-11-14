package com.ibm.sf.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.sf.employee.exception.EmployeeAlreadyExistsException;
import com.ibm.sf.employee.exception.EmployeeNotFoundException;
import com.ibm.sf.employee.model.Department;
import com.ibm.sf.employee.model.Employee;
import com.ibm.sf.employee.repository.EmpServiceRepository;
import com.ibm.sf.employee.service.EmpService;



@RestController
@RequestMapping("/api")
public class EmpServiceController {
	
	private ResponseEntity<?> responseEntity;
	
	@Autowired
	private EmpService employeeService;
	
	@Autowired
	private EmpServiceRepository empRepository;
	
	public EmpServiceController() {
		super();
	}
	
	@PostMapping(value="/employee", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> createEmployee(@RequestBody Employee employee) 
			throws EmployeeAlreadyExistsException{
		try {
			System.out.println("-------->"+employee.getDepartment().getName());
			Employee emp = employeeService.createEmployee(employee);
			responseEntity = new ResponseEntity<Employee>(employee, HttpStatus.CREATED);
		} catch (EmployeeAlreadyExistsException e) {
			throw new EmployeeAlreadyExistsException();
		}
			return responseEntity;
		
	}
	
	@GetMapping(value = "/employee", produces = "application/json")
	public List<Employee> getAllEmployees() {
		return employeeService.getAllEmployees();
	}
	
	@PutMapping(value="/employee", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> updateEmployee(@RequestBody Employee employee) 
			throws EmployeeNotFoundException {
		
		try {
			//employee.setDepartment(dept);
			Employee emp = employeeService.updateEmployee(employee);
			responseEntity = new ResponseEntity<Employee>(employee, HttpStatus.OK);
		} catch (EmployeeNotFoundException e) {
			throw new EmployeeNotFoundException();
		}
			return responseEntity;
	}
	
	@GetMapping(value="/employeebyID/{id}",produces = "application/json")
	public ResponseEntity<?> getEmployee(@PathVariable("id") Integer id) throws EmployeeNotFoundException{
		System.out.println("in getemployee"); 
		try {
			Employee emp= employeeService.getEmployee(id);
			System.out.println(emp.getFirstName());
			responseEntity = new ResponseEntity<Employee>(emp,HttpStatus.OK);
		} catch (EmployeeNotFoundException e) {
			throw new EmployeeNotFoundException();
		}
		return responseEntity;
	}
	
	@DeleteMapping("/employee/{id}")
	public ResponseEntity<?> deleteEmployee(@PathVariable("id") Integer id) throws EmployeeNotFoundException{
		boolean deleted=false;
		try {
			employeeService.getEmployee(id);
		} catch (EmployeeNotFoundException e) {
			responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		try {
		deleted=employeeService.deleteEmployee(id);
		}catch(Exception e) {
			e.printStackTrace();
		}
		if(deleted == true) {
			responseEntity = new ResponseEntity<>(HttpStatus.OK);
		}else {
			responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return responseEntity;
	}
	
	@GetMapping("/employee/search/{firstName}")
    public List<Employee> findCustomersByFirstName(@PathVariable("firstName") String firstName) {
 
        if (firstName == null) {
            return employeeService.getAllEmployees();
        } else {
            return employeeService.findByFirstName(firstName);
        }
    }

}
