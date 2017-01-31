package com.tiy.practice;

/**
 * Created by crci1 on 1/31/2017.
 */
public class StatusMessage {
    public static final String SUCCESS = "SUCCESS";
    public static final String ERROR = "ERROR";
    // Two possible values: SUCCESS and ERROR
    private String status;
    // this will only have a value if there is an error
    // it will be null otherwise
    private String errorMessage;

    public StatusMessage() {
    }

    public StatusMessage(String status, String errorMessage) {
        this.status = status;
        this.errorMessage = errorMessage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
