package org.acme.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.entity.DoacaoEntity;

@ApplicationScoped
public class DoacaoRepository implements PanacheRepositoryBase<DoacaoEntity, Integer> {
}
