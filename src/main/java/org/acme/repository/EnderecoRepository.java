package org.acme.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.entity.EnderecoEntity;

import java.util.List;

@ApplicationScoped
public class EnderecoRepository implements PanacheRepositoryBase<EnderecoEntity, Integer> {

    public List<EnderecoEntity> buscarPorLogradouro(String logradouro) {
        return find("LOWER(logradouro) LIKE LOWER(?1)", "%" + logradouro + "%").list();
    }

    public List<EnderecoEntity> buscarPorBairro(String bairro) {
        return find("LOWER(bairro) LIKE LOWER(?1)", "%" + bairro + "%").list();
    }
}
