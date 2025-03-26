package com.trantienloi.jobhunter.demo.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class LoginDTO {
    @NotBlank(message = "user name khong duoc bo trong")
    private String username;
    @NotBlank(message = "pass world khong duoc bo trong")
    private String passworld;

    public String getUsername() {
        return username;
    }

    public void setUsername(String userName) {
        this.username = userName;
    }

    public String getPassworld() {
        return passworld;
    }

    public void setPassworld(String passworld) {
        this.passworld = passworld;
    }

}
