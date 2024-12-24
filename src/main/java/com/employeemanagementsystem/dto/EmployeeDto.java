package com.employeemanagementsystem.dto;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.employeemanagementsystem.constant.ConstantValuesEmployeeDto;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EmployeeDto {
	
    private Long employeeSeqId;

    @Column(unique = true, nullable = false)
    @Size(min = ConstantValuesEmployeeDto.minsize,max=ConstantValuesEmployeeDto.maxsize)
    @NotBlank(message = ConstantValuesEmployeeDto.messageEmpId)
    private String empId;

    @Size(min = ConstantValuesEmployeeDto.minsize,max=ConstantValuesEmployeeDto.maxsize,message = ConstantValuesEmployeeDto.sizemsgFirstName)
    @NotBlank(message = ConstantValuesEmployeeDto.messageLastName)
    @Pattern(regexp = ConstantValuesEmployeeDto.regexpFirstName,message = ConstantValuesEmployeeDto.regexpmsgFirstName)
    private String firstName;

    @Size(min = ConstantValuesEmployeeDto.minsize,max=ConstantValuesEmployeeDto.maxsize,message = ConstantValuesEmployeeDto.sizemsgLastName)
    @NotBlank(message = ConstantValuesEmployeeDto.messageLastName)
    @Pattern(regexp = ConstantValuesEmployeeDto.regexpLastName,message = ConstantValuesEmployeeDto.regexpmsgLastName)
    private String lastName;

    @Size(min = ConstantValuesEmployeeDto.ContactNumberminsize,max=ConstantValuesEmployeeDto.ContactNumbermaxsize,message = ConstantValuesEmployeeDto.sizemsgPrimaryContact)
    @NotBlank(message = ConstantValuesEmployeeDto.messagePrimaryContact)
    @Pattern(regexp = ConstantValuesEmployeeDto.regexpPrimaryContact,message = ConstantValuesEmployeeDto.regexpmsgPrimaryContact)
    private String primaryContact;

    @Size(min = ConstantValuesEmployeeDto.ContactNumberminsize,max=ConstantValuesEmployeeDto.ContactNumbermaxsize,message = ConstantValuesEmployeeDto.sizemsgSecondaryContact)
    @NotBlank(message = ConstantValuesEmployeeDto.messageSecondaryContact)
    @Pattern(regexp = ConstantValuesEmployeeDto.regexpSecondaryContact,message = ConstantValuesEmployeeDto.regexpmsgSecondaryContact)
    private String secondaryContact;

    @Size(min = ConstantValuesEmployeeDto.minsize,max=ConstantValuesEmployeeDto.maxsize,message = ConstantValuesEmployeeDto.emailSizeMessage)
    @Email(message = ConstantValuesEmployeeDto.messageEmail)
    @NotBlank(message = ConstantValuesEmployeeDto.emailNotBlankMessage)
    private String emailId;

    @Size(min = ConstantValuesEmployeeDto.minsize,max=ConstantValuesEmployeeDto.maxsize,message = ConstantValuesEmployeeDto.OfficialemailSizeMessage)
    @Email(message = ConstantValuesEmployeeDto.messageOfficialEmail)
    @NotBlank(message = ConstantValuesEmployeeDto.OfficialemailNotBlankMessage)
    private String officialEmailId;

    @Size(min = ConstantValuesEmployeeDto.minsize,max=ConstantValuesEmployeeDto.maxsize,message = ConstantValuesEmployeeDto.designationSizeMessage)
    @NotBlank(message = ConstantValuesEmployeeDto.designationMessage)
    private String designation;

    @NotBlank(message = ConstantValuesEmployeeDto.dateOfJoiningeMessage)
    @JsonFormat(pattern =ConstantValuesEmployeeDto.dateOfJoiningFormatMessage)
    @DateTimeFormat(pattern = ConstantValuesEmployeeDto.dateOfJoiningFormatMessage)
    private LocalDate dateOfJoining;

    @Size(min = ConstantValuesEmployeeDto.previousExperienceminsize,max=ConstantValuesEmployeeDto.previousExperiencemaxsize,message = ConstantValuesEmployeeDto.previousExperienceMessage)
    private String previousExperience;

    @Size(max=ConstantValuesEmployeeDto.maxsize,message = ConstantValuesEmployeeDto.gapInExperienceMessage)
    private String gapInExperience;

    @NotBlank(message = ConstantValuesEmployeeDto.createUserNotBlankMessage)
    @Size(min = ConstantValuesEmployeeDto.minsize,max=ConstantValuesEmployeeDto.maxsize,message = ConstantValuesEmployeeDto.createUserSizeMessage)
    @Pattern(regexp=ConstantValuesEmployeeDto.regexpCreateUser,message = ConstantValuesEmployeeDto.createUserregexpMessage)
    private String createUser;

    private LocalDate createDate;

    private int status;

    @PrePersist
    protected void onCreate() {
        this.createDate = LocalDate.now();
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
    
}
