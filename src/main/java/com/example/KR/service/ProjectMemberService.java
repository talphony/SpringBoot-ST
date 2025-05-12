package com.example.KR.service;

import com.example.KR.models.Project;
import com.example.KR.models.ProjectMember;
import com.example.KR.models.User;
import com.example.KR.models.enums.ProjectRole;
import com.example.KR.repositories.ProjectMemberRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class ProjectMemberService {
    private final ProjectMemberRepository projectMemberRepository;

    public void addMemberToProject(Project project, User user, ProjectRole role) {
        if (projectMemberRepository.existsByProjectAndUser(project, user)) {
            throw new IllegalStateException("Ошибка");
        }

        ProjectMember member = new ProjectMember();
        member.setProject(project);
        member.setUser(user);
        member.setRole(role);
        projectMemberRepository.save(member);
    }


    public List<User> getProjectMembers(Project project) {
        return projectMemberRepository.findByProject(project).stream()
                .map(ProjectMember::getUser)
                .collect(Collectors.toList());
    }
    public boolean isUserLeadInProject(Project project, User user) {
        return projectMemberRepository.findByProjectAndUser(project, user)
                .map(member -> member.getRole() == ProjectRole.LEAD)
                .orElse(false);

    }
    public List<ProjectMemberDto> getProjectMembersWithRoles(Project project) {
        return projectMemberRepository.findByProject(project).stream()
                .map(member -> new ProjectMemberDto(
                        member.getUser().getId(),
                        member.getUser().getUsername(),
                        member.getUser().getEmail(),
                        member.getRole()
                ))
                .collect(Collectors.toList());
    }


    public void removeMemberFromProject(Project project, User user) {
        ProjectMember member = projectMemberRepository.findByProjectAndUser(project, user)
                .orElseThrow(() -> new EntityNotFoundException("Member not found"));

        projectMemberRepository.delete(member);
    }
}