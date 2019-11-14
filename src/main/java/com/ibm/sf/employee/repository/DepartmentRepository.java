package com.ibm.sf.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ibm.sf.employee.model.Department;



public interface DepartmentRepository extends JpaRepository<Department, Integer>{

}
