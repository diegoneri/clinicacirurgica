/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cirurgica.generated.controller;

import br.com.cirurgica.generated.controller.exceptions.NonexistentEntityException;
import br.com.cirurgica.generated.model.Caixa;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import br.com.cirurgica.generated.model.Tipocaixa;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author dfelix3
 */
public class CaixaJpaController implements Serializable {

    public CaixaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Caixa caixa) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tipocaixa cdTipoCaixa = caixa.getCdTipoCaixa();
            if (cdTipoCaixa != null) {
                cdTipoCaixa = em.getReference(cdTipoCaixa.getClass(), cdTipoCaixa.getCdTipoCaixa());
                caixa.setCdTipoCaixa(cdTipoCaixa);
            }
            em.persist(caixa);
            if (cdTipoCaixa != null) {
                cdTipoCaixa.getCaixaCollection().add(caixa);
                cdTipoCaixa = em.merge(cdTipoCaixa);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Caixa caixa) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Caixa persistentCaixa = em.find(Caixa.class, caixa.getCdCaixa());
            Tipocaixa cdTipoCaixaOld = persistentCaixa.getCdTipoCaixa();
            Tipocaixa cdTipoCaixaNew = caixa.getCdTipoCaixa();
            if (cdTipoCaixaNew != null) {
                cdTipoCaixaNew = em.getReference(cdTipoCaixaNew.getClass(), cdTipoCaixaNew.getCdTipoCaixa());
                caixa.setCdTipoCaixa(cdTipoCaixaNew);
            }
            caixa = em.merge(caixa);
            if (cdTipoCaixaOld != null && !cdTipoCaixaOld.equals(cdTipoCaixaNew)) {
                cdTipoCaixaOld.getCaixaCollection().remove(caixa);
                cdTipoCaixaOld = em.merge(cdTipoCaixaOld);
            }
            if (cdTipoCaixaNew != null && !cdTipoCaixaNew.equals(cdTipoCaixaOld)) {
                cdTipoCaixaNew.getCaixaCollection().add(caixa);
                cdTipoCaixaNew = em.merge(cdTipoCaixaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = caixa.getCdCaixa();
                if (findCaixa(id) == null) {
                    throw new NonexistentEntityException("The caixa with id " + id + " no longer exists.");
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
            Caixa caixa;
            try {
                caixa = em.getReference(Caixa.class, id);
                caixa.getCdCaixa();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The caixa with id " + id + " no longer exists.", enfe);
            }
            Tipocaixa cdTipoCaixa = caixa.getCdTipoCaixa();
            if (cdTipoCaixa != null) {
                cdTipoCaixa.getCaixaCollection().remove(caixa);
                cdTipoCaixa = em.merge(cdTipoCaixa);
            }
            em.remove(caixa);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Caixa> findCaixaEntities() {
        return findCaixaEntities(true, -1, -1);
    }

    public List<Caixa> findCaixaEntities(int maxResults, int firstResult) {
        return findCaixaEntities(false, maxResults, firstResult);
    }

    private List<Caixa> findCaixaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Caixa as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Caixa findCaixa(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Caixa.class, id);
        } finally {
            em.close();
        }
    }

    public int getCaixaCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Caixa as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
