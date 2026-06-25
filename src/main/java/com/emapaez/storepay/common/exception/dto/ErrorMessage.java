package com.emapaez.storepay.common.exception.dto;

import java.time.LocalDateTime;

public record ErrorMessage(LocalDateTime timestamp,
                           int status,
                           String error,
                           String message) {

}
