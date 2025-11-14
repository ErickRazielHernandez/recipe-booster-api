package org.rzlindustries.mx.util.error;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {
    private final String detail;

    public BusinessException(String code){
        super(code);
        this.detail = null;
    }

    public BusinessException(String code, String detail){
        super(code);
        this.detail = detail;
    }
}
