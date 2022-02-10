package com.revature.thevault.utility.enums;

public enum ResponseType {

    CREATE("Create"),
    DELETE("Delete"),
    UPDATE("Update"),
    FAIL("Fail"),
    TRANSFER("Transfer");

    private String requestType;

    public String getRequestType(){
        return this.requestType;
    }

    ResponseType(String requestType){
        this.requestType = requestType;
    }

}
