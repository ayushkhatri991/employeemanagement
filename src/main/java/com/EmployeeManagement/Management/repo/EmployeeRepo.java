package com.EmployeeManagement.Management.repo;

import com.EmployeeManagement.Management.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee,Long> {
    @Query("""
        SELECT e FROM Employee e
        WHERE LOWER(e.firstName) LIKE LOWER(CONCAT('%', :keyword, '%'))
        OR LOWER(e.lastName) LIKE LOWER(CONCAT('%', :keyword, '%'))
        OR LOWER(e.department) LIKE LOWER(CONCAT('%', :keyword, '%'))
    """)
    List<Employee> searchEmployees(@Param("keyword") String keyword);

}
