package com.steam.discount.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

@EqualsAndHashCode(callSuper = true)
@Data
public class BizException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1;
    private final int code;
    private final String message;

    public BizException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public BizException(String message) {
        this(500, message);
    }

    @Override
    public String getMessage() {
        return message;
    }
}
