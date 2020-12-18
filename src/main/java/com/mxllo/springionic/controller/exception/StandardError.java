package com.mxllo.springionic.controller.exception;

import lombok.*;

import java.io.Serializable;


@Getter
@Setter
public class StandardError implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer status;
    private String message;
    private Long timestamp;

    public StandardError(Integer status, String message, Long timestamp) {
        super();
        this.status = status;
        this.message = message;
        this.timestamp = timestamp;
    }
}
