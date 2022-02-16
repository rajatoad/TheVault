package com.revature.thevault.utility.enums;

public enum ResponseType {

    POST("Post"),
    DELETE("Delete"),
    PUT("Put"),
    FAIL("Fail"),
    GET("Get");

    private String requestType;

    public String getRequestType(){
        return this.requestType;
    }

    ResponseType(String requestType){
        this.requestType = requestType;
    }

}
