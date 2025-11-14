package org.rzlindustries.mx.external.rest.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.rzlindustries.mx.core.business.input.TagService;
import org.rzlindustries.mx.external.rest.dto.TagCreateDTO;

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

    @POST
    @Schema()
    public Boolean create(
            @Valid TagCreateDTO tag
    ){
        return tagService.crate(tag.toEntity());
    }
}
