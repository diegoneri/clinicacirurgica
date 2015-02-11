/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cirurgica.generated.controller;

import br.com.cirurgica.generated.controller.exceptions.NonexistentEntityException;
import br.com.cirurgica.generated.model.Despesa;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import br.com.cirurgica.generated.model.TipoDespesa;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author dfelix3
 */
public class DespesaJpaController implements Serializable {

    public DespesaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Despesa despesa) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoDespesa cdTipoDespesa = despesa.getCdTipoDespesa();
            if (cdTipoDespesa != null) {
                cdTipoDespesa = em.getReference(cdTipoDespesa.getClass(), cdTipoDespesa.getCdTipoDespesa());
                despesa.setCdTipoDespesa(cdTipoDespesa);
            }
            em.persist(despesa);
            if (cdTipoDespesa != null) {
                cdTipoDespesa.getDespesaCollection().add(despesa);
                cdTipoDespesa = em.merge(cdTipoDespesa);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Despesa despesa) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Despesa persistentDespesa = em.find(Despesa.class, despesa.getCdDespesa());
            TipoDespesa cdTipoDespesaOld = persistentDespesa.getCdTipoDespesa();
            TipoDespesa cdTipoDespesaNew = despesa.getCdTipoDespesa();
            if (cdTipoDespesaNew != null) {
                cdTipoDespesaNew = em.getReference(cdTipoDespesaNew.getClass(), cdTipoDespesaNew.getCdTipoDespesa());
                despesa.setCdTipoDespesa(cdTipoDespesaNew);
            }
            despesa = em.merge(despesa);
            if (cdTipoDespesaOld != null && !cdTipoDespesaOld.equals(cdTipoDespesaNew)) {
                cdTipoDespesaOld.getDespesaCollection().remove(despesa);
                cdTipoDespesaOld = em.merge(cdTipoDespesaOld);
            }
            if (cdTipoDespesaNew != null && !cdTipoDespesaNew.equals(cdTipoDespesaOld)) {
                cdTipoDespesaNew.getDespesaCollection().add(despesa);
                cdTipoDespesaNew = em.merge(cdTipoDespesaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = despesa.getCdDespesa();
                if (findDespesa(id) == null) {
                    throw new NonexistentEntityException("The despesa with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Despesa despesa;
            try {
                despesa = em.getReference(Despesa.class, id);
                despesa.getCdDespesa();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The despesa with id " + id + " no longer exists.", enfe);
            }
            TipoDespesa cdTipoDespesa = despesa.getCdTipoDespesa();
            if (cdTipoDespesa != null) {
                cdTipoDespesa.getDespesaCollection().remove(despesa);
                cdTipoDespesa = em.merge(cdTipoDespesa);
            }
            em.remove(despesa);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Despesa> findDespesaEntities() {
        return findDespesaEntities(true, -1, -1);
    }

    public List<Despesa> findDespesaEntities(int maxResults, int firstResult) {
        return findDespesaEntities(false, maxResults, firstResult);
    }

    private List<Despesa> findDespesaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Despesa as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Despesa findDespesa(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Despesa.class, id);
        } finally {
            em.close();
        }
    }

    public int getDespesaCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Despesa as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
