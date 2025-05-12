package com.example.KR.controller;
import com.example.KR.models.Project;
import com.example.KR.models.User;
import com.example.KR.models.enums.ProjectRole;
import com.example.KR.service.ProjectMemberService;
import com.example.KR.service.ProjectService;
import com.example.KR.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class ProjectMemberController {
    private final ProjectService projectService;
    private final UserService userService;
    private final ProjectMemberService projectMemberService;
    @PostMapping("/projects/{projectId}/members/add")
    public String addMember(
            @PathVariable Long projectId,
            @RequestParam String userIdentifier,
            @RequestParam ProjectRole role,
            Principal principal,
            RedirectAttributes redirectAttributes) {

        User currentUser = userService.findByUsername(principal.getName())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Project project = projectService.getProjectById(projectId);

        if (!projectMemberService.isUserLeadInProject(project, currentUser)) {
            throw new AccessDeniedException("Only LEAD can add members");
        }

        User userToAdd = userService.findByUsername(userIdentifier)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        projectMemberService.addMemberToProject(project, userToAdd, role);

        redirectAttributes.addFlashAttribute("message", "Участник добавлен");
        return "redirect:/projects/" + projectId + "/members";
    }
}