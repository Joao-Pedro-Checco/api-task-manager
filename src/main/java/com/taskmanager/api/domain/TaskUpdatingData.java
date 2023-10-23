package com.taskmanager.api.domain;

import jakarta.validation.constraints.NotNull;

public record TaskUpdatingData(@NotNull Long id, String title, String description) {
}
