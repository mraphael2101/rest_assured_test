package org.examples2.pojos;

public class MyResponse {
    private final String message;
    private final int status;

    // Constructor, getters, and setters
    public MyResponse(String message, int status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "MyResponse{" +
                "message='" + message + '\'' +
                ", status=" + status +
                '}';
    }
}
