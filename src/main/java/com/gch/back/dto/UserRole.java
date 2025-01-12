package com.gch.back.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserRole {
    GUEST("GUEST"),
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN");

    private String value;
}