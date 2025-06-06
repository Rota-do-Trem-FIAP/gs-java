package org.acme.controller;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.entity.DoacaoEntity;
import org.acme.service.DoacaoService;

@Path("/doacoes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DoacaoController {

    private final DoacaoService doacaoService;

    public DoacaoController(DoacaoService doacaoService) {
        this.doacaoService = doacaoService;
    }

    @GET
    public Response findAll(@QueryParam("page") @DefaultValue("0") Integer page,
                            @QueryParam("pageSize") @DefaultValue("20") Integer pageSize) {
        return Response.ok(doacaoService.findAll(page, pageSize)).build();
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Integer id) {
        return Response.ok(doacaoService.findById(id)).build();
    }

    @POST
    @Transactional
    public Response create(DoacaoEntity doacao) {
        return Response.ok(doacaoService.create(doacao)).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response update(@PathParam("id") Integer id, DoacaoEntity doacao) {
        return Response.ok(doacaoService.update(id, doacao)).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response delete(@PathParam("id") Integer id) {
        doacaoService.delete(id);
        return Response.noContent().build();
    }
}
