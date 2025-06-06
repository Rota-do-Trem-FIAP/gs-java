package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import org.acme.entity.ProdutoEntity;
import org.acme.repository.ProdutoRepository;

import java.util.List;

@ApplicationScoped
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<ProdutoEntity> findAll(int page, int pageSize) {
        try {
            return produtoRepository.findAll().page(page, pageSize).list();
        } catch (Exception e) {
            throw new WebApplicationException("Erro ao listar produtos", Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    public ProdutoEntity findById(Integer id) {
        try {
            return produtoRepository.findByIdOptional(id)
                    .orElseThrow(() -> new WebApplicationException("Produto não encontrado com id: " + id, Response.Status.NOT_FOUND));
        } catch (WebApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw new WebApplicationException("Erro ao buscar produto", Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    public ProdutoEntity create(ProdutoEntity produto) {
        try {
            produtoRepository.persist(produto);
            return produto;
        } catch (Exception e) {
            throw new WebApplicationException("Erro ao criar produto", Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    public ProdutoEntity update(Integer id, ProdutoEntity updated) {
        try {
            ProdutoEntity produto = findById(id);
            produto.setNomeProduto(updated.getNomeProduto());
            produto.setDescricao(updated.getDescricao());
            produto.setQuantidade(updated.getQuantidade());
            produto.setQuantidadeDescricao(updated.getQuantidadeDescricao());
            produto.setValidadesDias(updated.getValidadesDias());
            produto.setValorEstimado(updated.getValorEstimado());
            produto.setStatus(updated.getStatus());
            produto.setUsuario(updated.getUsuario());

            produtoRepository.persist(produto);
            return produto;
        } catch (WebApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw new WebApplicationException("Erro ao atualizar produto", Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    public void delete(Integer id) {
        try {
            ProdutoEntity produto = findById(id);
            produtoRepository.delete(produto);
        } catch (WebApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw new WebApplicationException("Erro ao excluir produto", Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    public List<ProdutoEntity> buscarPorNome(String nome) {
        try {
            return produtoRepository.buscarPorNome(nome);
        } catch (Exception e) {
            throw new WebApplicationException("Erro ao buscar produto por nome", Response.Status.INTERNAL_SERVER_ERROR);
        }
    }
}
