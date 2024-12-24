package com.employeemanagementsystem.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.employeemanagementsystem.entity.Audit;
import com.employeemanagementsystem.entity.EmployeeData;
import com.employeemanagementsystem.exception.AuditNotFoundException;
import com.employeemanagementsystem.service.AuditService;

@RestController
@RequestMapping("/employee")
public class AuditController {
	
	@Autowired
	private AuditService auditService; 

	
	@GetMapping("/auditview")
	public ResponseEntity<Map<String, Object>> viewAudits(
	        @RequestParam(required = false) String search,
	        @RequestParam(required = false) Long auditId,
	        @RequestParam(required = false) EmployeeData employee,
	        @RequestParam(required = false) String details,
	        @RequestParam(required = false) LocalDate createDate,
	        @RequestParam(required = false) Integer status,
	        @RequestParam(required = false) Integer operation,
	        @RequestParam(defaultValue = "1") int page,
	        @RequestParam(defaultValue = "10") int size) {

	    if (page < 1 || size < 1) {
	        return ResponseEntity.badRequest().body(Map.of("message", "Page & size must be at least 1."));
	    }

	    Pageable pageable = PageRequest.of(page - 1, size);

	    try {
	        Page<Audit> auditPage = auditService.searchAudits(search, auditId, employee, details, createDate, status, operation, pageable);

	        Map<String, Object> response = new HashMap<>();
	        response.put("data", auditPage.getContent());
	        response.put("pagination", Map.of(
	                "currentPage", auditPage.getNumber() + 1,
	                "totalPages", auditPage.getTotalPages(),
	                "totalRecords", auditPage.getTotalElements()
	        )); 

	        return ResponseEntity.ok(response);
	    } catch (AuditNotFoundException e) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", e.getMessage()));
	    } catch (IllegalArgumentException e) {
	        return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message", "An unexpected error occurred."));
	    }
	}
}
