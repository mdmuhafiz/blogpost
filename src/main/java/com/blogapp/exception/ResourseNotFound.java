package com.blogapp.exception;

public class ResourseNotFound extends RuntimeException{
    public ResourseNotFound(String message){
        super(message);
    }
}
