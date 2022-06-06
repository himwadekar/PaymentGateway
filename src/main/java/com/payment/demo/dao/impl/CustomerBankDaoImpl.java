package com.payment.demo.dao.impl;

import com.payment.demo.commons.HibernateGenericDao;
import com.payment.demo.dao.CustomerBankDao;
import com.payment.demo.model.CustomerBank;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;


import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository("CustomerBankDaoImpl")
@Slf4j
public class CustomerBankDaoImpl extends HibernateGenericDao<CustomerBank, UUID> implements CustomerBankDao {
    public CustomerBankDaoImpl(EntityManager entityManager) {
        super(CustomerBank.class, entityManager);
    }
    @Override
    public CustomerBank getCustomerDetails(String cardNumber) throws Exception {
        log.info("Inside CustomerBank getCustomerDetails");
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CustomerBank result = new CustomerBank();
        CriteriaQuery<CustomerBank> criteriaQuery = builder.createQuery(CustomerBank.class);
        Root<CustomerBank> customerBankRoot = criteriaQuery.from(CustomerBank.class);

        List<Predicate> predicateList = new ArrayList<>();

        predicateList.add(builder.equal(customerBankRoot.get("card_number"), cardNumber));
        criteriaQuery.select(customerBankRoot).where(predicateList.toArray(new Predicate[]{}));

        result = getEntityManager().createQuery(criteriaQuery).getSingleResult();
        log.info("Customer Dao : getCustomerDetails : {}",result);
        return result;
    }
}
