package com.EmployeeManagement.Management.services;

import com.EmployeeManagement.Management.models.Attendance;
import com.EmployeeManagement.Management.repo.AttendanceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AttendanceServices {

    @Autowired
    private AttendanceRepo repo;

    public List<Attendance> getAllAttendance() {
       return repo.findAll();
    }

    public Attendance postAttendance(Attendance attendance) {
        return repo.save(attendance);
    }

    public Attendance getAttendanceById(long id) {
        return repo.findById(id).orElse(null);
    }

    public double calculateAttendanceRating(
            long employeeId,
            int month,
            int year){

        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate = startDate.plusMonths(1);

        long totalDays =
                repo.countTotalDays(employeeId, startDate, endDate);

        long presentDays =
                repo.countPresentDays(employeeId, startDate, endDate);

        if (totalDays == 0) {
            return 0;
        }

        double percentage =
                (presentDays * 100.0) / totalDays;

        if (percentage >= 95) {
            return 5;
        } else if (percentage >= 90) {
            return 4;
        } else if (percentage >= 80) {
            return 3;
        } else if (percentage >= 70) {
            return 2;
        } else {
            return 1;
        }
    }

    public Attendance updateAttendance(long id, Attendance attendance) {

        if (!repo.existsById(id)) {
            return null;
        }

        attendance.setId(id);
        return repo.save(attendance);
    }


    public void deleteAttendance(long id) {
        repo.deleteById(id);
    }
}
