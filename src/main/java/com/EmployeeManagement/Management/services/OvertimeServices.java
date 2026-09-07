package com.EmployeeManagement.Management.services;

import com.EmployeeManagement.Management.models.Overtime;
import com.EmployeeManagement.Management.repo.OvertimeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OvertimeServices {
    @Autowired
    private OvertimeRepo repo;

        public List<Overtime> getAllOvertime() {
            return repo.findAll();
        }

        public Overtime getOvertimeById(long id) {
            return repo.findById(id).orElse(null);
        }

        public Overtime postOvertime(Overtime overtime) {

            overtime.setAmount(
                    overtime.getHours() * overtime.getRate()
            );

            return repo.save(overtime);
        }

        public Overtime updateOvertime(long id, Overtime overtime) {

            if (!repo.existsById(id)) return null;

            overtime.setId(id);

            overtime.setAmount(
                    overtime.getHours() * overtime.getRate()
            );

            return repo.save(overtime);
        }

        public void deleteOvertime(long id) {
            repo.deleteById(id);
        }
}
