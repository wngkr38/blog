package com.estsoft.demo.blog.exception;

public class NotExistsIdException extends RuntimeException {
    public NotExistsIdException(Long id) {
        super("not exists id: " + id);
    }
}
