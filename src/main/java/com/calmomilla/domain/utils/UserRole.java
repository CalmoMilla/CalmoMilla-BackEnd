package com.calmomilla.domain.utils;

public enum UserRole {

    ADMIN("admin"),
    USER("user"),
    PSICOLOGO("psicologo");

    private String role;

     UserRole(String role){
        this.role = role;
    }

    public String getRole(){
         return role;
    }

}
