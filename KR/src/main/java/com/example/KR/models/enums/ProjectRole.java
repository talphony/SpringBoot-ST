package com.example.KR.models.enums;

import lombok.Getter;

@Getter
public enum ProjectRole {
    LEAD("Лидер"), MEMBER("Участник");

    private final String displayName;

    ProjectRole(String displayName) {
        this.displayName = displayName;
    }

}