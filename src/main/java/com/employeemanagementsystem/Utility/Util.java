package com.employeemanagementsystem.Utility;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.employeemanagementsystem.entity.Audit;
import com.employeemanagementsystem.entity.EmployeeData;
import com.employeemanagementsystem.repository.AuditRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class Util {
	
	@Autowired
	AuditRepository auditRepository;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	public Integer convertStatusNameToCode(String statusName) {
        if (statusName == null || statusName.isEmpty()) {
            return null;
        }
        switch (statusName.toLowerCase()) {
            case "new": return 0;
            case "deleted": return 1;
            case "modified": return 2;
            case "resigned": return 3;
            default: throw new IllegalArgumentException("Invalid status name: " + statusName);
        }
    }

    public Map<String, Object> createResponse(Page<EmployeeData> employeePage, String details) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", details);
        response.put("data", employeePage.getContent());
        response.put("pagination", Map.of(
                "currentPage", employeePage.getNumber() + 1,
                "totalPages", employeePage.getTotalPages(),
                "totalRecords", employeePage.getTotalElements()
        ));
        return response;
    }


	public void logAudit(EmployeeData employee, int operation, String details, int status) {
		Audit audit = new Audit();
		audit.setEmployee(employee);
		audit.setCreateDate(LocalDate.now());
		audit.setOperation(operation);
		audit.setDetails(details);
		audit.setStatus(status);
		auditRepository.save(audit);
	}

	public String serializeEmployeeData(EmployeeData employee) {
		try {
			return objectMapper.writeValueAsString(employee);
		} catch (JsonProcessingException e) {
			return "Error serializing employee data: " + e.getMessage();
		}
	}
}
