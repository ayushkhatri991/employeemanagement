package com.EmployeeManagement.Management.repo;

import com.EmployeeManagement.Management.models.Payroll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PayrollRepo extends JpaRepository<Payroll, Long> { // Fixed this line


    boolean existsByEmployeeIdAndMonthAndYear( Long employeeId, int month, int year );
    List<Payroll> findByEmployeeId(Long employeeId);
}