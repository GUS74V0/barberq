package com.br.barberq.barberq.model;

public class LoginResponse {
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    private Long id;
    public String getUserType() {
        return userType;
    }
    public void setUserType(String userType) {
        this.userType = userType;
    }
    private String userType;

    public LoginResponse(Long id, String userType) {
        this.id = id;
        this.userType = userType;
    }

}
