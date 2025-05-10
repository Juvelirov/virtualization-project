package com.example.demo.enums;

import lombok.Getter;

@Getter
public enum InternshipStatus {
    DRAFT("Черновик"),
    PUBLISHED("Опубликована"),
    ARCHIVED("В архиве"),
    DELETED("Удалена");

    private final String displayName;

    InternshipStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}