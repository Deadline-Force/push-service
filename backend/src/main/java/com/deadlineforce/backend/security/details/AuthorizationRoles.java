package com.deadlineforce.backend.security.details;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum AuthorizationRoles {
    USER(1),
    EMPLOYEE(2, USER),
    ADMIN(3, USER, EMPLOYEE),
    ;

    @Getter
    private final int id;
    private final AuthorizationRoles[] dependencies;

    AuthorizationRoles(int id, AuthorizationRoles... dependencies) {
        this.id = id;
        this.dependencies = dependencies;
    }

    public static List<AuthorizationRoles> asList(AuthorizationRoles role) {
        List<AuthorizationRoles> roles = new ArrayList<>(Arrays.stream(role.dependencies).toList());
        roles.add(role);
        return roles;
    }

    public static AuthorizationRoles asValue(int id) {
        return Arrays.stream(AuthorizationRoles.values()).filter(e -> e.getId() == id).findFirst().orElseThrow();
    }
}
