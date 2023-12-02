package com.tejashwin.admin.constant;

public enum ErrorCode {


    AA_OO1("Invalid Credentials");

    private final String message;


    ErrorCode(final String message) {
        this.message = message;
    }

    public String message() {
        return this.message;
    }
}
