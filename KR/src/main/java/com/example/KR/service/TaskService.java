package com.example.KR.service;

import com.example.KR.models.Project;
import com.example.KR.models.Task;
import com.example.KR.models.User;
import com.example.KR.models.enums.ProjectRole;
import com.example.KR.models.enums.TaskStatus;
import com.example.KR.repositories.ProjectMemberRepository;
import com.example.KR.repositories.ProjectRepository;
import com.example.KR.repositories.TaskRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class TaskService {
    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final UserService userService;
    private final ProjectMemberRepository projectMemberRepository;

    public void createTask(Long projectId, String username, Task task) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new EntityNotFoundException("Project not found"));

        User creator = userService.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        task.setProject(project);
        task.setCreatedBy(creator);
        taskRepository.save(task);
    }

    public void deleteTask(Long taskId, User currentUser) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new EntityNotFoundException("Task not found"));

        if (!task.getCreatedBy().equals(currentUser)) {
            throw new AccessDeniedException("Only task creator can delete the task");
        }

        taskRepository.delete(task);
    }

    public List<Task> getProjectTasks(Project project) {
        return taskRepository.findByProject(project);
    }

    public void updateTaskStatus(Long taskId, TaskStatus status) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new EntityNotFoundException("Task not found"));
        task.setStatus(status);
        taskRepository.save(task);
    }
}
