package com.tera.candidatemanagement.candidate.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Gender {
    MALE,
    FEMALE;

    @JsonCreator
    public static Gender fromString(String value) {
        try {
            return Gender.valueOf(value.toUpperCase());
        } catch (Exception e) {
            throw new IllegalArgumentException("Gender must be either MALE or FEMALE");
        }
    }
}
