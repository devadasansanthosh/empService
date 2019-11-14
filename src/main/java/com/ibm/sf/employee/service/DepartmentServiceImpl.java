package com.ibm.sf.employee.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.sf.employee.exception.DepartmentAlreadyExistsException;
import com.ibm.sf.employee.exception.DepartmentNotFoundException;
import com.ibm.sf.employee.model.Department;
import com.ibm.sf.employee.repository.DepartmentRepository;


@Service
public class DepartmentServiceImpl implements DepartmentService{
	@Autowired
	private DepartmentRepository deptRepository;
	
public Department createDepartment(Department department) throws DepartmentAlreadyExistsException{
		
		Optional<Department> optional = deptRepository.findById(department.getId());
		if (optional.isPresent()) {
			throw new DepartmentAlreadyExistsException();
		}else {
		return deptRepository.save(department);
		}
	}

@Override
public Department getDepartment(Integer id) throws DepartmentNotFoundException {
	Department dept = null;
	Optional<Department> optional = deptRepository.findById(id);
	if(optional.isPresent()) {
		dept = optional.get();
	}
	else
		throw new DepartmentNotFoundException();
		return dept;
}

/*
@Override
public Department updateDepartment(String id, Department department) throws DepartmentNotFoundException {
	Department dept = null;
	Optional<Department> optional = deptRepository.findById(id);
	if(optional.isPresent()) {
		dept = optional.get();
		dept.setId(department.getId());
		dept.setName(department.getName());
		deptRepository.save(dept);
	}
	return dept;
}

@Override
public Department getDepartment(String id) throws DepartmentNotFoundException {
	Department dept = null;
	Optional<Department> optional = deptRepository.findById(id);
	if(optional.isPresent()) {
		dept = optional.get();
	}
	else
		throw new DepartmentNotFoundException();
		return dept;
}

@Override
public boolean deleteDepartment(String id) throws DepartmentNotFoundException {
	boolean status = false;
	Optional<Department> optional = deptRepository.findById(id);
	if(optional.isPresent()) {
		deptRepository.delete(optional.get());
		status = true;
	}
	else
		throw new DepartmentNotFoundException();
	return status;
}*/

}
