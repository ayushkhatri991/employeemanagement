package com.EmployeeManagement.Management.repo;

import com.EmployeeManagement.Management.enums.LeaveStatus;
import com.EmployeeManagement.Management.models.Leave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.time.LocalDate;

@Repository
public interface LeaveRepo extends JpaRepository<Leave, Long> {
    List<Leave> findByStatus(LeaveStatus status);
    List<Leave> findByEmployeeIdAndStatusAndStartDateBetween(long employeeId, LeaveStatus status, LocalDate startDate, LocalDate endDate);
}