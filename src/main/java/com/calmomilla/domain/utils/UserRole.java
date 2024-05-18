package com.calmomilla.domain.utils;

public enum UserRole {

    ADMIN("admin"),
    PACIENTE("paciente"),
    PSICOLOGO("psicologo");

    private String role;

     UserRole(String role){
        this.role = role;
    }

}
