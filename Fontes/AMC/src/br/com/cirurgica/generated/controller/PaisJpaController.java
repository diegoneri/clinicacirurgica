/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cirurgica.generated.controller;

import br.com.cirurgica.generated.controller.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import br.com.cirurgica.generated.model.Estado;
import br.com.cirurgica.generated.model.Pais;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author dfelix3
 */
public class PaisJpaController implements Serializable {

    public PaisJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Pais pais) {
        if (pais.getEstadoCollection() == null) {
            pais.setEstadoCollection(new ArrayList<Estado>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Estado> attachedEstadoCollection = new ArrayList<Estado>();
            for (Estado estadoCollectionEstadoToAttach : pais.getEstadoCollection()) {
                estadoCollectionEstadoToAttach = em.getReference(estadoCollectionEstadoToAttach.getClass(), estadoCollectionEstadoToAttach.getCdEstado());
                attachedEstadoCollection.add(estadoCollectionEstadoToAttach);
            }
            pais.setEstadoCollection(attachedEstadoCollection);
            em.persist(pais);
            for (Estado estadoCollectionEstado : pais.getEstadoCollection()) {
                Pais oldCdPaisOfEstadoCollectionEstado = estadoCollectionEstado.getCdPais();
                estadoCollectionEstado.setCdPais(pais);
                estadoCollectionEstado = em.merge(estadoCollectionEstado);
                if (oldCdPaisOfEstadoCollectionEstado != null) {
                    oldCdPaisOfEstadoCollectionEstado.getEstadoCollection().remove(estadoCollectionEstado);
                    oldCdPaisOfEstadoCollectionEstado = em.merge(oldCdPaisOfEstadoCollectionEstado);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Pais pais) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pais persistentPais = em.find(Pais.class, pais.getCdPais());
            Collection<Estado> estadoCollectionOld = persistentPais.getEstadoCollection();
            Collection<Estado> estadoCollectionNew = pais.getEstadoCollection();
            Collection<Estado> attachedEstadoCollectionNew = new ArrayList<Estado>();
            for (Estado estadoCollectionNewEstadoToAttach : estadoCollectionNew) {
                estadoCollectionNewEstadoToAttach = em.getReference(estadoCollectionNewEstadoToAttach.getClass(), estadoCollectionNewEstadoToAttach.getCdEstado());
                attachedEstadoCollectionNew.add(estadoCollectionNewEstadoToAttach);
            }
            estadoCollectionNew = attachedEstadoCollectionNew;
            pais.setEstadoCollection(estadoCollectionNew);
            pais = em.merge(pais);
            for (Estado estadoCollectionOldEstado : estadoCollectionOld) {
                if (!estadoCollectionNew.contains(estadoCollectionOldEstado)) {
                    estadoCollectionOldEstado.setCdPais(null);
                    estadoCollectionOldEstado = em.merge(estadoCollectionOldEstado);
                }
            }
            for (Estado estadoCollectionNewEstado : estadoCollectionNew) {
                if (!estadoCollectionOld.contains(estadoCollectionNewEstado)) {
                    Pais oldCdPaisOfEstadoCollectionNewEstado = estadoCollectionNewEstado.getCdPais();
                    estadoCollectionNewEstado.setCdPais(pais);
                    estadoCollectionNewEstado = em.merge(estadoCollectionNewEstado);
                    if (oldCdPaisOfEstadoCollectionNewEstado != null && !oldCdPaisOfEstadoCollectionNewEstado.equals(pais)) {
                        oldCdPaisOfEstadoCollectionNewEstado.getEstadoCollection().remove(estadoCollectionNewEstado);
                        oldCdPaisOfEstadoCollectionNewEstado = em.merge(oldCdPaisOfEstadoCollectionNewEstado);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = pais.getCdPais();
                if (findPais(id) == null) {
                    throw new NonexistentEntityException("The pais with id " + id + " no longer exists.");
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
            Pais pais;
            try {
                pais = em.getReference(Pais.class, id);
                pais.getCdPais();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pais with id " + id + " no longer exists.", enfe);
            }
            Collection<Estado> estadoCollection = pais.getEstadoCollection();
            for (Estado estadoCollectionEstado : estadoCollection) {
                estadoCollectionEstado.setCdPais(null);
                estadoCollectionEstado = em.merge(estadoCollectionEstado);
            }
            em.remove(pais);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Pais> findPaisEntities() {
        return findPaisEntities(true, -1, -1);
    }

    public List<Pais> findPaisEntities(int maxResults, int firstResult) {
        return findPaisEntities(false, maxResults, firstResult);
    }

    private List<Pais> findPaisEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Pais as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Pais findPais(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Pais.class, id);
        } finally {
            em.close();
        }
    }

    public int getPaisCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Pais as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
