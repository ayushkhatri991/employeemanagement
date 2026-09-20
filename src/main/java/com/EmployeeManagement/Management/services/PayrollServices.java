package com.EmployeeManagement.Management.services;

import com.EmployeeManagement.Management.enums.OvertimeStatus;
import com.EmployeeManagement.Management.models.*;
import com.EmployeeManagement.Management.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

@Service
public class PayrollServices {

    @Autowired
    private PayrollRepo payrollRepo;

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private PerformanceRepo performanceRepo;

    @Autowired
    private OvertimeRepo overtimeRepo;

    @Autowired
    private DeductionRepo deductionRepo;

    @Autowired
    private LeaveRepo leaveRepo;


    private static final double TAX_DEDUCTION_RATE = 0.10;


    public List<Payroll> getAllPayrolls() {
        return payrollRepo.findAll();
    }


    public Payroll getPayrollById(long id) {
        return payrollRepo.findById(id).orElse(null);
    }


    public Payroll generatePayroll(
            long employeeId,
            int month,
            int year) {

        // 1. Check duplicate payroll
        if (payrollRepo.existsByEmployeeIdAndMonthAndYear(
                employeeId,
                month,
                year)) {

            throw new IllegalStateException(
                    "Payroll for month "
                            + month
                            + " and year "
                            + year
                            + " is already generated."
            );
        }


        // 2. Find employee
        Employee employee =
                employeeRepo.findById(employeeId)
                        .orElseThrow(() ->
                                new IllegalArgumentException(
                                        "Employee with ID "
                                                + employeeId
                                                + " not found."
                                ));


        // 3. Get base salary
        Double rawSalary =
                employee.getSalary();

        double baseSalary =
                rawSalary != null
                        ? rawSalary
                        : 0.0;


        // 4. Calculate performance bonus
        double performanceBonus = 0.0;

        Performance performance =
                performanceRepo
                        .findByEmployeeAndMonthYear(
                                employeeId,
                                month,
                                year)
                        .orElse(null);


        if (performance != null &&
                performance.getFinalRating() != null) {

            double rating =
                    performance.getFinalRating();

            if (rating >= 4.5) {

                performanceBonus =
                        baseSalary * 0.15;

            } else if (rating >= 4.0) {

                performanceBonus =
                        baseSalary * 0.10;

            } else if (rating >= 3.0) {

                performanceBonus =
                        baseSalary * 0.05;
            }
        }


        // 5. Create monthly date range
        LocalDate startDate =
                LocalDate.of(year, month, 1);

        LocalDate endDate =
                startDate.plusMonths(1);


        // 6. Get overtime for this month
        List<Overtime> overtimeList =
                overtimeRepo.findByEmployeeIdAndDateBetween(
                        employeeId,
                        startDate,
                        endDate.minusDays(1)
                );


        double overtimePay = 0.0;

        for (Overtime overtime : overtimeList) {

            if (overtime.getStatus() == OvertimeStatus.APPROVED) {

                if (overtime.getAmount() != null) {

                    overtimePay +=
                            overtime.getAmount();
                }
            }
        }


        // 7. Get deductions for this month
        List<Deduction> deductionList =
                deductionRepo.findByEmployeeIdAndDateBetween(
                        employeeId,
                        startDate,
                        endDate.minusDays(1)
                );


        double deductions = 0.0;

        for (Deduction deduction : deductionList) {

            if (deduction.getAmount() != null) {

                deductions +=
                        deduction.getAmount();
            }
        }

        // 7.5 Get leave deductions (Assuming "UNPAID" leaves result in salary deduction)
        List<Leave> leaves = leaveRepo.findByEmployeeIdAndStatusAndStartDateBetween(
                employeeId,
                com.EmployeeManagement.Management.enums.LeaveStatus.APPROVED,
                startDate,
                endDate.minusDays(1)
        );

        for (Leave leave : leaves) {
            if (leave.getLeaveType() == com.EmployeeManagement.Management.enums.LeaveType.UNPAID) {
                long days = java.time.temporal.ChronoUnit.DAYS.between(leave.getStartDate(), leave.getEndDate()) + 1;
                deductions += (baseSalary / 30.0) * days;
            }
        }


        // 8. Calculate gross salary
        double grossPay =
                baseSalary
                        + performanceBonus
                        + overtimePay;


        // 9. Calculate tax
        double tax =
                grossPay * TAX_DEDUCTION_RATE;


        // Add tax to deductions
        deductions += tax;


        // 10. Calculate net salary
        double netSalary =
                grossPay - deductions;


        // 11. Create Payroll
        Payroll payroll =
                new Payroll();

        payroll.setEmployee(employee);

        payroll.setMonth(month);

        payroll.setYear(year);

        payroll.setBaseSalary(
                round(baseSalary)
        );

        payroll.setPerformanceBonus(
                round(performanceBonus)
        );

        payroll.setOvertimePay(
                round(overtimePay)
        );

        payroll.setDeductions(
                round(deductions)
        );

        payroll.setNetSalary(
                round(netSalary)
        );

        payroll.setPaymentDate(
                LocalDate.now()
        );


        return payrollRepo.save(payroll);
    }


    public void deletePayroll(long id) {
        payrollRepo.deleteById(id);
    }


    private double round(double value) {

        return BigDecimal
                .valueOf(value)
                .setScale(
                        2,
                        RoundingMode.HALF_UP
                )
                .doubleValue();
    }
}
