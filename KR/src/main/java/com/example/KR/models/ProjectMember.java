package com.example.KR.models;
import com.example.KR.models.enums.ProjectRole;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "project_members")
@IdClass(ProjectMemberId.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectMember {
    @Id
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    private ProjectRole role;

    private LocalDateTime joinedAt = LocalDateTime.now();
}


