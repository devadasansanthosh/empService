package com.ibm.sf.employee.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(code=HttpStatus.NOT_FOUND , reason="Employee not found.")
public class EmployeeNotFoundException extends Exception{

}
