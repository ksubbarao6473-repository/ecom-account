package com.ecommerce.app.account.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErrorResponse {
    private String uuid;
    private int status;
    private String id;
    private String errorMessage;

}

