package org.rzlindustries.mx.utils.error;

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

import static org.rzlindustries.mx.utils.error.ErrorMapper.buildErrorDetail;

@Slf4j
@Provider
public class RuntimeExceptionMapper implements ExceptionMapper<RuntimeException> {
    @Context
    UriInfo uriInfo;

    /**
     * Mapeo de excepciones no controladas
     *
     * @param ex excepción no controlada
     * @return response con el error 500
     */
    @Override
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response toResponse(RuntimeException ex) {
        var body = ErrorResponseDTO.builder()
                .message(Response.Status.INTERNAL_SERVER_ERROR.name())
                .status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode())
                .path(uriInfo.getPath())
                .details(List.of(buildErrorDetail(ErrorCodeEnum.REB_ERROR.name())))
                .build();
        return Response.serverError().entity(body).build();
    }
}
