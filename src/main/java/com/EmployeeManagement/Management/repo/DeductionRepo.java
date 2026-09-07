package com.EmployeeManagement.Management.repo;

import com.EmployeeManagement.Management.models.Deduction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DeductionRepo extends JpaRepository<Deduction,Long> {
    List<Deduction> findByEmployeeIdAndDateBetween(
            Long employeeId,
            LocalDate startDate,
            LocalDate endDate
    );
}
