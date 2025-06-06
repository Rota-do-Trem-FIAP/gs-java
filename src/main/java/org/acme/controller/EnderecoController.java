package org.acme.controller;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.entity.EnderecoEntity;
import org.acme.service.EnderecoService;

import java.util.List;

@Path("/enderecos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EnderecoController {

    private final EnderecoService enderecoService;

    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @GET
    public Response findAll(@QueryParam("page") @DefaultValue("0") Integer page,
                            @QueryParam("pageSize") @DefaultValue("10") Integer pageSize) {
        return Response.ok(enderecoService.findAll(page, pageSize)).build();
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Integer id) {
        return Response.ok(enderecoService.findById(id)).build();
    }

    @POST
    @Transactional
    public Response create(EnderecoEntity endereco) {
        return Response.ok(enderecoService.create(endereco)).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response update(@PathParam("id") Integer id, EnderecoEntity endereco) {
        return Response.ok(enderecoService.update(id, endereco)).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response delete(@PathParam("id") Integer id) {
        enderecoService.delete(id);
        return Response.noContent().build();
    }

    @GET
    @Path("/buscarlogradouro")
    public Response buscarPorLogradouro(@QueryParam("logradouro") String logradouro) {
        List<EnderecoEntity> enderecos = enderecoService.buscarPorLogradouro(logradouro);
        return Response.ok(enderecos).build();
    }

    @GET
    @Path("/buscarbairro")
    public Response buscarPorBairro(@QueryParam("bairro") String bairro) {
        List<EnderecoEntity> enderecos = enderecoService.buscarPorBairro(bairro);
        return Response.ok(enderecos).build();
    }
}
