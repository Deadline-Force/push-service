package com.deadlineforce.backend.entity.converter;

import com.deadlineforce.backend.security.details.AuthorizationRoles;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class AuthorizationRolesConverter implements AttributeConverter<AuthorizationRoles, Integer> {
    @Override
    public Integer convertToDatabaseColumn(AuthorizationRoles authorizationRoles) {
        return authorizationRoles.getId();
    }

    @Override
    public AuthorizationRoles convertToEntityAttribute(Integer integer) {
        return AuthorizationRoles.asValue(integer);
    }
}
