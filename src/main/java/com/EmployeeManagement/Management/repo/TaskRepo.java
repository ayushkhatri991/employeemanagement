package com.EmployeeManagement.Management.repo;

import com.EmployeeManagement.Management.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepo extends JpaRepository<Task,Long> {
}
