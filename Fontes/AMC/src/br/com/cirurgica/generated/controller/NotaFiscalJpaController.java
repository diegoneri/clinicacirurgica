/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cirurgica.generated.controller;

import br.com.cirurgica.generated.controller.exceptions.NonexistentEntityException;
import br.com.cirurgica.generated.model.NotaFiscal;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import br.com.cirurgica.generated.model.TipoNotaFiscal;
import br.com.cirurgica.generated.model.Venda;
import java.util.ArrayList;
import java.util.Collection;
import br.com.cirurgica.model.Pedido;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

/**
 *
 * @author dfelix3
 */
public class NotaFiscalJpaController implements Serializable {

    public NotaFiscalJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(NotaFiscal notaFiscal) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoNotaFiscal cdTipoNotaFiscal = notaFiscal.getCdTipoNotaFiscal();
            if (cdTipoNotaFiscal != null) {
                cdTipoNotaFiscal = em.getReference(cdTipoNotaFiscal.getClass(), cdTipoNotaFiscal.getCdTipoNotaFiscal());
                notaFiscal.setCdTipoNotaFiscal(cdTipoNotaFiscal);
            }
            em.persist(notaFiscal);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void create(NotaFiscal notaFiscal, EntityManager em) {
        try {
            TipoNotaFiscal cdTipoNotaFiscal = notaFiscal.getCdTipoNotaFiscal();
            if (cdTipoNotaFiscal != null) {
                cdTipoNotaFiscal = em.getReference(cdTipoNotaFiscal.getClass(), cdTipoNotaFiscal.getCdTipoNotaFiscal());
                notaFiscal.setCdTipoNotaFiscal(cdTipoNotaFiscal);
            }
            em.persist(notaFiscal);
        } finally {

        }
    }    
    
