package com.employeemanagementsystem.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.employeemanagementsystem.constant.ConstantValuesExceptionMessages;
import com.employeemanagementsystem.dto.EmployeeDto;
import com.employeemanagementsystem.dto.EmployeeModifyDto;
import com.employeemanagementsystem.entity.EmployeeData;
import com.employeemanagementsystem.exception.EmployeeNotFoundException;
import com.employeemanagementsystem.service.EmployeeService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/employee")
public class EmployeeDataController {
	@Autowired
	private EmployeeService employeeService;

	@PostMapping("/create")
	public ResponseEntity<EmployeeData> createEmployee(@Valid @RequestBody EmployeeDto employeeDto) {
	    EmployeeData createdEmployee = employeeService.createEmployee(employeeDto);
	    return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
	}
	
	@GetMapping("/findbyid/{empId}")
	public EmployeeData findEmployee(@PathVariable String empId,HttpServletRequest request) {
		String requestUrl = request.getRequestURL().toString() + 
				(request.getQueryString() != null ? "?" + request.getQueryString() : "");
		EmployeeData updatedEmployee = employeeService.getEmployeeById(empId,requestUrl);
		return updatedEmployee;
	}

	@PatchMapping("/update/{empId}")
	public ResponseEntity<EmployeeData> updateEmployee(
			@PathVariable String empId,
			@Valid @RequestBody EmployeeModifyDto employeeUpdates) {
		EmployeeData updatedEmployee = employeeService.updateEmployee(empId, employeeUpdates);
		return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
	}

	@DeleteMapping("delete/{empId}")
	public ResponseEntity<?> deleteEmployee(@PathVariable String empId,HttpServletRequest request) {
		String fullUrl = request.getRequestURL().toString() + 
				(request.getQueryString() != null ? "?" + request.getQueryString() : "");

		employeeService.deleteEmployee(empId,fullUrl);
		return new ResponseEntity<>(Map.of(ConstantValuesExceptionMessages.ExceptionMessage,ConstantValuesExceptionMessages.deleteSucessMessage), HttpStatus.OK);
	}

	@GetMapping("/view")
	public ResponseEntity<Map<String, Object>> viewEmployees(
			@RequestParam(defaultValue = "1") int page,
			@RequestParam(required = false) String search,
			@RequestParam(required = false) String emplID,
			@RequestParam(required = false) String statusName,
			@RequestParam(required = false) String firstName,
			@RequestParam(required = false) String lastName,
			@RequestParam(required = false) String primaryContact,
			@RequestParam(required = false) String secondaryContact,
			@RequestParam(required = false) String emailId,
			@RequestParam(required = false) String officialEmailId,
			@RequestParam(required = false) String designation,
			@RequestParam(required = false) String previousExperience,
			@RequestParam(required = false) String createDate,
			@RequestParam(required = false) String modifyDate,
			@RequestParam(required = false) String dateOfJoining,
			@RequestParam(required = false) String gapInExperience,
			@RequestParam(required = false) String createUser,
			@RequestParam(defaultValue = "10") Integer size,
			HttpServletRequest request) {

		if (page < 1 || size < 1) {
			return ResponseEntity.badRequest().body(Map.of(ConstantValuesExceptionMessages.ExceptionMessage,ConstantValuesExceptionMessages.paginationExceptionMessage ));
		}

		Pageable pageable = PageRequest.of(page - 1, size);
		String fullUrl = request.getRequestURL().toString() +
				(request.getQueryString() != null ? "?" + request.getQueryString() : "");

		try {
			Map<String, Object> result = employeeService.findEmployees(
					statusName, firstName, lastName, emplID, primaryContact, secondaryContact,
					emailId, officialEmailId, designation, previousExperience, createDate,
					modifyDate, dateOfJoining, gapInExperience, createUser, search, pageable, fullUrl
					);
			return ResponseEntity.ok(result);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
		} catch (EmployeeNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(ConstantValuesExceptionMessages.ExceptionMessage,ConstantValuesExceptionMessages.NOT_FOUNDExceptionMessage));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(ConstantValuesExceptionMessages.ExceptionMessage,ConstantValuesExceptionMessages.INTERNAL_SERVER_ERRORExceptionMessage));
		}
	}
}