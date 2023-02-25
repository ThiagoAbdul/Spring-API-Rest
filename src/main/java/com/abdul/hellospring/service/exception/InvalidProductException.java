package com.abdul.hellospring.service.exception;

public class InvalidProductException extends RuntimeException{

    public InvalidProductException(){
        super("Produto inválido");
    }

}
