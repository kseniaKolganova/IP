package com.example.kinoteatr.model;

import org.springframework.security.core.GrantedAuthority;

public enum RoleEnum implements GrantedAuthority {
    USER,
    ADMIN,
    CASHIER;
    @Override
    public String getAuthority()
    {
        return name();
    }
}
