package com.EmployeeManagement.Management.services;

import com.EmployeeManagement.Management.enums.LeaveStatus;
import com.EmployeeManagement.Management.models.Leave;
import com.EmployeeManagement.Management.repo.LeaveRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveServices {

    @Autowired
    private LeaveRepo repo;


    public List<Leave> getAllLeaves() {
        return repo.findAll();
    }

    public Leave getLeaveById(long id) {
        return repo.findById(id).orElse(null);
    }

    public Leave postLeave(Leave leave) {
        return repo.save(leave);
    }



    public void deleteLeave(long id) {
        repo.deleteById(id);
    }

    public Leave updateLeave(long id,  LeaveStatus status) {

        Leave leave = repo.findById(id).orElse(null);

        if (leave == null) {
            return null;
        }

        leave.setStatus(status);

        return repo.save(leave);
    }

    public List<Leave> getPendingLeaves() {
        return repo.findByStatus(LeaveStatus.PENDING);
    }
}
