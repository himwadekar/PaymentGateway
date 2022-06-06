package com.payment.demo.commons;


import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@NoRepositoryBean
@Slf4j
public class HibernateGenericDao<Entity, Pk> extends SimpleJpaRepository<Entity, Pk> implements GenericDao<Entity, Pk> {

    //private Logger logger = LogManager.getLogger(HibernateGenericDao.class);
    private Class<Entity> type;
    private EntityManager entityManager;
    public static final String DESC = "desc";

    public HibernateGenericDao(Class<Entity> type, EntityManager entityManager) {
        super(type, entityManager);
        this.type = type;
        this.entityManager = entityManager;
    }

    public Class<Entity> getType() {
        return this.type;
    }

    public void setType(Class<Entity> type) {
        this.type = type;
    }

    public EntityManager getEntityManager() {
        return this.entityManager;
    }

    public Entity create(Entity anEntity) {
        this.log.info("Inside @create, anEntity : {}", anEntity);
        if (anEntity == null) {
            this.log.warn("Cannot create a null entity.");
            throw new IllegalArgumentException("Cannot create null entity[" + this.getType().getName() + "]");
        } else {
            return super.save(anEntity);
        }
    }

    public Entity update(Entity anEntity) {
        this.log.info("Inside @update, anEntity : {}", anEntity);
        return this.create(anEntity);
    }

    public void deleteByPk(Pk entityPk) {
        this.log.info("Inside @deleteByPk, entityPk : {}", entityPk);
        if (entityPk == null) {
            this.log
                    .warn("Cannot delete entity[" + this.getType().getName() + "] since specified primary key is null");
            throw new IllegalArgumentException(
                    "Cannot delete entity[" + this.getType().getName() + "] since specified primary key is null");
        } else {
            super.deleteById(entityPk);
        }
    }

    public boolean contains(Entity anEntity) {
        if (anEntity == null) {
            this.log.warn("Illegal argument null for entity[" + this.getType().getName() + "] contains method");
            return false;
        } else {
            return super.exists(Example.of(anEntity));
        }
    }

    public Entity findByPk(Pk entityPk) {
        this.log.info("Inside findByPk, entityPk:{}", entityPk);
        if (entityPk == null) {
            this.log.warn("Cannot find entity[" + this.getType().getName() + "] with null primary key");
            return null;
        } else {
            Optional<Entity> findById = super.findById(entityPk);
            return findById.orElse(null);
        }
    }

    public List<Entity> findByExample(Entity refEntity, String[] excludeProperty) {
        this.log.info("performing @findByExample using entity : {} excludedProperty : {}", refEntity, excludeProperty);
        if (refEntity == null) {
            this.log.warn("Cannot delete a null entity.");
            throw new IllegalArgumentException("Cannot find null entity[" + this.getType().getName() + "]");
        } else {
            this.log.debug("entity object's class is [" + refEntity.getClass() + "]");
            this.log.debug("entity object's type is [" + this.getType() + "]");
            return super.findAll(Example.of(refEntity));
        }
    }

    public int getSearchRecordsCount(String query) {
        return 0;
    }

}
