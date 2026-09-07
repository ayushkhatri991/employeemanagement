package com.EmployeeManagement.Management.repo;

import com.EmployeeManagement.Management.models.Leave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaveRepo extends JpaRepository<Leave, Long> {
}