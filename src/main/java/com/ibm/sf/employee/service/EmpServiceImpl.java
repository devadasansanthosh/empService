package com.ibm.sf.employee.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.sf.employee.exception.EmployeeAlreadyExistsException;
import com.ibm.sf.employee.exception.EmployeeNotFoundException;
import com.ibm.sf.employee.model.Department;
import com.ibm.sf.employee.model.Employee;
import com.ibm.sf.employee.repository.EmpServiceRepository;

@Service
public class EmpServiceImpl implements EmpService{
	@Autowired
	private EmpServiceRepository empRepository;
	
	@Autowired
	private DepartmentService deptService;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public Employee createEmployee(Employee employee) throws EmployeeAlreadyExistsException{
		
		if(employee.getId() != null) {
		throw new EmployeeAlreadyExistsException();
		}
		return empRepository.save(employee);
		
	}
	
	public List<Employee> getAllEmployees(){
		return empRepository.findAll();
	}
	
public Employee updateEmployee(Employee employee) throws EmployeeNotFoundException{
	Employee emp = entityManager.find(Employee.class, employee.getId());
	System.out.println(emp.getDepartment().getId());
	Department dept = new Department();
		//emp = optional.get();
		emp.setFirstName(employee.getFirstName());
		emp.setLastName(employee.getLastName());
		dept.setId(emp.getDepartment().getId());
		dept.setName(employee.getDepartment().getName());
		emp.setDepartment(dept);
		empRepository.save(emp);
	
	return emp;
	}

public Employee getEmployee(Integer id) throws EmployeeNotFoundException {
	Employee emp = null;
	Optional<Employee> optional = empRepository.findById(id);
	if(optional.isPresent()) {
		emp = optional.get();
	}
	else
		throw new EmployeeNotFoundException();
	System.out.println("---->>>>>>>"+emp.getFirstName());
		return emp;
}

public boolean deleteEmployee(Integer id) throws EmployeeNotFoundException {
	boolean status = false;
	Optional<Employee> optional = empRepository.findById(id);
	if(optional.isPresent()) {
		empRepository.delete(optional.get());
		status = true;
	}
	else
		throw new EmployeeNotFoundException();
	return status;
}

public List<Employee> findByFirstName( String firstName){
	return empRepository.findByFirstName(firstName);
}

}
