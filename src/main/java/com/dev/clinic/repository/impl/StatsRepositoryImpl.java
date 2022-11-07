package com.dev.clinic.repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;

import com.dev.clinic.model.ReceiptExamination;
import com.dev.clinic.model.ReceiptPrescription;
import com.dev.clinic.repository.StatsRepository;

@Repository
public class StatsRepositoryImpl implements StatsRepository{

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Object[]> getStatsRevenueReMonthByYear(int year) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);
        Root rootRe = query.from(ReceiptExamination.class);
        // Root rootRp = query.from(ReceiptPrescription.class);
        List<Predicate> predicates = new ArrayList<>();
        Predicate p1 = builder.equal(rootRe.get("isPayment"), 1);
        predicates.add(p1);
        // Predicate p2 = builder.equal(rootRp.get("isPayment"), true);
        // predicates.add(p2);
        Predicate p3 = builder.equal(builder.function("YEAR", Integer.class, rootRe.get("createdDate")), year);
        predicates.add(p3);
        // Predicate p4 = builder.equal(builder.function("YEAR", Integer.class, rootRp.get("createdDate")), year);
        // predicates.add(p4);
        // List<Predicate> predicates = new ArrayList<>();
        // Predicate p1 = builder.equal(rootOrderAgency.get("agency").get("id"), agency.getId());
        // predicates.add(p1);
        // Predicate p2 = builder.equal(rootOrder.get("id"), rootOrderAgency.get("orders").get("id"));
        // predicates.add(p2);
        // Predicate p3 = builder.equal(rootOrderDetail.get("orderAgency").get("id"), rootOrderAgency.get("id"));
        // predicates.add(p3);
        // Predicate p5 = builder.equal(builder.function("YEAR", Integer.class, rootOrder.get("createdDate")), year);
        // predicates.add(p5);
        query.where(predicates.toArray(new Predicate[]{}));
        query.multiselect(builder.function("MONTH", Integer.class, rootRe.get("createdDate")), builder.sum(rootRe.get("priceTotal")));
        query.groupBy(builder.function("MONTH", Integer.class, rootRe.get("createdDate")));
        query.orderBy(builder.asc(builder.function("MONTH", Integer.class, rootRe.get("createdDate"))));
        Query q = em.createQuery(query);
        return q.getResultList();
    }

    @Override
    public List<Object[]> getStatsRevenueRpMonthByYear(int year) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);
        Root rootRp = query.from(ReceiptPrescription.class);
        List<Predicate> predicates = new ArrayList<>();
        Predicate p1 = builder.equal(rootRp.get("isPayment"), 1);
        predicates.add(p1);
        Predicate p2 = builder.equal(builder.function("YEAR", Integer.class, rootRp.get("createdDate")), year);
        predicates.add(p2);
        query.where(predicates.toArray(new Predicate[]{}));
        query.multiselect(builder.function("MONTH", Integer.class, rootRp.get("createdDate")), builder.sum(rootRp.get("priceTotal")));
        query.groupBy(builder.function("MONTH", Integer.class, rootRp.get("createdDate")));
        query.orderBy(builder.asc(builder.function("MONTH", Integer.class, rootRp.get("createdDate"))));
        Query q = em.createQuery(query);
        return q.getResultList();
    }
    
}