    public void edit(NotaFiscal notaFiscal) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            NotaFiscal persistentNotaFiscal = em.find(NotaFiscal.class, notaFiscal.getCdNotaFiscal());
            TipoNotaFiscal cdTipoNotaFiscalOld = persistentNotaFiscal.getCdTipoNotaFiscal();
            TipoNotaFiscal cdTipoNotaFiscalNew = notaFiscal.getCdTipoNotaFiscal();
            Collection<Venda> vendaCollectionOld = persistentNotaFiscal.getVendaCollection();
            Collection<Venda> vendaCollectionNew = notaFiscal.getVendaCollection();
            Collection<Pedido> pedidoCollectionOld = persistentNotaFiscal.getPedidoCollection();
            Collection<Pedido> pedidoCollectionNew = notaFiscal.getPedidoCollection();
            if (cdTipoNotaFiscalNew != null) {
                cdTipoNotaFiscalNew = em.getReference(cdTipoNotaFiscalNew.getClass(), cdTipoNotaFiscalNew.getCdTipoNotaFiscal());
                notaFiscal.setCdTipoNotaFiscal(cdTipoNotaFiscalNew);
            }
            Collection<Venda> attachedVendaCollectionNew = new ArrayList<Venda>();
            for (Venda vendaCollectionNewVendaToAttach : vendaCollectionNew) {
                vendaCollectionNewVendaToAttach = em.getReference(vendaCollectionNewVendaToAttach.getClass(), vendaCollectionNewVendaToAttach.getCdVenda());
                attachedVendaCollectionNew.add(vendaCollectionNewVendaToAttach);
            }
            vendaCollectionNew = attachedVendaCollectionNew;
            notaFiscal.setVendaCollection(vendaCollectionNew);
            Collection<Pedido> attachedPedidoCollectionNew = new ArrayList<Pedido>();
            for (Pedido pedidoCollectionNewPedidoToAttach : pedidoCollectionNew) {
                pedidoCollectionNewPedidoToAttach = em.getReference(pedidoCollectionNewPedidoToAttach.getClass(), pedidoCollectionNewPedidoToAttach.getCdPedido());
                attachedPedidoCollectionNew.add(pedidoCollectionNewPedidoToAttach);
            }
            pedidoCollectionNew = attachedPedidoCollectionNew;
            notaFiscal.setPedidoCollection(pedidoCollectionNew);
            notaFiscal = em.merge(notaFiscal);
            if (cdTipoNotaFiscalOld != null && !cdTipoNotaFiscalOld.equals(cdTipoNotaFiscalNew)) {
                cdTipoNotaFiscalOld.getNotaFiscalCollection().remove(notaFiscal);
                cdTipoNotaFiscalOld = em.merge(cdTipoNotaFiscalOld);
            }
            if (cdTipoNotaFiscalNew != null && !cdTipoNotaFiscalNew.equals(cdTipoNotaFiscalOld)) {
                cdTipoNotaFiscalNew.getNotaFiscalCollection().add(notaFiscal);
                cdTipoNotaFiscalNew = em.merge(cdTipoNotaFiscalNew);
            }
            for (Venda vendaCollectionOldVenda : vendaCollectionOld) {
                if (!vendaCollectionNew.contains(vendaCollectionOldVenda)) {
                    vendaCollectionOldVenda.setCdNotaFiscal(null);
                    vendaCollectionOldVenda = em.merge(vendaCollectionOldVenda);
                }
            }
            for (Venda vendaCollectionNewVenda : vendaCollectionNew) {
                if (!vendaCollectionOld.contains(vendaCollectionNewVenda)) {
                    NotaFiscal oldCdNotaFiscalOfVendaCollectionNewVenda = vendaCollectionNewVenda.getCdNotaFiscal();
                    vendaCollectionNewVenda.setCdNotaFiscal(notaFiscal);
                    vendaCollectionNewVenda = em.merge(vendaCollectionNewVenda);
                    if (oldCdNotaFiscalOfVendaCollectionNewVenda != null && !oldCdNotaFiscalOfVendaCollectionNewVenda.equals(notaFiscal)) {
                        oldCdNotaFiscalOfVendaCollectionNewVenda.getVendaCollection().remove(vendaCollectionNewVenda);
                        oldCdNotaFiscalOfVendaCollectionNewVenda = em.merge(oldCdNotaFiscalOfVendaCollectionNewVenda);
                    }
                }
            }
            for (Pedido pedidoCollectionOldPedido : pedidoCollectionOld) {
                if (!pedidoCollectionNew.contains(pedidoCollectionOldPedido)) {
                    pedidoCollectionOldPedido.setCdNotaFiscal(null);
                    pedidoCollectionOldPedido = em.merge(pedidoCollectionOldPedido);
                }
            }
            for (Pedido pedidoCollectionNewPedido : pedidoCollectionNew) {
                if (!pedidoCollectionOld.contains(pedidoCollectionNewPedido)) {
                    NotaFiscal oldCdNotaFiscalOfPedidoCollectionNewPedido = pedidoCollectionNewPedido.getCdNotaFiscal();
                    pedidoCollectionNewPedido.setCdNotaFiscal(notaFiscal);
                    pedidoCollectionNewPedido = em.merge(pedidoCollectionNewPedido);
                    if (oldCdNotaFiscalOfPedidoCollectionNewPedido != null && !oldCdNotaFiscalOfPedidoCollectionNewPedido.equals(notaFiscal)) {
                        oldCdNotaFiscalOfPedidoCollectionNewPedido.getPedidoCollection().remove(pedidoCollectionNewPedido);
                        oldCdNotaFiscalOfPedidoCollectionNewPedido = em.merge(oldCdNotaFiscalOfPedidoCollectionNewPedido);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = notaFiscal.getCdNotaFiscal();
                if (findNotaFiscal(id) == null) {
                    throw new NonexistentEntityException("The notaFiscal with id " + id + " no longer exists.");
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
            NotaFiscal notaFiscal;
            try {
                notaFiscal = em.getReference(NotaFiscal.class, id);
                notaFiscal.getCdNotaFiscal();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The notaFiscal with id " + id + " no longer exists.", enfe);
            }
            TipoNotaFiscal cdTipoNotaFiscal = notaFiscal.getCdTipoNotaFiscal();
            if (cdTipoNotaFiscal != null) {
                cdTipoNotaFiscal.getNotaFiscalCollection().remove(notaFiscal);
                cdTipoNotaFiscal = em.merge(cdTipoNotaFiscal);
            }
            Collection<Venda> vendaCollection = notaFiscal.getVendaCollection();
            for (Venda vendaCollectionVenda : vendaCollection) {
                vendaCollectionVenda.setCdNotaFiscal(null);
                vendaCollectionVenda = em.merge(vendaCollectionVenda);
            }
            Collection<Pedido> pedidoCollection = notaFiscal.getPedidoCollection();
            for (Pedido pedidoCollectionPedido : pedidoCollection) {
                pedidoCollectionPedido.setCdNotaFiscal(null);
                pedidoCollectionPedido = em.merge(pedidoCollectionPedido);
            }
            em.remove(notaFiscal);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<NotaFiscal> findNotaFiscalEntities() {
        return findNotaFiscalEntities(true, -1, -1);
    }

    public List<NotaFiscal> findNotaFiscalEntities(int maxResults, int firstResult) {
        return findNotaFiscalEntities(false, maxResults, firstResult);
    }

    private List<NotaFiscal> findNotaFiscalEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from NotaFiscal as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public NotaFiscal findNotaFiscal(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(NotaFiscal.class, id);
        } finally {
            em.close();
        }
    }

    public int getNotaFiscalCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from NotaFiscal as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
