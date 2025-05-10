package com.example.demo.enums;

import lombok.Getter;

@Getter
public enum ApplicationStatus {
    SENT("Отправлена"),
    ACCEPTED("Принята"),
    DELETED("Удалена");

    private final String displayName;

    ApplicationStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}