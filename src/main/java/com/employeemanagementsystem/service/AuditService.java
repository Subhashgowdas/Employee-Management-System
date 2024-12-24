package com.employeemanagementsystem.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.employeemanagementsystem.entity.Audit;
import com.employeemanagementsystem.entity.EmployeeData;
import com.employeemanagementsystem.exception.AuditNotFoundException;
import com.employeemanagementsystem.repository.AuditRepository;


@Service
public class AuditService {
	
	@Autowired
	 AuditRepository auditRepository;

    public Page<Audit> searchAudits(String search, Long auditId, EmployeeData employee, String details, LocalDate createDate, Integer status, Integer operation, Pageable pageable) {
        try {
            if (search != null && !search.isEmpty()) {
                Page<Audit> result = auditRepository.searchAllFields(search, pageable);
                if (result.isEmpty()) {
                    throw new AuditNotFoundException("No audits found for the search term: " + search);
                }
                return result;
            }

            if (auditId != null) {
                Page<Audit> result = auditRepository.findByAuditId(auditId, pageable);
                if (result.isEmpty()) {
                    throw new AuditNotFoundException("No audits found with ID: " + auditId);
                }
                return result;
            }

            if (employee != null) {
                Page<Audit> result = auditRepository.findByEmployee(employee, pageable);
                if (result.isEmpty()) {
                    throw new AuditNotFoundException("No audits found for the specified employee.");
                }
                return result;
            }

            if (details != null) {
                Page<Audit> result = auditRepository.findByDetailsContainingIgnoreCase(details, pageable);
                if (result.isEmpty()) {
                    throw new AuditNotFoundException("No audits found containing the details: " + details);
                }
                return result;
            }

            if (createDate != null) {
                Page<Audit> result = auditRepository.findByCreateDate(createDate, pageable);
                if (result.isEmpty()) {
                    throw new AuditNotFoundException("No audits found for the create date: " + createDate);
                }
                return result;
            }

            if (status != null) {
                Page<Audit> result = auditRepository.findByStatus(status, pageable);
                if (result.isEmpty()) {
                    throw new AuditNotFoundException("No audits found with status: " + status);
                }
                return result;
            }

            if (operation != null) {
                Page<Audit> result = auditRepository.findByOperation(operation, pageable);
                if (result.isEmpty()) {
                    throw new AuditNotFoundException("No audits found with operation: " + operation);
                }
                return result;
            }

            return auditRepository.findAll(pageable);
        } catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred while searching audits: " + e.getMessage());
        }
    }
}



