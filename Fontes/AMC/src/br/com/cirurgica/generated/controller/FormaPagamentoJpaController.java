/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cirurgica.generated.controller;

import br.com.cirurgica.generated.controller.exceptions.NonexistentEntityException;
import br.com.cirurgica.generated.model.FormaPagamento;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import br.com.cirurgica.generated.model.Venda;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author dfelix3
 */
public class FormaPagamentoJpaController implements Serializable {

    public FormaPagamentoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(FormaPagamento formaPagamento) {
        if (formaPagamento.getVendaCollection() == null) {
            formaPagamento.setVendaCollection(new ArrayList<Venda>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Venda> attachedVendaCollection = new ArrayList<Venda>();
            for (Venda vendaCollectionVendaToAttach : formaPagamento.getVendaCollection()) {
                vendaCollectionVendaToAttach = em.getReference(vendaCollectionVendaToAttach.getClass(), vendaCollectionVendaToAttach.getCdVenda());
                attachedVendaCollection.add(vendaCollectionVendaToAttach);
            }
            formaPagamento.setVendaCollection(attachedVendaCollection);
            em.persist(formaPagamento);
            for (Venda vendaCollectionVenda : formaPagamento.getVendaCollection()) {
                FormaPagamento oldCdFormaPagamentoOfVendaCollectionVenda = vendaCollectionVenda.getCdFormaPagamento();
                vendaCollectionVenda.setCdFormaPagamento(formaPagamento);
                vendaCollectionVenda = em.merge(vendaCollectionVenda);
                if (oldCdFormaPagamentoOfVendaCollectionVenda != null) {
                    oldCdFormaPagamentoOfVendaCollectionVenda.getVendaCollection().remove(vendaCollectionVenda);
                    oldCdFormaPagamentoOfVendaCollectionVenda = em.merge(oldCdFormaPagamentoOfVendaCollectionVenda);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(FormaPagamento formaPagamento) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            FormaPagamento persistentFormaPagamento = em.find(FormaPagamento.class, formaPagamento.getCdFormaPagamento());
            Collection<Venda> vendaCollectionOld = persistentFormaPagamento.getVendaCollection();
            Collection<Venda> vendaCollectionNew = formaPagamento.getVendaCollection();
            Collection<Venda> attachedVendaCollectionNew = new ArrayList<Venda>();
            for (Venda vendaCollectionNewVendaToAttach : vendaCollectionNew) {
                vendaCollectionNewVendaToAttach = em.getReference(vendaCollectionNewVendaToAttach.getClass(), vendaCollectionNewVendaToAttach.getCdVenda());
                attachedVendaCollectionNew.add(vendaCollectionNewVendaToAttach);
            }
            vendaCollectionNew = attachedVendaCollectionNew;
            formaPagamento.setVendaCollection(vendaCollectionNew);
            formaPagamento = em.merge(formaPagamento);
            for (Venda vendaCollectionOldVenda : vendaCollectionOld) {
                if (!vendaCollectionNew.contains(vendaCollectionOldVenda)) {
                    vendaCollectionOldVenda.setCdFormaPagamento(null);
                    vendaCollectionOldVenda = em.merge(vendaCollectionOldVenda);
                }
            }
            for (Venda vendaCollectionNewVenda : vendaCollectionNew) {
                if (!vendaCollectionOld.contains(vendaCollectionNewVenda)) {
                    FormaPagamento oldCdFormaPagamentoOfVendaCollectionNewVenda = vendaCollectionNewVenda.getCdFormaPagamento();
                    vendaCollectionNewVenda.setCdFormaPagamento(formaPagamento);
                    vendaCollectionNewVenda = em.merge(vendaCollectionNewVenda);
                    if (oldCdFormaPagamentoOfVendaCollectionNewVenda != null && !oldCdFormaPagamentoOfVendaCollectionNewVenda.equals(formaPagamento)) {
                        oldCdFormaPagamentoOfVendaCollectionNewVenda.getVendaCollection().remove(vendaCollectionNewVenda);
                        oldCdFormaPagamentoOfVendaCollectionNewVenda = em.merge(oldCdFormaPagamentoOfVendaCollectionNewVenda);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = formaPagamento.getCdFormaPagamento();
                if (findFormaPagamento(id) == null) {
                    throw new NonexistentEntityException("The formaPagamento with id " + id + " no longer exists.");
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
            FormaPagamento formaPagamento;
            try {
                formaPagamento = em.getReference(FormaPagamento.class, id);
                formaPagamento.getCdFormaPagamento();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The formaPagamento with id " + id + " no longer exists.", enfe);
            }
            Collection<Venda> vendaCollection = formaPagamento.getVendaCollection();
            for (Venda vendaCollectionVenda : vendaCollection) {
                vendaCollectionVenda.setCdFormaPagamento(null);
                vendaCollectionVenda = em.merge(vendaCollectionVenda);
            }
            em.remove(formaPagamento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<FormaPagamento> findFormaPagamentoEntities() {
        return findFormaPagamentoEntities(true, -1, -1);
    }

    public List<FormaPagamento> findFormaPagamentoEntities(int maxResults, int firstResult) {
        return findFormaPagamentoEntities(false, maxResults, firstResult);
    }

    private List<FormaPagamento> findFormaPagamentoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from FormaPagamento as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public FormaPagamento findFormaPagamento(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(FormaPagamento.class, id);
        } finally {
            em.close();
        }
    }

    public int getFormaPagamentoCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from FormaPagamento as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
