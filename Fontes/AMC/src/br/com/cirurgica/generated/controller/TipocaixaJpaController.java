/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cirurgica.generated.controller;

import br.com.cirurgica.generated.controller.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import br.com.cirurgica.generated.model.Caixa;
import br.com.cirurgica.generated.model.Tipocaixa;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author dfelix3
 */
public class TipocaixaJpaController implements Serializable {

    public TipocaixaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tipocaixa tipocaixa) {
        if (tipocaixa.getCaixaCollection() == null) {
            tipocaixa.setCaixaCollection(new ArrayList<Caixa>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Caixa> attachedCaixaCollection = new ArrayList<Caixa>();
            for (Caixa caixaCollectionCaixaToAttach : tipocaixa.getCaixaCollection()) {
                caixaCollectionCaixaToAttach = em.getReference(caixaCollectionCaixaToAttach.getClass(), caixaCollectionCaixaToAttach.getCdCaixa());
                attachedCaixaCollection.add(caixaCollectionCaixaToAttach);
            }
            tipocaixa.setCaixaCollection(attachedCaixaCollection);
            em.persist(tipocaixa);
            for (Caixa caixaCollectionCaixa : tipocaixa.getCaixaCollection()) {
                Tipocaixa oldCdTipoCaixaOfCaixaCollectionCaixa = caixaCollectionCaixa.getCdTipoCaixa();
                caixaCollectionCaixa.setCdTipoCaixa(tipocaixa);
                caixaCollectionCaixa = em.merge(caixaCollectionCaixa);
                if (oldCdTipoCaixaOfCaixaCollectionCaixa != null) {
                    oldCdTipoCaixaOfCaixaCollectionCaixa.getCaixaCollection().remove(caixaCollectionCaixa);
                    oldCdTipoCaixaOfCaixaCollectionCaixa = em.merge(oldCdTipoCaixaOfCaixaCollectionCaixa);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tipocaixa tipocaixa) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tipocaixa persistentTipocaixa = em.find(Tipocaixa.class, tipocaixa.getCdTipoCaixa());
            Collection<Caixa> caixaCollectionOld = persistentTipocaixa.getCaixaCollection();
            Collection<Caixa> caixaCollectionNew = tipocaixa.getCaixaCollection();
            Collection<Caixa> attachedCaixaCollectionNew = new ArrayList<Caixa>();
            for (Caixa caixaCollectionNewCaixaToAttach : caixaCollectionNew) {
                caixaCollectionNewCaixaToAttach = em.getReference(caixaCollectionNewCaixaToAttach.getClass(), caixaCollectionNewCaixaToAttach.getCdCaixa());
                attachedCaixaCollectionNew.add(caixaCollectionNewCaixaToAttach);
            }
            caixaCollectionNew = attachedCaixaCollectionNew;
            tipocaixa.setCaixaCollection(caixaCollectionNew);
            tipocaixa = em.merge(tipocaixa);
            for (Caixa caixaCollectionOldCaixa : caixaCollectionOld) {
                if (!caixaCollectionNew.contains(caixaCollectionOldCaixa)) {
                    caixaCollectionOldCaixa.setCdTipoCaixa(null);
                    caixaCollectionOldCaixa = em.merge(caixaCollectionOldCaixa);
                }
            }
            for (Caixa caixaCollectionNewCaixa : caixaCollectionNew) {
                if (!caixaCollectionOld.contains(caixaCollectionNewCaixa)) {
                    Tipocaixa oldCdTipoCaixaOfCaixaCollectionNewCaixa = caixaCollectionNewCaixa.getCdTipoCaixa();
                    caixaCollectionNewCaixa.setCdTipoCaixa(tipocaixa);
                    caixaCollectionNewCaixa = em.merge(caixaCollectionNewCaixa);
                    if (oldCdTipoCaixaOfCaixaCollectionNewCaixa != null && !oldCdTipoCaixaOfCaixaCollectionNewCaixa.equals(tipocaixa)) {
                        oldCdTipoCaixaOfCaixaCollectionNewCaixa.getCaixaCollection().remove(caixaCollectionNewCaixa);
                        oldCdTipoCaixaOfCaixaCollectionNewCaixa = em.merge(oldCdTipoCaixaOfCaixaCollectionNewCaixa);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tipocaixa.getCdTipoCaixa();
                if (findTipocaixa(id) == null) {
                    throw new NonexistentEntityException("The tipocaixa with id " + id + " no longer exists.");
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
            Tipocaixa tipocaixa;
            try {
                tipocaixa = em.getReference(Tipocaixa.class, id);
                tipocaixa.getCdTipoCaixa();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipocaixa with id " + id + " no longer exists.", enfe);
            }
            Collection<Caixa> caixaCollection = tipocaixa.getCaixaCollection();
            for (Caixa caixaCollectionCaixa : caixaCollection) {
                caixaCollectionCaixa.setCdTipoCaixa(null);
                caixaCollectionCaixa = em.merge(caixaCollectionCaixa);
            }
            em.remove(tipocaixa);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tipocaixa> findTipocaixaEntities() {
        return findTipocaixaEntities(true, -1, -1);
    }

    public List<Tipocaixa> findTipocaixaEntities(int maxResults, int firstResult) {
        return findTipocaixaEntities(false, maxResults, firstResult);
    }

    private List<Tipocaixa> findTipocaixaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Tipocaixa as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Tipocaixa findTipocaixa(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tipocaixa.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipocaixaCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Tipocaixa as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
