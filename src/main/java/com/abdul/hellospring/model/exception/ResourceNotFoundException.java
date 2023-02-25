package com.abdul.hellospring.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(){
        super("Recurso não encontrado");
    }

    public <T> ResourceNotFoundException(Class<T> clazz){
        super("Recurso não encontrado: " + clazz.getSimpleName());
    }

    
}
