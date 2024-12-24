package com.employeemanagementsystem.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.employeemanagementsystem.entity.EmployeeData;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeData, Long> {
	boolean existsByEmpId(String empId);

	EmployeeData findByEmpId(String empId); 

	Page<EmployeeData> findByFirstNameContaining(String searchQuery, Pageable pageable);


	@Query("SELECT e FROM EmployeeData e WHERE " +
	        "(:status IS NULL OR e.status = :status) AND " +
	        "(:firstName IS NULL OR LOWER(e.firstName) LIKE LOWER(CONCAT('%', :firstName, '%'))) AND " +
	        "(:lastName IS NULL OR LOWER(e.lastName) LIKE LOWER(CONCAT('%', :lastName, '%'))) AND " +
	        "(:empId IS NULL OR LOWER(e.empId) LIKE LOWER(CONCAT('%', :empId, '%'))) AND " +
	        "(:primaryContact IS NULL OR LOWER(e.primaryContact) LIKE LOWER(CONCAT('%', :primaryContact, '%'))) AND " +
	        "(:secondaryContact IS NULL OR LOWER(e.secondaryContact) LIKE LOWER(CONCAT('%', :secondaryContact, '%'))) AND " +
	        "(:emailId IS NULL OR LOWER(e.emailId) LIKE LOWER(CONCAT('%', :emailId, '%'))) AND " +
	        "(:officialEmailId IS NULL OR LOWER(e.officialEmailId) LIKE LOWER(CONCAT('%', :officialEmailId, '%'))) AND " +
	        "(:designation IS NULL OR LOWER(e.designation) LIKE LOWER(CONCAT('%', :designation, '%'))) AND " +
	        "(:previousExperience IS NULL OR LOWER(e.previousExperience) LIKE LOWER(CONCAT('%', :previousExperience, '%'))) AND " +
	        "(:createDate IS NULL OR LOWER(CAST(e.createDate AS string)) LIKE LOWER(CONCAT('%', :createDate, '%'))) AND " +
	        "(:modifyDate IS NULL OR LOWER(CAST(e.modifyDate AS string)) LIKE LOWER(CONCAT('%', :modifyDate, '%'))) AND " +
	        "(:dateOfJoining IS NULL OR LOWER(CAST(e.dateOfJoining AS string)) LIKE LOWER(CONCAT('%', :dateOfJoining, '%'))) AND " +
	        "(:gapInExperience IS NULL OR LOWER(e.gapInExperience) LIKE LOWER(CONCAT('%', :gapInExperience, '%'))) AND " +
	        "(:createUser IS NULL OR LOWER(e.createUser) LIKE LOWER(CONCAT('%', :createUser, '%'))) AND " +
	        "(:search IS NULL OR " +
	        "LOWER(CAST(e.employeeSeqId AS string)) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
	        "LOWER(e.firstName) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
	        "LOWER(e.lastName) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
	        "LOWER(e.empId) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
	        "LOWER(e.primaryContact) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
	        "LOWER(e.secondaryContact) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
	        "LOWER(e.emailId) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
	        "LOWER(e.officialEmailId) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
	        "LOWER(e.designation) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
	        "LOWER(e.previousExperience) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
	        "LOWER(CAST(e.dateOfJoining AS string)) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
	        "LOWER(e.gapInExperience) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
	        "LOWER(e.createUser) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
	        "LOWER(CAST(e.createDate AS string)) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
	        "LOWER(CAST(e.status AS string)) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
	        "LOWER(CAST(e.modifyDate AS string)) LIKE LOWER(CONCAT('%', :search, '%')))")
	Page<EmployeeData> findEmployees(
	        @Param("status") Integer status,
	        @Param("empId") String empId,
	        @Param("firstName") String firstName,
	        @Param("lastName") String lastName,
	        @Param("primaryContact") String primaryContact,
	        @Param("secondaryContact") String secondaryContact,
	        @Param("emailId") String emailId,
	        @Param("officialEmailId") String officialEmailId,
	        @Param("designation") String designation,
	        @Param("previousExperience") String previousExperience,
	        @Param("createDate") String createDate,
	        @Param("modifyDate") String modifyDate,
	        @Param("dateOfJoining") String dateOfJoining,
	        @Param("gapInExperience") String gapInExperience,
	        @Param("createUser") String createUser,
	        @Param("search") String search,
	        Pageable pageable);

}


