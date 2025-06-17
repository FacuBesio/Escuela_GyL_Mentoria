package com.school.app.application.utils.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GenericResponse {
    private int status;
    private String message;
    private LocalDateTime timestamp;

    public GenericResponse(int status, String message, LocalDateTime timestamp) {
        this.status = status;
        this.message = message;
        this.timestamp = timestamp;
    }
}