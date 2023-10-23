package com.taskmanager.api.domain;

import jakarta.validation.constraints.NotBlank;

public record TaskCreationData(@NotBlank String title, String description) {
}
