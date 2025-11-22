package org.rzlindustries.mx.external.rest.controller;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.rzlindustries.mx.core.business.input.EtiquetaService;
import org.rzlindustries.mx.external.rest.dto.EtiquetaCreateDTO;
import org.rzlindustries.mx.external.rest.dto.EtiquetaDTO;
import org.rzlindustries.mx.utils.error.BusinessException;

import java.util.List;

@Path("etiquetas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Etiqueta", description = "Servicio para la gestión de etiquetas")
public class EtiquetaController {
    private final EtiquetaService etiquetaService;

    @Inject
    public EtiquetaController(EtiquetaService etiquetaService) {
        this.etiquetaService = etiquetaService;
    }

    @GET
    @Operation(operationId = "listEtiquetasByIdTipo", summary = "Obtiene todas las etiquetas del tipo dado.",
            description = "Obtiene todas las etiquetas del tipo dado.")
    public List<EtiquetaDTO> listEtiquetasByIdTipo(
            @Parameter(description = "Identificador del tipo de etiqueta") @QueryParam("idTipo") Integer idTipo,
            @Parameter(description = "Filtro de búsqueda por nombre") @QueryParam("nombre") String nombre
    ){
        return etiquetaService.listByIdTipoAndNombre(idTipo, nombre)
                .stream().map(EtiquetaDTO::fromEntity).toList();
    }

    @POST
    @Operation(operationId = "createEtiqueta", summary = "Registra una nueva etiqueta.",
            description = "Registra una nueva etiqueta.")
    public Boolean create(@Valid EtiquetaCreateDTO etiqueta){
        return etiquetaService.create(etiqueta.toEntity())
                .getOrElseThrow(errorCode -> new BusinessException(errorCode.getName()));
    }

    @DELETE
    @Path("{idEtiqueta}")
    @Operation(operationId = "deleteEtiqueta", summary = "Elimina una etiqueta dada.",
            description = "Elimina una etiqueta dada.")
    public Boolean delete(@Parameter(description = "Identificador de la etiqueta") @PathParam("idEtiqueta") Integer idEtiqueta){
        return etiquetaService.delete(idEtiqueta)
                .getOrElseThrow(errorCode -> new BusinessException(errorCode.getName()));
    }
}
