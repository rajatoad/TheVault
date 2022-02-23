package com.revature.thevault.utility;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JWTInfo {
    private int userId;
    private String username;

    public JWTInfo(){}

    public JWTInfo(int userId, String username){
        this.userId = userId;
        this.username = username;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "{\"JWTInfo\":{"
                + "\"userId\":\"" + userId + "\""
                + ", \"username\":\"" + username + "\""
                + "}}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JWTInfo)) return false;
        JWTInfo jwtInfo = (JWTInfo) o;
        return getUserId() == jwtInfo.getUserId() && Objects.equals(getUsername(), jwtInfo.getUsername());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), getUsername());
    }
}
