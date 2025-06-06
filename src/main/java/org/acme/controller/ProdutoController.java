package org.acme.controller;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.entity.ProdutoEntity;
import org.acme.service.ProdutoService;

import java.util.List;

@Path("/produtos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GET
    public Response findAll(@QueryParam("page") @DefaultValue("0") Integer page,
                            @QueryParam("pageSize") @DefaultValue("15") Integer pageSize) {
        return Response.ok(produtoService.findAll(page, pageSize)).build();
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Integer id) {
        return Response.ok(produtoService.findById(id)).build();
    }

    @POST
    @Transactional
    public Response create(ProdutoEntity produto) {
        return Response.ok(produtoService.create(produto)).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response update(@PathParam("id") Integer id, ProdutoEntity produto) {
        return Response.ok(produtoService.update(id, produto)).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response delete(@PathParam("id") Integer id) {
        produtoService.delete(id);
        return Response.noContent().build();
    }

    @GET
    @Path("/buscarproduto")
    public Response buscarPorNome(@QueryParam("produto") String produto) {
        List<ProdutoEntity> produtos = produtoService.buscarPorNome(produto);
        return Response.ok(produtos).build();
    }
}
