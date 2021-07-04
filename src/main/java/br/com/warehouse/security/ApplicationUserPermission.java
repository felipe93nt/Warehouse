package br.com.warehouse.security;

import lombok.Getter;

@Getter
public enum ApplicationUserPermission {

    EMPLOYEE_READ("employee:read"),
    EMPLOYER_READ("employer:read"),
    EMPLOYER_WRITE("employer:write");

    private final  String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }


}
