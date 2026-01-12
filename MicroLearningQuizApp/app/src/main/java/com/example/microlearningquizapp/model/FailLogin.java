package com.example.microlearningquizapp.model;

public class FailLogin {

    private int status;
    private Error error;

    public FailLogin() {}

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }
}

