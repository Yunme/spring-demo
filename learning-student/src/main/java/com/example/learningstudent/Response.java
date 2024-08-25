package com.example.learningstudent;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Response<T> {

    private T data;
    private boolean success;
    private String message;

    public static <T> Response<T> success(T data) {
        Response<T> response = new Response<T>();
        response.success = true;
        response.data = data;
        return response;
    }

    public static <T> Response<T> fail(String message) {
        Response<T> response = new Response<T>();
        response.success = false;
        response.message = message;
        return response;
    }
}
