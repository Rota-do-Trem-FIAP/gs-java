package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import org.acme.entity.EnderecoEntity;
import org.acme.repository.EnderecoRepository;

import java.util.List;

@ApplicationScoped
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;

    public EnderecoService(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    public List<EnderecoEntity> findAll(int page, int pageSize) {
        try {
            return enderecoRepository.findAll().page(page, pageSize).list();
        } catch (WebApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw new WebApplicationException("Erro ao buscar endereços", Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    public EnderecoEntity findById(Integer id) {
        try {
            return enderecoRepository.findByIdOptional(id)
                    .orElseThrow(() -> new WebApplicationException("Endereço não encontrado com id: " + id, Response.Status.NOT_FOUND));
        } catch (WebApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw new WebApplicationException("Erro ao buscar endereço", Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    public EnderecoEntity create(EnderecoEntity endereco) {
        try {
            enderecoRepository.persist(endereco);
            return endereco;
        } catch (WebApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw new WebApplicationException("Erro ao criar endereço", Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    public EnderecoEntity update(Integer id, EnderecoEntity updated) {
        try {
            EnderecoEntity endereco = findById(id);
            endereco.setLogradouro(updated.getLogradouro());
            endereco.setNumero(updated.getNumero());
            endereco.setBairro(updated.getBairro());
            endereco.setCep(updated.getCep());
            endereco.setUsuario(updated.getUsuario());

            enderecoRepository.persist(endereco);
            return endereco;
        } catch (WebApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw new WebApplicationException("Erro ao atualizar endereço", Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    public void delete(Integer id) {
        try {
            EnderecoEntity endereco = findById(id);
            enderecoRepository.delete(endereco);
        } catch (WebApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw new WebApplicationException("Erro ao excluir endereço", Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    public List<EnderecoEntity> buscarPorLogradouro(String logradouro) {
        try {
            return enderecoRepository.buscarPorLogradouro(logradouro);
        } catch (WebApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw new WebApplicationException("Erro ao buscar por logradouro", Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    public List<EnderecoEntity> buscarPorBairro(String bairro) {
        try {
            return enderecoRepository.buscarPorBairro(bairro);
        } catch (WebApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw new WebApplicationException("Erro ao buscar por bairro", Response.Status.INTERNAL_SERVER_ERROR);
        }
    }
}
