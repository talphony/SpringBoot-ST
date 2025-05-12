package com.example.KR.controller;
import com.example.KR.models.Project;
import com.example.KR.models.ProjectMember;
import com.example.KR.models.User;
import com.example.KR.models.enums.ProjectRole;
import com.example.KR.repositories.ProjectMemberRepository;
import com.example.KR.service.ProjectMemberService;
import com.example.KR.service.ProjectService;
import com.example.KR.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.security.Principal;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;
    private final UserService userService;
    private final ProjectMemberService projectMemberService;
    private final ProjectMemberRepository projectMemberRepository;
    @GetMapping("/addProject")
    public String addProject() {
        return "addProject";
    }

    @GetMapping("/project")
    public String projects(Model model, Principal principal) {
        Optional<User> user = userService.findByUsername(principal.getName());
        model.addAttribute("myProjects", projectService.getProjectsWhereMember(user.orElse(null)));
        model.addAttribute("memberProjects", projectService.getProjectsWhereMember(user.orElse(null)));
        model.addAttribute("ProjectRoleValues", ProjectRole.values());
        return "project";

    }

    @GetMapping("/projects/{projectId}/members")
    public String showMembersPage(@PathVariable Long projectId, Model model,
                                  Principal principal) {
        Project project = projectService.getProjectById(projectId);
        User currentUser = userService.findByUsername(principal.getName())
                .orElseThrow(() -> new UsernameNotFoundException("Не найден"));

        boolean isLead = projectMemberService.isUserLeadInProject(project, currentUser);
        model.addAttribute("isLead", isLead);
        model.addAttribute("project", project);
        model.addAttribute("members", projectMemberService.getProjectMembers(project));
        model.addAttribute("membersGetRole", projectMemberService.getProjectMembersWithRoles(project));
        model.addAttribute("roles", ProjectRole.values());
        model.addAttribute("currentUser", currentUser);
//        model.addAttribute("isProjectOwner",
//                project.getCreatedBy().getId().equals(currentUser.getId()));
        return "members";
    }

    @PostMapping("/projects/{projectId}/members/{userId}/remove")
    public String removeMember(
            @PathVariable Long projectId,
            @PathVariable Long userId,
            Principal principal,
            RedirectAttributes redirectAttributes) {

        User currentUser = userService.findByUsername(principal.getName())
                .orElseThrow(() -> new UsernameNotFoundException("Не найден№1"));

        Project project = projectService.getProjectById(projectId);
        User userToRemove = userService.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("Не найден№2"));

        boolean isCreator = project.getCreatedBy().equals(currentUser);

        if (!isCreator) {
            boolean isLead = projectMemberRepository.findByProjectAndUser(project, currentUser)
                    .map(member -> member.getRole() == ProjectRole.LEAD)
                    .orElse(false);

            if (!isLead) {
                throw new AccessDeniedException("Только создатель или LEAD могут удалять участников");
            }

            ProjectMember memberToRemove = projectMemberRepository.findByProjectAndUser(project, userToRemove)
                    .orElseThrow(() -> new EntityNotFoundException("Не найден№3"));

            if (memberToRemove.getRole() != ProjectRole.MEMBER) {
                throw new IllegalStateException("LEAD может удалять только MEMBER");
            }
        }

        projectMemberService.removeMemberFromProject(project, userToRemove);
        redirectAttributes.addFlashAttribute("message", "Участник удалён");
        return "redirect:/projects/" + projectId + "/members";
    }

    @PostMapping("/project/add")
    public String addProject(@ModelAttribute Project project, Principal principal) {
        Optional<User> user = userService.findByUsername(principal.getName());
        projectService.createProject(project, user.orElse(null));
        return "redirect:/project";
    }

}
