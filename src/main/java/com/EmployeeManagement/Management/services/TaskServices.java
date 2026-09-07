package com.EmployeeManagement.Management.services;

import com.EmployeeManagement.Management.enums.TaskStatus;
import com.EmployeeManagement.Management.models.Task;
import com.EmployeeManagement.Management.repo.TaskRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServices {

    private final TaskRepo repo;

    public TaskServices(TaskRepo repo) {
        this.repo = repo;
    }

    public List<Task> getAllTasks() {
        return repo.findAll();
    }

    public Task getTaskById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public Task postTask(Task task) {
        if (task.getStatus() == null) {
            task.setStatus(TaskStatus.PENDING);
        }
        return repo.save(task);
    }

    public Task updateTaskStatus(Long id, TaskStatus status) {

        Optional<Task> existing = repo.findById(id);

        if (existing.isEmpty()) {
            return null;
        }

        Task task = existing.get();
        task.setStatus(status);

        return repo.save(task);
    }

    public boolean deleteTask(long id) {

        if (!repo.existsById(id)) {
            return false;
        }

        repo.deleteById(id);

        return true;
    }
}