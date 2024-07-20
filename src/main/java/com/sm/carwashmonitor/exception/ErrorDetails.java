package com.sm.carwashmonitor.exception;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ErrorDetails {
    private LocalDateTime timeStamp;
    private String message;
}
