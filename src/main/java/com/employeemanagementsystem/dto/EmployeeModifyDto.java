package com.employeemanagementsystem.dto;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.employeemanagementsystem.constant.ConstantValuesEmployeeModifyDto;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EmployeeModifyDto {

    @Size(min = ConstantValuesEmployeeModifyDto.minsize, max = ConstantValuesEmployeeModifyDto.maxsize, message = ConstantValuesEmployeeModifyDto.sizemsgFirstName)
    @Pattern(regexp = ConstantValuesEmployeeModifyDto.regexpFirstName, message = ConstantValuesEmployeeModifyDto.regexpmsgFirstName)
    private String firstName;

    @Size(min = ConstantValuesEmployeeModifyDto.minsize, max = ConstantValuesEmployeeModifyDto.maxsize, message = ConstantValuesEmployeeModifyDto.sizemsgLastName)
    @Pattern(regexp = ConstantValuesEmployeeModifyDto.regexpLastName, message = ConstantValuesEmployeeModifyDto.regexpmsgLastName)
    private String lastName;

    @Pattern(regexp = ConstantValuesEmployeeModifyDto.regexpPrimaryContact, message = ConstantValuesEmployeeModifyDto.regexpmsgPrimaryContact)
    @Size(min = ConstantValuesEmployeeModifyDto.ContactNumberminsize, max = ConstantValuesEmployeeModifyDto.ContactNumbermaxsize, message = ConstantValuesEmployeeModifyDto.sizemsgPrimaryContact)
    private String primaryContact;

    @Pattern(regexp = ConstantValuesEmployeeModifyDto.regexpSecondaryContact, message = ConstantValuesEmployeeModifyDto.regexpmsgSecondaryContact)
    @Size(min = ConstantValuesEmployeeModifyDto.ContactNumberminsize, max = ConstantValuesEmployeeModifyDto.ContactNumbermaxsize, message = ConstantValuesEmployeeModifyDto.sizemsgSecondaryContact)
    private String secondaryContact;

    @Email(message = ConstantValuesEmployeeModifyDto.messageEmail)
    private String emailId;

    @Email(message = ConstantValuesEmployeeModifyDto.messageOfficialEmail)
    private String officialEmailId;

    @JsonFormat(pattern = ConstantValuesEmployeeModifyDto.dateOfJoiningFormatMessage)
    @DateTimeFormat(pattern = ConstantValuesEmployeeModifyDto.dateOfJoiningFormatMessage)
    private LocalDate dateOfJoining;

    @Size(min = ConstantValuesEmployeeModifyDto.minsize, max = ConstantValuesEmployeeModifyDto.maxsize, message = ConstantValuesEmployeeModifyDto.designationSizeMessage)
    private String designation;

    @Size(min = ConstantValuesEmployeeModifyDto.previousExperienceminsize, max = ConstantValuesEmployeeModifyDto.previousExperiencemaxsize, message = ConstantValuesEmployeeModifyDto.previousExperienceMessage)
    private String previousExperience;

    @Size(min = ConstantValuesEmployeeModifyDto.minsize, max = ConstantValuesEmployeeModifyDto.maxsize, message = ConstantValuesEmployeeModifyDto.gapInExperienceMessage)
    private String gapInExperience;

    private LocalDate modifyDate;

    @NotBlank(message = ConstantValuesEmployeeModifyDto.modifyUserNotBlankMessage)
    @Size(min = ConstantValuesEmployeeModifyDto.minsize, max = ConstantValuesEmployeeModifyDto.maxsize, message = ConstantValuesEmployeeModifyDto.modifyUserSizeMessage)
    @Pattern(regexp = ConstantValuesEmployeeModifyDto.regexpModifyUser, message = ConstantValuesEmployeeModifyDto.modifyUserregexpMessage)
    private String modifyUser;

    @NotNull(message = "Status is mandatory")
    private int status;

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

	public LocalDate getDateOfJoining() {
		return dateOfJoining;
	}

	public void setDateOfJoining(LocalDate dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
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
		return "UpdateEmployee [firstName=" + firstName + ", lastName=" + lastName + ", primaryContact="
				+ primaryContact + ", secondaryContact=" + secondaryContact + ", emailId=" + emailId
				+ ", officialEmailId=" + officialEmailId + ", dateOfJoining=" + dateOfJoining + ", designation="
				+ designation + ", previousExperience=" + previousExperience + ", gapInExperience=" + gapInExperience
				+ ", modifyDate=" + modifyDate + ", modifyUser=" + modifyUser + ", status=" + status + "]";
	}
}
