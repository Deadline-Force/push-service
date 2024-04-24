package com.deadlineforce.backend.model.auth;

import lombok.Builder;

@Builder
public record JWT(String token) {
}
