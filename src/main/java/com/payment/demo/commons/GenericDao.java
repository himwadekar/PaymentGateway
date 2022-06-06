package com.payment.demo.commons;


import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface GenericDao<Entity, Pk> {

    @Transactional
    Entity create(Entity anEntity);

    @Transactional
    Entity update(Entity anEntity);

    @Transactional
    void delete(Entity anEntity);

    @Transactional
    void deleteByPk(Pk entityPk);

    boolean contains(Entity anEntity);

    Entity findByPk(Pk entityPk);

    List<Entity> findByExample(Entity refEntity, String[] excludeProperty);

    int getSearchRecordsCount(String query);

}