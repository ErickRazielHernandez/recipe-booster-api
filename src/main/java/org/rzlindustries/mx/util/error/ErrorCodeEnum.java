package org.rzlindustries.mx.util.error;

import lombok.Getter;

@Getter
public enum ErrorCodeEnum implements ErrorCode {
    /**
     * REB_ERROR. Error genérico
     */
    REB_ERROR("Error inesperado"),
    /**
     * ERB-NOT_FOUND. Error de recurso no encontrado
     */
    REB_NOT_FOUND("Recurso no encontrado"),
    /**
     * REB-RN-S002. Campos obligatorios
     */
    REB_RNS002("Campos obligatorios"),
    /**
     * REB-RN-S003. Formato de los campos
     */
    REB_RNS003("Formato de los campos");

    private final String detail;

    ErrorCodeEnum(String detail) {
        this.detail = detail;
    }

    @Override
    public String getName() {
        return this.name();
    }
}
