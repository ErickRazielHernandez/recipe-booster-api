package org.rzlindustries.mx.util.error;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Provider
public class BusinessExceptionMapper implements ExceptionMapper<BusinessException> {
    @Context
    UriInfo uriInfo;

    /**
     * Mapeo de las excepciones de negocio
     * @param ex excepción de negocio
     * @return response con el error 400 y un error code
     */
    @Override
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response toResponse(BusinessException ex) {
        var status = Response.Status.BAD_REQUEST;
        if (ErrorCodeEnum.REB_ERROR.name().equals(ex.getMessage())) {
            status = Response.Status.INTERNAL_SERVER_ERROR;
        }
        else if (ErrorCodeEnum.REB_NOT_FOUND.name().equals(ex.getMessage())) {
            status = Response.Status.NOT_FOUND;
        }
        var body = ErrorResponseDTO.builder()
                .message(status.name())
                .status(status.getStatusCode())
                .path(uriInfo.getPath())
                .details(List.of(ErrorMapper.buildErrorDetail(ex.getMessage())))
                .build();
        return Response.status(status).entity(body).build();
    }
}
