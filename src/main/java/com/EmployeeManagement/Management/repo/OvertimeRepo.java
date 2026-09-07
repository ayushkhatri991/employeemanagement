package com.EmployeeManagement.Management.repo;

import com.EmployeeManagement.Management.models.Overtime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OvertimeRepo extends JpaRepository<Overtime,Long> {
    List<Overtime> findByEmployeeIdAndDateBetween(
            Long employeeId,
            LocalDate startDate,
            LocalDate endDate
    );
}
