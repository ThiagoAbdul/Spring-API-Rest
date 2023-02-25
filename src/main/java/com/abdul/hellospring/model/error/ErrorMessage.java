package com.abdul.hellospring.model.error;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import lombok.Getter;

@Getter
public class ErrorMessage {

    private String title;
    private Integer statusCode;
    private String message;
    private String dateTime;

    public ErrorMessage(String title, Integer statusCode, String message){
        this();
        this.title = title;
        this.statusCode = statusCode;
        this.message = message;
    }

    private ErrorMessage(){
        super();
        this.dateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
    }

    public static Builder builder(String title){
        return new Builder(title);
    }

    public static class Builder{
        private ErrorMessage errorMessage;

        private Builder(String title){
            errorMessage = new ErrorMessage();
            errorMessage.title = title;
        }

        public Builder statusCode(int statusCode){
            errorMessage.statusCode = statusCode;
            return this;
        }

        public Builder message(String message){
            errorMessage.message = message;
            return this;
        }

        public ErrorMessage build(){
            return this.errorMessage;
        }
    }


}

