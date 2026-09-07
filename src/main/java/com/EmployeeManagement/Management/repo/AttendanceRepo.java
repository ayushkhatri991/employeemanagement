package com.EmployeeManagement.Management.repo;

import com.EmployeeManagement.Management.models.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface AttendanceRepo extends JpaRepository<Attendance ,Long> {
    @Query("""
            SELECT COUNT(a)
            FROM Attendance a
            WHERE a.employee.id = :employeeId
            AND a.date >= :startDate
            AND a.date < :endDate
            """)
    long countTotalDays(
            @Param("employeeId") long employeeId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );

    @Query("""
            SELECT COUNT(a)
            FROM Attendance a
            WHERE a.employee.id = :employeeId
            AND a.status = com.EmployeeManagement.Management.enums.AttendanceEnum.PRESENT
            AND a.date >= :startDate
            AND a.date < :endDate
            """)
    long countPresentDays(
            @Param("employeeId") long employeeId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );

}
