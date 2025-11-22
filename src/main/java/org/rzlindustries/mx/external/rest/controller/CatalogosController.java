package org.rzlindustries.mx.external.rest.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.rzlindustries.mx.core.business.input.CatalogoService;
import org.rzlindustries.mx.external.rest.dto.CatalogoDTO;
import org.rzlindustries.mx.utils.error.BusinessException;

import java.util.Collections;
import java.util.List;

@Path("catalogos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Catalogo", description = "Servicio para la consulta de catálogos")
public class CatalogosController {
    private final CatalogoService catalogoService;

    @Inject
    public CatalogosController(CatalogoService catalogoService) {
        this.catalogoService = catalogoService;
    }

    @GET
    @Operation(operationId = "listAllTipoEtiqueta", summary = "Obtiene el catálogo de tipos de etiquetas.",
            description = "Obtiene el catálogo de tipos de etiquetas.")
    public List<CatalogoDTO> listAllTipoEtiqueta(
            @Parameter(description = "Filtro por estado del catálogo") @QueryParam("activo") Boolean activo
    ){
        return catalogoService.listAllTipoEtiqueta(activo)
                .stream().map(CatalogoDTO::fromEntity).toList();
    }
}
