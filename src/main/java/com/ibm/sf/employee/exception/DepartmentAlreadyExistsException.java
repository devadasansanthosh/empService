package com.ibm.sf.employee.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.CONFLICT , reason="Department already exists.")
public class DepartmentAlreadyExistsException extends Exception{

}
