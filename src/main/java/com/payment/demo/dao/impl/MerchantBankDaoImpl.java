package com.payment.demo.dao.impl;

import com.payment.demo.commons.HibernateGenericDao;
import com.payment.demo.dao.MerchantBankDao;
import com.payment.demo.model.MerchantBank;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service("MerchantBankDaoImpl")
@Slf4j
public class MerchantBankDaoImpl extends HibernateGenericDao<MerchantBank, UUID> implements MerchantBankDao {
    public MerchantBankDaoImpl(EntityManager entityManager) {
        super(MerchantBank.class, entityManager);
    }

    @Override
    public MerchantBank getMerchantDetails(String merchantId) {

        try {
            log.info("Inside CustomerBank getCustomerDetails");
            CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
            MerchantBank result = new MerchantBank();
            CriteriaQuery<MerchantBank> criteriaQuery = builder.createQuery(MerchantBank.class);
            Root<MerchantBank> merchantBankRoot = criteriaQuery.from(MerchantBank.class);

            List<Predicate> predicateList = new ArrayList<>();

            predicateList.add(builder.equal(merchantBankRoot.get("merchant_id"), merchantId.toString()));
            criteriaQuery.select(merchantBankRoot).where(predicateList.toArray(new Predicate[]{}));

            result = getEntityManager().createQuery(criteriaQuery).getSingleResult();
            log.info("Merchant Dao : getMerchantDetails : {}",result);
            return result;
        } catch (Exception e) {
            log.error("Exception caught in MerchantBank Dao : {}", e.getMessage());
        }
        return null;
    }
}