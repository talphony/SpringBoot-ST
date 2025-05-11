package com.example.KR.service;
import com.example.KR.models.Project;
import com.example.KR.models.ProjectMember;
import com.example.KR.models.User;
import com.example.KR.models.enums.ProjectRole;
import com.example.KR.repositories.ProjectMemberRepository;
import com.example.KR.repositories.ProjectRepository;
import com.example.KR.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final ProjectMemberRepository projectMemberRepository;
    private final UserRepository userRepository;
    private final ProjectMemberService projectMemberService;
    public Project createProject(Project project, User creator) {
        project.setCreatedBy(creator);
        Project savedProject = projectRepository.save(project);
        addProjectMember(savedProject, creator, ProjectRole.LEAD);

        return savedProject;
    }


    public void addProjectMember(Project project, User user, ProjectRole role) {
        ProjectMember member = new ProjectMember();
        member.setProject(project);
        member.setUser(user);
        member.setRole(role);
        projectMemberRepository.save(member);
    }

    public void addMemberToProject(Long projectId, String userIdentifier, ProjectRole role, User currentUser) {
        Project project = getProjectById(projectId);
        if (!project.getCreatedBy().equals(currentUser)) {
            throw new AccessDeniedException("Только владелец проекта может добавлять участников");
        }
        User userToAdd = userRepository.findByEmailOrUsername(userIdentifier)
                .orElseThrow(() -> new EntityNotFoundException("Пользователь не найден"));

        projectMemberService.addMemberToProject(project, userToAdd, role);
    }

    public void deleteProject(Long projectId, User currentUser) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new EntityNotFoundException("Project not found"));
        if (!project.getCreatedBy().equals(currentUser)) {
            throw new AccessDeniedException("Only project creator can delete the project");
        }

        projectRepository.delete(project);
    }

    public Project getProjectById(Long projectId) {
        return projectRepository.findById(projectId)
                .orElseThrow(() -> new EntityNotFoundException("Project not found"));
    }
    public List<Project> getProjectsWhereMember(User user) {
        return projectRepository.findByMembers_User(user);
    }
}