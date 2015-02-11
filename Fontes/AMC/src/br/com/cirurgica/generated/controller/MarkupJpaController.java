/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cirurgica.generated.controller;

import br.com.cirurgica.generated.controller.exceptions.NonexistentEntityException;
import br.com.cirurgica.generated.controller.exceptions.PreexistingEntityException;
import br.com.cirurgica.generated.model.Markup;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;

/**
 *
 * @author dfelix3
 */
public class MarkupJpaController implements Serializable {

    public MarkupJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Markup markup) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(markup);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findMarkup(markup.getVlMarkup()) != null) {
                throw new PreexistingEntityException("Markup " + markup + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Markup markup) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            markup = em.merge(markup);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Double id = markup.getVlMarkup();
                if (findMarkup(id) == null) {
                    throw new NonexistentEntityException("The markup with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Double id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Markup markup;
            try {
                markup = em.getReference(Markup.class, id);
                markup.getVlMarkup();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The markup with id " + id + " no longer exists.", enfe);
            }
            em.remove(markup);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Markup> findMarkupEntities() {
        return findMarkupEntities(true, -1, -1);
    }

    public List<Markup> findMarkupEntities(int maxResults, int firstResult) {
        return findMarkupEntities(false, maxResults, firstResult);
    }

    private List<Markup> findMarkupEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Markup as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Markup findMarkup(Double id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Markup.class, id);
        } finally {
            em.close();
        }
    }

    public int getMarkupCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Markup as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
