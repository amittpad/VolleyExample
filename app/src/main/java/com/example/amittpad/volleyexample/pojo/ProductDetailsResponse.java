package com.example.amittpad.volleyexample.pojo;

import java.util.List;

public class ProductDetailsResponse {
    private String status;
    private String message;
    private List<Value> value = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Value> getValue() {
        return value;
    }

    public void setValue(List<Value> value) {
        this.value = value;
    }

}
