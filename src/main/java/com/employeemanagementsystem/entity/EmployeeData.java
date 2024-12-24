package com.employeemanagementsystem.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

@Entity
public class EmployeeData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeSeqId;

    @Column(unique = true, nullable = false)
    private String empId;

    private String firstName;

    private String lastName;

    private String primaryContact;

    private String secondaryContact;

    private String emailId;

    private String officialEmailId;

    private String designation;

    private LocalDate dateOfJoining;

    @Column(length = 2000)
    private String previousExperience;

    private String gapInExperience;

    private String createUser;

    private LocalDate createDate;

    private LocalDate modifyDate;

    private String modifyUser;
 
    private int status;

    @PrePersist
    protected void onCreate() {
        this.createDate = LocalDate.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.modifyDate = LocalDate.now();
    }

    public String getStatusName() {
        switch (this.status) {
            case 0: return "New";
            case 1: return "Deleted";
            case 2: return "Modified";
            case 3: return "Resigned";
            default: return "Unknown";
        }
    }
    
    public Long getEmployeeSeqId() {
		return employeeSeqId;
	}

	public void setEmployeeSeqId(Long employeeSeqId) {
		this.employeeSeqId = employeeSeqId;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPrimaryContact() {
		return primaryContact;
	}

	public void setPrimaryContact(String primaryContact) {
		this.primaryContact = primaryContact;
	}

	public String getSecondaryContact() {
		return secondaryContact;
	}

	public void setSecondaryContact(String secondaryContact) {
		this.secondaryContact = secondaryContact;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getOfficialEmailId() {
		return officialEmailId;
	}

	public void setOfficialEmailId(String officialEmailId) {
		this.officialEmailId = officialEmailId;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public LocalDate getDateOfJoining() {
		return dateOfJoining;
	}

	public void setDateOfJoining(LocalDate dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

	public String getPreviousExperience() {
		return previousExperience;
	}

	public void setPreviousExperience(String previousExperience) {
		this.previousExperience = previousExperience;
	}

	public String getGapInExperience() {
		return gapInExperience;
	}

	public void setGapInExperience(String gapInExperience) {
		this.gapInExperience = gapInExperience;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public LocalDate getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDate createDate) {
		this.createDate = createDate;
	}

	public LocalDate getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(LocalDate modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getModifyUser() {
		return modifyUser;
	}

	public void setModifyUser(String modifyUser) {
		this.modifyUser = modifyUser;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
    public String toString() {
        return "EmployeeData [employeeSeqId=" + employeeSeqId + ", empId=" + empId + ", firstName=" + firstName
                + ", lastName=" + lastName + ", primaryContact=" + primaryContact + ", secondaryContact="
                + secondaryContact + ", emailId=" + emailId + ", officialEmailId=" + officialEmailId + ", designation="
                + designation + ", dateOfJoining=" + dateOfJoining + ", previousExperience=" + previousExperience
                + ", gapInExperience=" + gapInExperience + ", createUser=" + createUser + ", createDate=" + createDate
                + ", modifyDate=" + modifyDate + ", modifyUser=" + modifyUser + ", status=" + status
                + ", statusName=" + getStatusName() + "]";
    }
}
