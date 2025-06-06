package org.acme.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.entity.ProdutoEntity;

import java.util.List;

@ApplicationScoped
public class ProdutoRepository implements PanacheRepositoryBase<ProdutoEntity, Integer> {
    public List<ProdutoEntity> buscarPorNome(String nome) {
        return find("LOWER(nome_produto) LIKE LOWER(?1)", "%" + nome + "%").list();
    }
}
