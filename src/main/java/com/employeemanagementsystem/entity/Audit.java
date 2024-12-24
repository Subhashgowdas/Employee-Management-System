package com.employeemanagementsystem.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="Audit")
public class Audit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long auditId;

    @ManyToOne
    @JoinColumn(name = "employee_seq_id")
    private EmployeeData employee;

    private LocalDate createDate;

    private int operation;

    @Lob()
    private String details; 

    private int status;
    
    public String getStatusName() {
        switch (this.status) {
            case 1: return "Success";
            case 2: return "Failed";
            default: return "Unknown";
        }
    }
    
    public String getOperationName() {
        switch (this.operation) {
            case 1: return "Create";
            case 2: return "Modify";
            case 3: return "Delete";
            case 4: return "View";
            case 5: return "GetById";
            default: return "Unknown";
        }
    }
    
   
    public Long getAuditId() {
		return auditId;
	}
	public void setAuditId(Long auditId) {
		this.auditId = auditId;
	}
	public EmployeeData getEmployee() {
		return employee;
	}
	public void setEmployee(EmployeeData employee) {
		this.employee = employee;
	}
	public LocalDate getCreateDate() {
		return createDate;
	}
	public void setCreateDate(LocalDate createDate) {
		this.createDate = createDate;
	}
	public int getOperation() {
		return operation;
	}
	public void setOperation(int operation) {
		this.operation = operation;
	}
	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	@Override
    public String toString() {
        return "Audit [auditId=" + auditId + ", employee=" + employee.getEmpId() + ", createDate=" + createDate +
                ", operation=" + operation + ", details=" + details + ", status=" + status + "]";
    }
}
