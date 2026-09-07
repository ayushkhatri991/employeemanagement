package com.EmployeeManagement.Management.services;

import com.EmployeeManagement.Management.models.Performance;
import com.EmployeeManagement.Management.repo.AttendanceRepo;
import com.EmployeeManagement.Management.repo.PerformanceRepo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

@Service
public class PerformanceServices {

    @Autowired
    private PerformanceRepo repo;

    @Autowired
    private AttendanceRepo attendanceRepo;


    public List<Performance> getAllPerformance() {
        return repo.findAll();
    }


    public Performance getPerformanceById(long id) {
        return repo.findById(id).orElse(null);
    }


    public Performance postPerformance(
            Performance performance,
            int month,
            int year) {

        calculateRatings(performance, month, year);

        return repo.save(performance);
    }


    public Performance updatePerformance(
            long id,
            Performance performance,
            int month,
            int year) {

        if (!repo.existsById(id)) {
            return null;
        }

        performance.setId(id);

        calculateRatings(performance, month, year);

        return repo.save(performance);
    }


    public void deletePerformance(long id) {
        repo.deleteById(id);
    }


    private void calculateRatings(
            Performance performance,
            int month,
            int year) {


        if (performance.getEmployee() == null ||
                performance.getEmployee().getId() == null) {

            throw new IllegalArgumentException(
                    "Employee and Employee ID must not be null.");
        }


        long employeeId =
                performance.getEmployee().getId();



        LocalDate startDate =
                LocalDate.of(year, month, 1);

        LocalDate endDate =
                startDate.plusMonths(1);



        long totalDays =
                attendanceRepo.countTotalDays(
                        employeeId,
                        startDate,
                        endDate);


        long presentDays =
                attendanceRepo.countPresentDays(
                        employeeId,
                        startDate,
                        endDate);



        double attendanceRating = 0.0;

        if (totalDays > 0) {

            double percentage =
                    (presentDays * 100.0) / totalDays;

            if (percentage >= 95) {
                attendanceRating = 5.0;

            } else if (percentage >= 90) {
                attendanceRating = 4.0;

            } else if (percentage >= 80) {
                attendanceRating = 3.0;

            } else if (percentage >= 70) {
                attendanceRating = 2.0;

            } else {
                attendanceRating = 1.0;
            }
        }

        performance.setAttendance(attendanceRating);
        double rawFinalRating =
                (performance.getTaskCompletion() * 0.30)
                        + (performance.getWorkQuality() * 0.25)
                        + (attendanceRating * 0.20)
                        + (performance.getPunctuality() * 0.15)
                        + (performance.getTeamwork() * 0.10);


        // 6. Round final rating to 2 decimal places
        double roundedFinalRating =
                BigDecimal
                        .valueOf(rawFinalRating)
                        .setScale(
                                2,
                                RoundingMode.HALF_UP)
                        .doubleValue();

        performance.setFinalRating(
                roundedFinalRating);
    }
}
