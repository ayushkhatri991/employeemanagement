package com.EmployeeManagement.Management.services;

import com.EmployeeManagement.Management.models.Deduction;
import com.EmployeeManagement.Management.repo.DeductionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeductionServices {

    @Autowired
    private DeductionRepo repo;

    public List<Deduction> getAllDeductions() {
        return repo.findAll();
    }

    public Deduction getDeductionById(long id) {
        return repo.findById(id).orElse(null);
    }

    public Deduction postDeduction(Deduction deduction) {
        return repo.save(deduction);
    }

    public Deduction updateDeduction(
            long id,
            Deduction deduction) {

        if (!repo.existsById(id)) return null;

        deduction.setId(id);

        return repo.save(deduction);
    }

    public void deleteDeduction(long id) {
        repo.deleteById(id);
    }
}