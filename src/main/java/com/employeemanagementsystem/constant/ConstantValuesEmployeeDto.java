package com.employeemanagementsystem.constant;

public class ConstantValuesEmployeeDto {

	public static final int minsize = 2;

	public static final int maxsize = 50;

	public static final String messageEmpId  = "Employee Id mandatory";

	public static final String regexpFirstName  = "^[a-zA-Z]+$";

	public static final String messageFirstName="Employee First Name is mandatory";

	public static final String sizemsgFirstName="First Name must be between 2 and 50 characters";
	
	public static final String regexpmsgFirstName="Employee First Name Contains only String";

	
	public static final String regexpLastName  = "^[a-zA-Z]+$";

	public static final String messageLastName="Employee Last Name is mandatory";

	public static final String regexpmsgLastName="Employee Last Name Contains only String";

	public static final String sizemsgLastName="Last Name must be between 2 and 50 characters";

	
	public static final int ContactNumberminsize = 2;

	public static final int ContactNumbermaxsize = 50;

	public static final String regexpPrimaryContact = "^\\d{10}$";
	
	public static final String regexpmsgPrimaryContact="Contains Only Digits with out Space";

	public static final String messagePrimaryContact="Primary Contact Number is mandatory";

	public static final String sizemsgPrimaryContact="Primary Contact Number should contain 10 digits";

	
	public static final String regexpSecondaryContact = "^\\d{10}$";

	public static final String messageSecondaryContact="Secondary  Contact Number is mandatory";

	public static final String sizemsgSecondaryContact="Secondary  must be between 2 and 50 characters";

	public static final String regexpmsgSecondaryContact="Contains Only Digits with out Space";

	
	public static final String messageEmail="Invalid Email ID format";

	public static final String emailNotBlankMessage="Email ID is mandatory";

	public static final String emailSizeMessage="Email ID must be between 5 and 50 characters";

	
	public static final String messageOfficialEmail="Invalid Official Email ID format";

	public static final String OfficialemailNotBlankMessage="Official Email ID is mandatory";

	public static final String OfficialemailSizeMessage="Official Email ID must be between 5 and 50 characters";

	
	public static final String designationMessage="Designation is mandatory";

	public static final String designationSizeMessage="Designation must be between 2 and 100 characters";

	
	public static final String dateOfJoiningeMessage="Date of Joining is mandatory";
	
	public static final String dateOfJoiningFormatMessage="yyyy-MM-dd";

	public static final int previousExperienceminsize = 100;

	public static final int previousExperiencemaxsize = 1000;

	
	public static final String previousExperienceMessage="Previous Experience must be between 50 and 1000 characters";
	
	
	public static final String gapInExperienceMessage="Gap In Experience must be between 1 and 2 characters";
	
	
	public static final String regexpCreateUser  = "^[a-zA-Z]+( [a-zA-Z]+)*$";
	
	public static final String createUserNotBlankMessage="Create User is mandatory";
	
	public static final String createUserSizeMessage="Create User must be between 2 and 50 characters";

	public static final String createUserregexpMessage="Create User Contain only String";

	


}
