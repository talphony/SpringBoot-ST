package com.example.KR.service;

import com.example.KR.models.enums.ProjectRole;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProjectMemberDto {
    private Long userId;
    private String username;
    private String email;
    private ProjectRole role;
}