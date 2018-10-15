package ca.korichi.springcrud.controllers.exceptions;

import lombok.Value;

import java.time.Instant;

@Value
public class ExceptionInfo {
    private String time;
    private String message;

    public ExceptionInfo(String message) {
        this.message = message;
        this.time = Instant.now().toString();
    }
}
