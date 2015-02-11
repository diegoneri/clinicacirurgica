/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cirurgica.generated.controller;

import br.com.cirurgica.generated.controller.exceptions.IllegalOrphanException;
import br.com.cirurgica.generated.controller.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import br.com.cirurgica.generated.model.NotaFiscal;
import br.com.cirurgica.generated.model.TipoNotaFiscal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author dfelix3
 */
public class TipoNotaFiscalJpaController implements Serializable {

    public TipoNotaFiscalJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoNotaFiscal tipoNotaFiscal) {
        if (tipoNotaFiscal.getNotaFiscalCollection() == null) {
            tipoNotaFiscal.setNotaFiscalCollection(new ArrayList<NotaFiscal>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<NotaFiscal> attachedNotaFiscalCollection = new ArrayList<NotaFiscal>();
            for (NotaFiscal notaFiscalCollectionNotaFiscalToAttach : tipoNotaFiscal.getNotaFiscalCollection()) {
                notaFiscalCollectionNotaFiscalToAttach = em.getReference(notaFiscalCollectionNotaFiscalToAttach.getClass(), notaFiscalCollectionNotaFiscalToAttach.getCdNotaFiscal());
                attachedNotaFiscalCollection.add(notaFiscalCollectionNotaFiscalToAttach);
            }
            tipoNotaFiscal.setNotaFiscalCollection(attachedNotaFiscalCollection);
            em.persist(tipoNotaFiscal);
            for (NotaFiscal notaFiscalCollectionNotaFiscal : tipoNotaFiscal.getNotaFiscalCollection()) {
                TipoNotaFiscal oldCdTipoNotaFiscalOfNotaFiscalCollectionNotaFiscal = notaFiscalCollectionNotaFiscal.getCdTipoNotaFiscal();
                notaFiscalCollectionNotaFiscal.setCdTipoNotaFiscal(tipoNotaFiscal);
                notaFiscalCollectionNotaFiscal = em.merge(notaFiscalCollectionNotaFiscal);
                if (oldCdTipoNotaFiscalOfNotaFiscalCollectionNotaFiscal != null) {
                    oldCdTipoNotaFiscalOfNotaFiscalCollectionNotaFiscal.getNotaFiscalCollection().remove(notaFiscalCollectionNotaFiscal);
                    oldCdTipoNotaFiscalOfNotaFiscalCollectionNotaFiscal = em.merge(oldCdTipoNotaFiscalOfNotaFiscalCollectionNotaFiscal);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipoNotaFiscal tipoNotaFiscal) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoNotaFiscal persistentTipoNotaFiscal = em.find(TipoNotaFiscal.class, tipoNotaFiscal.getCdTipoNotaFiscal());
            Collection<NotaFiscal> notaFiscalCollectionOld = persistentTipoNotaFiscal.getNotaFiscalCollection();
            Collection<NotaFiscal> notaFiscalCollectionNew = tipoNotaFiscal.getNotaFiscalCollection();
            List<String> illegalOrphanMessages = null;
            for (NotaFiscal notaFiscalCollectionOldNotaFiscal : notaFiscalCollectionOld) {
                if (!notaFiscalCollectionNew.contains(notaFiscalCollectionOldNotaFiscal)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain NotaFiscal " + notaFiscalCollectionOldNotaFiscal + " since its cdTipoNotaFiscal field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<NotaFiscal> attachedNotaFiscalCollectionNew = new ArrayList<NotaFiscal>();
            for (NotaFiscal notaFiscalCollectionNewNotaFiscalToAttach : notaFiscalCollectionNew) {
                notaFiscalCollectionNewNotaFiscalToAttach = em.getReference(notaFiscalCollectionNewNotaFiscalToAttach.getClass(), notaFiscalCollectionNewNotaFiscalToAttach.getCdNotaFiscal());
                attachedNotaFiscalCollectionNew.add(notaFiscalCollectionNewNotaFiscalToAttach);
            }
            notaFiscalCollectionNew = attachedNotaFiscalCollectionNew;
            tipoNotaFiscal.setNotaFiscalCollection(notaFiscalCollectionNew);
            tipoNotaFiscal = em.merge(tipoNotaFiscal);
            for (NotaFiscal notaFiscalCollectionNewNotaFiscal : notaFiscalCollectionNew) {
                if (!notaFiscalCollectionOld.contains(notaFiscalCollectionNewNotaFiscal)) {
                    TipoNotaFiscal oldCdTipoNotaFiscalOfNotaFiscalCollectionNewNotaFiscal = notaFiscalCollectionNewNotaFiscal.getCdTipoNotaFiscal();
                    notaFiscalCollectionNewNotaFiscal.setCdTipoNotaFiscal(tipoNotaFiscal);
                    notaFiscalCollectionNewNotaFiscal = em.merge(notaFiscalCollectionNewNotaFiscal);
                    if (oldCdTipoNotaFiscalOfNotaFiscalCollectionNewNotaFiscal != null && !oldCdTipoNotaFiscalOfNotaFiscalCollectionNewNotaFiscal.equals(tipoNotaFiscal)) {
                        oldCdTipoNotaFiscalOfNotaFiscalCollectionNewNotaFiscal.getNotaFiscalCollection().remove(notaFiscalCollectionNewNotaFiscal);
                        oldCdTipoNotaFiscalOfNotaFiscalCollectionNewNotaFiscal = em.merge(oldCdTipoNotaFiscalOfNotaFiscalCollectionNewNotaFiscal);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tipoNotaFiscal.getCdTipoNotaFiscal();
                if (findTipoNotaFiscal(id) == null) {
                    throw new NonexistentEntityException("The tipoNotaFiscal with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoNotaFiscal tipoNotaFiscal;
            try {
                tipoNotaFiscal = em.getReference(TipoNotaFiscal.class, id);
                tipoNotaFiscal.getCdTipoNotaFiscal();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoNotaFiscal with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<NotaFiscal> notaFiscalCollectionOrphanCheck = tipoNotaFiscal.getNotaFiscalCollection();
            for (NotaFiscal notaFiscalCollectionOrphanCheckNotaFiscal : notaFiscalCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This TipoNotaFiscal (" + tipoNotaFiscal + ") cannot be destroyed since the NotaFiscal " + notaFiscalCollectionOrphanCheckNotaFiscal + " in its notaFiscalCollection field has a non-nullable cdTipoNotaFiscal field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(tipoNotaFiscal);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipoNotaFiscal> findTipoNotaFiscalEntities() {
        return findTipoNotaFiscalEntities(true, -1, -1);
    }

    public List<TipoNotaFiscal> findTipoNotaFiscalEntities(int maxResults, int firstResult) {
        return findTipoNotaFiscalEntities(false, maxResults, firstResult);
    }

    private List<TipoNotaFiscal> findTipoNotaFiscalEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from TipoNotaFiscal as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public TipoNotaFiscal findTipoNotaFiscal(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoNotaFiscal.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoNotaFiscalCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from TipoNotaFiscal as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
