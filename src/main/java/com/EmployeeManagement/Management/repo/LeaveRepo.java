package com.EmployeeManagement.Management.repo;

import com.EmployeeManagement.Management.enums.LeaveStatus;
import com.EmployeeManagement.Management.models.Leave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaveRepo extends JpaRepository<Leave, Long> {
    List<Leave> findByStatus(LeaveStatus status);
}