package com.banking.persistence;

import com.banking.bank.Account;
import com.banking.bank.Customer;
import com.banking.bank.Transaction;
import org.glassfish.jersey.internal.inject.Custom;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import java.util.List;

/**
 * @author Hannah ORourke
 * @version 1.0
 */
public class PersistenceManager {

    private EntityManagerFactory emfactory;
    private EntityManager entityManager;

    public PersistenceManager() {
        emfactory = Persistence.createEntityManagerFactory("Bank");
        entityManager = emfactory.createEntityManager();
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    /**
     * Returns a single result object for a typed query
     *
     * @param query
     * @return Object
     */
    public Object getSingleResult(TypedQuery<?>  query) {
        query.setMaxResults(1);

        List<?> resultList = query.getResultList();
        if (resultList == null || resultList.isEmpty()) {
            return null;
        }

        return query.getSingleResult();
    }

    /**
     * Finds an object of either type Account,
     * Customer or Transaction by id. Returns null
     * if an object isn't a valid type
     *
     * @param id
     * @return Object
     */
    public Object find(Class clazz, int id) {
        return entityManager.find(clazz, id);
    }

    /**
     * Persist an object
     * @param o
     */
    public void persist(Object o) {
        entityManager.persist(o);
    }

    /**
     * Commits a entity within a valid transaction
     */
    public void commit() {
        entityManager.getTransaction().commit();
    }

    /**
     * Start a transaction
     */
    public void start() {
        entityManager.getTransaction().begin();
    }

    /**
     * Closes the entity manager factory along with the
     * entity manager. Is used after a PersistenceManager
     * instance is no longer required.
     */
    public void close() {
        emfactory.close();
        entityManager.close();
    }

}
