package com.ibm.sf.employee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ibm.sf.employee.model.Employee;


public interface EmpServiceRepository extends JpaRepository<Employee, Integer>{
	List<Employee> findByFirstName(String firstName);

}
