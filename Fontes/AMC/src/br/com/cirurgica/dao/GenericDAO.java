/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cirurgica.dao;

import br.com.cirurgica.model.AbstractModel;
import java.lang.reflect.ParameterizedType;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author dfelix3
 */
public abstract class GenericDAO<A extends AbstractModel> {

    private EntityManagerFactory emf;
    private Integer pageSize;
    protected Class<A> type;

    public GenericDAO(final Class<A> clazz) {
        this.type = clazz;
        this.emf = MyEntityManager.getEntityManagerFactory();
        this.pageSize = 10;
    }

    protected final EntityManagerFactory getEntityManagerFactory() {
        return emf;
    }

    protected final String getLikeValue(String value) {
        return "%" + value + "%";
    }

    protected Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public Long getLastPage() {
        BigDecimal bd = new BigDecimal(getMaxResults())
                .divide(new BigDecimal(getPageSize()))
                .setScale(0 , BigDecimal.ROUND_UP);

        return bd.longValue();
    }

    public Class<A> getEntityClass() {
        return type;
    }

    public Long getMaxResults() {
        EntityManager em = getEntityManagerFactory().createEntityManager();
        try {
            String query = "select count(o) from " + this.type.getSimpleName() + " as o";
            Query q = em.createQuery(query);
            return ((Long) q.getSingleResult());
        } finally {
            em.close();
        }
    }

    private TypedQuery<A> getTypedDefaultQuery(EntityManager em) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<A> cq =
                cb.createQuery(this.type);
        Root<A> root = cq.from(this.type);

        cq = cq.select(root).orderBy(
                cb.asc(root.get(getOrderingFieldName())));

        TypedQuery<A> query = em.createQuery(cq);

        return query;

    }

    public List<A> findEntities(int page) {
        EntityManager em = null;

        try {
            em = getEntityManagerFactory().createEntityManager();
            TypedQuery<A> tq = getTypedDefaultQuery(em);
            tq.setMaxResults(this.getPageSize())
                    .setFirstResult((page - 1) * this.getPageSize());
            return tq.getResultList();
        } finally {
            em.close();
        }
    }

    public final List<A> findAll() {
        EntityManager em = null;
        try {
            em = getEntityManagerFactory().createEntityManager();
            return getTypedDefaultQuery(em).getResultList();
        } finally {
            em.close();
        }
    }

    public void closeEM() {
        if (MyEntityManager.getEntityManager().isOpen()) {
            MyEntityManager.getEntityManager().close();
        }
    }

    public abstract void incluir(A value) throws Exception;

    public abstract void alterar(A value) throws Exception;

    public abstract void excluir(A value) throws Exception;

    protected abstract String getOrderingFieldName();
}
