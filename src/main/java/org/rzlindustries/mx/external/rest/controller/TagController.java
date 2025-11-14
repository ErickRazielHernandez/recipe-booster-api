package org.rzlindustries.mx.external.rest.controller;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.rzlindustries.mx.core.business.input.TagService;
import org.rzlindustries.mx.external.rest.dto.TagCreateDTO;
import org.rzlindustries.mx.external.rest.dto.TagDTO;
import org.rzlindustries.mx.util.error.BusinessException;

import java.util.List;

@Path("tags")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Tags", description = "Servicio para la gestión de etiquetas")
public class TagController {
    private final TagService tagService;

    @Inject
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GET
    @Operation(operationId = "listTagsByIdTipo", summary = "Obtiene todas las etiquetas del tipo dado.",
            description = "Obtiene todas las etiquetas del tipo dado.")
    public List<TagDTO> listTagsByIdTipo(
            @Parameter(description = "Identificador del tipo de tag") @QueryParam("idTipo") Integer idTipo
    ){
        return tagService.listByIdTipo(idTipo)
                .stream().map(TagDTO::fromEntity).toList();
    }

    @POST
    @Operation(operationId = "createAgrupamiento", summary = "Registra un agrupamiento")
    public Boolean create(@Valid TagCreateDTO tag){
        return tagService.create(tag.toEntity())
                .getOrElseThrow(errorCode -> new BusinessException(errorCode.getName()));
    }
}
