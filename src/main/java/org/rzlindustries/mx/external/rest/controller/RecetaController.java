package org.rzlindustries.mx.external.rest.controller;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.rzlindustries.mx.core.business.input.RecetaService;
import org.rzlindustries.mx.external.rest.dto.RecetaCreateDTO;
import org.rzlindustries.mx.external.rest.dto.RecetaDTO;
import org.rzlindustries.mx.external.rest.dto.RecetaGetDTO;
import org.rzlindustries.mx.utils.error.BusinessException;

import java.util.List;

@Path("recetas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Receta", description = "Servicio para la gestión de recetas")
public class RecetaController {
    private final RecetaService recetaService;

    @Inject
    public RecetaController(RecetaService recetaService) {
        this.recetaService = recetaService;
    }

    @GET
    @Operation(operationId = "listAllRecetas", summary = "Obtiene todas las recetas.",
            description = "Obtiene todas las recetas.")
    public List<RecetaDTO> listAllRecetas(){
        return recetaService.listAll()
                .stream().map(RecetaDTO::fromEntity).toList();
    }

    @GET
    @Path("{idReceta}")
    @Operation(operationId = "getRecetaById", summary = "Obtiene la receta por el identificador dado.",
            description = "Obtiene la receta por el identificador dado.")
    public RecetaGetDTO getRecetaById(
            @Parameter(description = "Identificador de la receta") @PathParam("idReceta") Integer idReceta
    ){
        return recetaService.getById(idReceta).map(RecetaGetDTO::fromEntity)
                .getOrElseThrow(result -> new BusinessException(result.getName()));
    }

    @POST
    @Operation(operationId = "createReceta", summary = "Crea una nueva receta.",
            description = "Crea una nueva receta.")
    public Boolean createReceta(@Valid RecetaCreateDTO receta){
        return recetaService.create(receta.toEntity())
                .getOrElseThrow(result -> new BusinessException(result.getName()));
    }
}
