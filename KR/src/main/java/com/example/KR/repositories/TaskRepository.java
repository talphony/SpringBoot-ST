package com.example.KR.repositories;

import com.example.KR.models.Project;
import com.example.KR.models.Task;
import com.example.KR.models.User;
import com.example.KR.models.enums.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByProject(Project project);
    List<Task> findByCreatedBy(User user);
    List<Task> findByProjectAndStatus(Project project, TaskStatus status);
}
