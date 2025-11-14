package org.rzlindustries.mx.util.error;

import jakarta.validation.ConstraintViolation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ErrorMapper {
    private ErrorMapper() {
        super();
    }

    /**
     * Convierte un ConstraintViolation a un ErrorDetailDTO
     *
     * @param ve  ConstraintViolation
     * @param <T> Tipo de la clase que contiene el error
     * @return ErrorDetailDTO con el detalle del error
     */
    public static <T> ErrorDetailDTO constraintToError(ConstraintViolation<T> ve) {
        String msg = ErrorCodeEnum.REB_ERROR.getDetail();
        try {
            var temp = ErrorCodeEnum.valueOf(ve.getMessage());
            msg = temp.getDetail();
        } catch (IllegalArgumentException e) {
            log.error("Enum del error no encontrado", e);
        }
        return ErrorDetailDTO.builder()
                .code(ve.getMessage())
                .message(msg)
                .path(ve.getPropertyPath().toString()).build();
    }

    /**
     * Convierte un error code a un ErrorDetailDTO
     *
     * @param code Código del error
     * @return ErrorDetailDTO con el detalle del error
     */
    public static ErrorDetailDTO buildErrorDetail(String code) {
        String msg = ErrorCodeEnum.REB_ERROR.getDetail();
        try {
            var temp = ErrorCodeEnum.valueOf(code);
            msg = temp.getDetail();
        } catch (IllegalArgumentException e) {
            log.error("Error enum not found", e);
        }
        return ErrorDetailDTO.builder()
                .code(code)
                .message(msg)
                .build();
    }

    /**
     * Convierte un error code a un {@link ErrorDetailDTO}
     *
     * @param code código de error
     * @param path url en donde ocurrió el error
     * @return entidad de tipo {@link ErrorDetailDTO}
     */
    public static ErrorDetailDTO buildErrorDetail(String code, String path) {
        String msg = ErrorCodeEnum.REB_ERROR.getDetail();
        try {
            var temp = ErrorCodeEnum.valueOf(code);
            msg = temp.getDetail();
        } catch (IllegalArgumentException e) {
            log.error("Error enum not found", e);
        }
        return ErrorDetailDTO.builder()
                .code(code)
                .message(msg)
                .path(path)
                .build();
    }
}
