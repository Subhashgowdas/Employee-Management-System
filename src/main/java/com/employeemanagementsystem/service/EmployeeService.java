package com.employeemanagementsystem.service;
import java.time.LocalDate;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.employeemanagementsystem.Utility.Util;
import com.employeemanagementsystem.constant.ConstantValuesExceptionMessages;
import com.employeemanagementsystem.dto.EmployeeDto;
import com.employeemanagementsystem.dto.EmployeeModifyDto;
import com.employeemanagementsystem.entity.EmployeeData;
import com.employeemanagementsystem.exception.EmployeeAlreadyExistsException;
import com.employeemanagementsystem.exception.EmployeeNotFoundException;
import com.employeemanagementsystem.repository.EmployeeRepository;

import jakarta.validation.Valid;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private Util util;

	public EmployeeData createEmployee(@Valid EmployeeDto employeeDto) {
		if (employeeRepository.existsByEmpId(employeeDto.getEmpId())) {
			EmployeeData existingEmployee = employeeRepository.findByEmpId(employeeDto.getEmpId());
			util.logAudit(existingEmployee, 1, util.serializeEmployeeData(existingEmployee), 2);
			throw new EmployeeAlreadyExistsException(ConstantValuesExceptionMessages.employeeAlreadyExistsExceptionMessage);
		}
		EmployeeData employeeData = new EmployeeData();
	    employeeData.setEmpId(employeeDto.getEmpId());
	    employeeData.setFirstName(employeeDto.getFirstName());
	    employeeData.setLastName(employeeDto.getLastName());
	    employeeData.setPrimaryContact(employeeDto.getPrimaryContact());
	    employeeData.setSecondaryContact(employeeDto.getSecondaryContact());
	    employeeData.setEmailId(employeeDto.getEmailId());
	    employeeData.setOfficialEmailId(employeeDto.getOfficialEmailId());
	    employeeData.setDesignation(employeeDto.getDesignation());
	    employeeData.setDateOfJoining(employeeDto.getDateOfJoining());
	    employeeData.setPreviousExperience(employeeDto.getPreviousExperience());
	    employeeData.setGapInExperience(employeeDto.getGapInExperience());
	    employeeData.setCreateUser(employeeDto.getCreateUser());
	    employeeData.setStatus(0);
	    
	    EmployeeData createdEmployee = employeeRepository.save(employeeData);
	    util.logAudit(createdEmployee, 1, util.serializeEmployeeData(createdEmployee), 1);
		return createdEmployee; 
	}

	public EmployeeData getEmployeeById(String empId,String request) {
		if (!employeeRepository.existsByEmpId(empId)) {
			util.logAudit(null, 5, request, 2);
			throw new EmployeeNotFoundException(ConstantValuesExceptionMessages.employeeNotFoundExceptionMessage + empId);
		}

		EmployeeData employee = employeeRepository.findByEmpId(empId);
		util.logAudit(null, 5, request, 1);
		return employee;
	}

	public EmployeeData updateEmployee(String empId, @Valid EmployeeModifyDto employeeUpdates) {
		EmployeeData existingEmployee = getEmployeeById(empId,"");

		try {
			if (existingEmployee.getStatus() == 1 || existingEmployee.getStatus() == 3) {
				throw new EmployeeNotFoundException(ConstantValuesExceptionMessages.employeeNotFoundExceptionMessage);
			}

			if (employeeUpdates.getFirstName() != null) {
				existingEmployee.setFirstName(employeeUpdates.getFirstName());
			}
			if (employeeUpdates.getLastName() != null) {
				existingEmployee.setLastName(employeeUpdates.getLastName());
			}
			if (employeeUpdates.getPrimaryContact() != null) {
				existingEmployee.setPrimaryContact(employeeUpdates.getPrimaryContact());
			}
			if (employeeUpdates.getSecondaryContact() != null) {
				existingEmployee.setSecondaryContact(employeeUpdates.getSecondaryContact());
			}
			if (employeeUpdates.getEmailId() != null) {
				existingEmployee.setEmailId(employeeUpdates.getEmailId());
			}
			if (employeeUpdates.getOfficialEmailId() != null) {
				existingEmployee.setOfficialEmailId(employeeUpdates.getOfficialEmailId());
			}
			if (employeeUpdates.getDesignation() != null) {
				existingEmployee.setDesignation(employeeUpdates.getDesignation());
			}
			if (employeeUpdates.getDateOfJoining() != null) {
				existingEmployee.setDateOfJoining(employeeUpdates.getDateOfJoining());
			}
			if (employeeUpdates.getPreviousExperience() != null) {
				existingEmployee.setPreviousExperience(employeeUpdates.getPreviousExperience());
			}
			if (employeeUpdates.getGapInExperience() != null) {
				existingEmployee.setGapInExperience(employeeUpdates.getGapInExperience());
			}
			if (employeeUpdates.getModifyUser() != null) {
				existingEmployee.setModifyUser(employeeUpdates.getModifyUser());
			}
			existingEmployee.setModifyDate(LocalDate.now());

			if (employeeUpdates.getStatus() >= 1) {
				existingEmployee.setStatus(employeeUpdates.getStatus());
			} else {
				existingEmployee.setStatus(2);
			}
			EmployeeData updatedEmployee = employeeRepository.save(existingEmployee);
			util.logAudit(existingEmployee, 2, employeeUpdates.toString(), 1);
			return updatedEmployee;
		} catch (EmployeeNotFoundException ie) {
			util.logAudit(existingEmployee, 2, employeeUpdates.toString(), 2);
			throw ie;
		} catch (Exception e) {
			util.logAudit(existingEmployee, 2, employeeUpdates.toString(), 2);
			throw e;
		} 
	}
	public void deleteEmployee(String empId,String requestUrl) {
		EmployeeData existingEmployee = employeeRepository.findByEmpId(empId);

		if (existingEmployee == null) {
			throw new EmployeeNotFoundException(ConstantValuesExceptionMessages.employeeNotFoundExceptionMessage+ empId);
		}

		if (existingEmployee.getStatus() == 1 || existingEmployee.getStatus() == 3) {
			util.logAudit(null, 3,requestUrl, 2);
			throw new EmployeeNotFoundException(ConstantValuesExceptionMessages.employeeNotFoundExceptionMessage+ empId);
		}
		existingEmployee.setStatus(1);
		employeeRepository.save(existingEmployee);
		util.logAudit(existingEmployee, 3,requestUrl, 1);
	}

    public Map<String, Object> findEmployees(String statusName, String firstName, String lastName, String empId,
                                              String primaryContact, String secondaryContact, String emailId,
                                              String officialEmailId, String designation, String previousExperience,
                                              String createDate, String modifyDate, String dateOfJoining,
                                              String gapInExperience, String createUser, String search,
                                              Pageable pageable, String requestUrl) {
        Integer status = (statusName != null) ? util.convertStatusNameToCode(statusName) : null;

        Page<EmployeeData> employeePage = employeeRepository.findEmployees(
                status, empId, firstName, lastName, primaryContact, secondaryContact, emailId,
                officialEmailId, designation, previousExperience, createDate, modifyDate,
                dateOfJoining, gapInExperience, createUser, search, pageable
        );

        String details = employeePage.hasContent() ? ConstantValuesExceptionMessages.employeeRetriveSuccessMessage :
                ConstantValuesExceptionMessages.employeeSearchMessage + search;
        int logStatus = employeePage.hasContent() ? 1 : 2;

        if (employeePage.hasContent()) {
            for (EmployeeData employee : employeePage.getContent()) {
            	util.logAudit(employee, 4, requestUrl, logStatus);
            }
        } else {
        	util.logAudit(null, 4, requestUrl + " - " + details, logStatus);
            throw new EmployeeNotFoundException(ConstantValuesExceptionMessages.employeeNotFoundExceptionMessage);
        }

        return util.createResponse(employeePage, details);
    }

}