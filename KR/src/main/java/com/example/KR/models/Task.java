package com.example.KR.models;

import com.example.KR.models.enums.TaskStatus;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Entity
@Table(name = "tasks")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskId;

    @Column(nullable = false)
    private String title;

    private String description;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    private User createdBy;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('TODO', 'IN_PROGRESS', 'CODE_REVIEW', 'TESTING', 'DONE')")
    private TaskStatus status = TaskStatus.TODO;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime deadline;

    @Transient
    private String formattedDeadline;
}

