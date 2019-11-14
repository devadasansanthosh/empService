package com.ibm.sf.employee.service;

import com.ibm.sf.employee.exception.DepartmentAlreadyExistsException;
import com.ibm.sf.employee.exception.DepartmentNotFoundException;
import com.ibm.sf.employee.model.Department;

public interface DepartmentService {
	public Department createDepartment(Department department)throws DepartmentAlreadyExistsException;
	//public Department updateDepartment(String id, Department department)throws DepartmentNotFoundException;
	public Department getDepartment(Integer id) throws DepartmentNotFoundException;
	//public boolean deleteDepartment(String id) throws DepartmentNotFoundException;

}
