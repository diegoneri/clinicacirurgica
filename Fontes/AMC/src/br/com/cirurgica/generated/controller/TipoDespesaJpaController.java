/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cirurgica.generated.controller;

import br.com.cirurgica.generated.controller.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import br.com.cirurgica.generated.model.Despesa;
import br.com.cirurgica.generated.model.TipoDespesa;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author dfelix3
 */
public class TipoDespesaJpaController implements Serializable {

    public TipoDespesaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoDespesa tipoDespesa) {
        if (tipoDespesa.getDespesaCollection() == null) {
            tipoDespesa.setDespesaCollection(new ArrayList<Despesa>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Despesa> attachedDespesaCollection = new ArrayList<Despesa>();
            for (Despesa despesaCollectionDespesaToAttach : tipoDespesa.getDespesaCollection()) {
                despesaCollectionDespesaToAttach = em.getReference(despesaCollectionDespesaToAttach.getClass(), despesaCollectionDespesaToAttach.getCdDespesa());
                attachedDespesaCollection.add(despesaCollectionDespesaToAttach);
            }
            tipoDespesa.setDespesaCollection(attachedDespesaCollection);
            em.persist(tipoDespesa);
            for (Despesa despesaCollectionDespesa : tipoDespesa.getDespesaCollection()) {
                TipoDespesa oldCdTipoDespesaOfDespesaCollectionDespesa = despesaCollectionDespesa.getCdTipoDespesa();
                despesaCollectionDespesa.setCdTipoDespesa(tipoDespesa);
                despesaCollectionDespesa = em.merge(despesaCollectionDespesa);
                if (oldCdTipoDespesaOfDespesaCollectionDespesa != null) {
                    oldCdTipoDespesaOfDespesaCollectionDespesa.getDespesaCollection().remove(despesaCollectionDespesa);
                    oldCdTipoDespesaOfDespesaCollectionDespesa = em.merge(oldCdTipoDespesaOfDespesaCollectionDespesa);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipoDespesa tipoDespesa) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoDespesa persistentTipoDespesa = em.find(TipoDespesa.class, tipoDespesa.getCdTipoDespesa());
            Collection<Despesa> despesaCollectionOld = persistentTipoDespesa.getDespesaCollection();
            Collection<Despesa> despesaCollectionNew = tipoDespesa.getDespesaCollection();
            Collection<Despesa> attachedDespesaCollectionNew = new ArrayList<Despesa>();
            for (Despesa despesaCollectionNewDespesaToAttach : despesaCollectionNew) {
                despesaCollectionNewDespesaToAttach = em.getReference(despesaCollectionNewDespesaToAttach.getClass(), despesaCollectionNewDespesaToAttach.getCdDespesa());
                attachedDespesaCollectionNew.add(despesaCollectionNewDespesaToAttach);
            }
            despesaCollectionNew = attachedDespesaCollectionNew;
            tipoDespesa.setDespesaCollection(despesaCollectionNew);
            tipoDespesa = em.merge(tipoDespesa);
            for (Despesa despesaCollectionOldDespesa : despesaCollectionOld) {
                if (!despesaCollectionNew.contains(despesaCollectionOldDespesa)) {
                    despesaCollectionOldDespesa.setCdTipoDespesa(null);
                    despesaCollectionOldDespesa = em.merge(despesaCollectionOldDespesa);
                }
            }
            for (Despesa despesaCollectionNewDespesa : despesaCollectionNew) {
                if (!despesaCollectionOld.contains(despesaCollectionNewDespesa)) {
                    TipoDespesa oldCdTipoDespesaOfDespesaCollectionNewDespesa = despesaCollectionNewDespesa.getCdTipoDespesa();
                    despesaCollectionNewDespesa.setCdTipoDespesa(tipoDespesa);
                    despesaCollectionNewDespesa = em.merge(despesaCollectionNewDespesa);
                    if (oldCdTipoDespesaOfDespesaCollectionNewDespesa != null && !oldCdTipoDespesaOfDespesaCollectionNewDespesa.equals(tipoDespesa)) {
                        oldCdTipoDespesaOfDespesaCollectionNewDespesa.getDespesaCollection().remove(despesaCollectionNewDespesa);
                        oldCdTipoDespesaOfDespesaCollectionNewDespesa = em.merge(oldCdTipoDespesaOfDespesaCollectionNewDespesa);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tipoDespesa.getCdTipoDespesa();
                if (findTipoDespesa(id) == null) {
                    throw new NonexistentEntityException("The tipoDespesa with id " + id + " no longer exists.");
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
            TipoDespesa tipoDespesa;
            try {
                tipoDespesa = em.getReference(TipoDespesa.class, id);
                tipoDespesa.getCdTipoDespesa();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoDespesa with id " + id + " no longer exists.", enfe);
            }
            Collection<Despesa> despesaCollection = tipoDespesa.getDespesaCollection();
            for (Despesa despesaCollectionDespesa : despesaCollection) {
                despesaCollectionDespesa.setCdTipoDespesa(null);
                despesaCollectionDespesa = em.merge(despesaCollectionDespesa);
            }
            em.remove(tipoDespesa);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipoDespesa> findTipoDespesaEntities() {
        return findTipoDespesaEntities(true, -1, -1);
    }

    public List<TipoDespesa> findTipoDespesaEntities(int maxResults, int firstResult) {
        return findTipoDespesaEntities(false, maxResults, firstResult);
    }

    private List<TipoDespesa> findTipoDespesaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from TipoDespesa as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public TipoDespesa findTipoDespesa(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoDespesa.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoDespesaCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from TipoDespesa as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
