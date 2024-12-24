package com.employeemanagementsystem.repository;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.employeemanagementsystem.entity.Audit;
import com.employeemanagementsystem.entity.EmployeeData;

	@Repository
	public interface AuditRepository extends JpaRepository<Audit, Long> {
		@Query("SELECT a FROM Audit a WHERE " +
		           "LOWER(CAST(a.auditId AS string)) LIKE LOWER(CONCAT('%', :searchQuery, '%')) OR " +
		           "LOWER(CAST(a.employee AS string)) LIKE LOWER(CONCAT('%', :searchQuery, '%')) OR " +
		           "LOWER(CAST(a.details AS string)) LIKE LOWER(CONCAT('%', :searchQuery, '%')) OR " +
		           "LOWER(CAST(a.createDate AS string)) LIKE LOWER(CONCAT('%', :searchQuery, '%')) OR " +
		           "LOWER(CAST(a.operation AS string)) LIKE LOWER(CONCAT('%', :searchQuery, '%')) OR " +
		           "LOWER(CAST(a.status AS string)) LIKE LOWER(CONCAT('%', :searchQuery, '%'))")
		    Page<Audit> searchAllFields(@Param("searchQuery") String searchQuery, Pageable pageable);

		    Page<Audit> findByAuditId(Long auditId, Pageable pageable);
		    Page<Audit> findByEmployee(EmployeeData employee, Pageable pageable);
		    Page<Audit> findByDetailsContainingIgnoreCase(String details, Pageable pageable);
		    Page<Audit> findByCreateDate(LocalDate createDate, Pageable pageable);
		    Page<Audit> findByStatus(int status, Pageable pageable);
		    Page<Audit> findByOperation(int operation, Pageable pageable);

	}

	    