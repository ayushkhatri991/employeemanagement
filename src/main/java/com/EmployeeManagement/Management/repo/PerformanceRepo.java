package com.EmployeeManagement.Management.repo;

import com.EmployeeManagement.Management.models.Performance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface PerformanceRepo extends JpaRepository<Performance,Long> {
    @Query("SELECT p FROM Performance p WHERE p.employee.id = :employeeId AND MONTH(p.reviewDate) = :month AND YEAR(p.reviewDate) = :year")
    Optional<Performance> findByEmployeeAndMonthYear(
            @Param("employeeId") Long employeeId,
            @Param("month") int month,
            @Param("year") int year
    );
}
