package com.nayera.babylon.data.models;

public class ApiResponse<T> {

    private Status status;
    private T data;


    public ApiResponse(T data) {
        this(data, null);
    }

    public ApiResponse(Status status) {
        this(null, status);
    }

    public ApiResponse(T data, Status status) {
        this.data = data;
        status = status;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


}
