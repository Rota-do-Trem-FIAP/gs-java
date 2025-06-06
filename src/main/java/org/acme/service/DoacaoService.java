package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import org.acme.entity.DoacaoEntity;
import org.acme.repository.DoacaoRepository;

import java.util.List;

@ApplicationScoped
public class DoacaoService {

    private final DoacaoRepository doacaoRepository;

    public DoacaoService(DoacaoRepository doacaoRepository) {
        this.doacaoRepository = doacaoRepository;
    }

    public List<DoacaoEntity> findAll(int page, int pageSize) {
        try {
            return doacaoRepository.findAll().page(page, pageSize).list();
        } catch (WebApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw new WebApplicationException("Erro ao buscar doações", Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    public DoacaoEntity findById(Integer id) {
        try {
            return doacaoRepository.findByIdOptional(id)
                    .orElseThrow(() -> new WebApplicationException("Doação não encontrada com id: " + id, Response.Status.NOT_FOUND));
        } catch (WebApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw new WebApplicationException("Erro ao buscar doação", Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    public DoacaoEntity create(DoacaoEntity doacao) {
        try {
            doacaoRepository.persist(doacao);
            return doacao;
        } catch (WebApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw new WebApplicationException("Erro ao criar doação", Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    public DoacaoEntity update(Integer id, DoacaoEntity updated) {
        try {
            DoacaoEntity doacao = findById(id);
            doacao.setProduto(updated.getProduto());
            doacao.setUsuarioDoador(updated.getUsuarioDoador());
            doacao.setUsuarioReceptor(updated.getUsuarioReceptor());
            doacao.setValorEstimado(updated.getValorEstimado());
            doacao.setStatus(updated.getStatus());

            doacaoRepository.persist(doacao);
            return doacao;
        } catch (WebApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw new WebApplicationException("Erro ao atualizar doação", Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    public void delete(Integer id) {
        try {
            DoacaoEntity doacao = findById(id);
            doacaoRepository.delete(doacao);
        } catch (WebApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw new WebApplicationException("Erro ao excluir doação", Response.Status.INTERNAL_SERVER_ERROR);
        }
    }
}
