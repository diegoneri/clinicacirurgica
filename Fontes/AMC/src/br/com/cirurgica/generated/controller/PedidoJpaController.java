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
import br.com.cirurgica.model.Fornecedor;
import br.com.cirurgica.model.Pedido;
import br.com.cirurgica.model.PedidoProduto;
import br.com.cirurgica.model.Produto;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author dfelix3
 */
public class PedidoJpaController implements Serializable {

    public PedidoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Pedido pedido) {
        if (pedido.getPedidoProdutoCollection() == null || pedido.getPedidoProdutoCollection().size() <= 0) {
            throw new IllegalStateException("O pedido deve conter itens!!");
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            NotaFiscal cdNotaFiscal = pedido.getCdNotaFiscal();
            if (cdNotaFiscal != null) {
                if (cdNotaFiscal.getCdNotaFiscal() == null && !cdNotaFiscal.getNumeroNotaFiscal().trim().equals("")) {
                    new NotaFiscalJpaController(this.emf).create(cdNotaFiscal, em);
                } else {
                    cdNotaFiscal = null;
                }
                pedido.setCdNotaFiscal(cdNotaFiscal);
            }
            Fornecedor cdFornecedor = pedido.getCdFornecedor();

            if (cdFornecedor != null) {
                cdFornecedor = em.getReference(cdFornecedor.getClass(), cdFornecedor.getCdFornecedor());
                pedido.setCdFornecedor(cdFornecedor);
            }

            for (PedidoProduto pedidoProdutoOld : pedido.getPedidoProdutoCollection()) {
                pedidoProdutoOld.setCdPedido(pedido);
            }

            em.persist(pedido);

            PedidoProdutoJpaController jpaC = new PedidoProdutoJpaController(emf);

            for (PedidoProduto pedidoProdutoOld : pedido.getPedidoProdutoCollection()) {
                jpaC.create(pedidoProdutoOld, em);
            }
            
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Pedido pedido) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pedido persistentPedido = em.find(Pedido.class, pedido.getCdPedido());
            NotaFiscal cdNotaFiscalOld = persistentPedido.getCdNotaFiscal();
            NotaFiscal cdNotaFiscalNew = pedido.getCdNotaFiscal();
            if (cdNotaFiscalOld == null && cdNotaFiscalNew != null){
                new NotaFiscalJpaController(this.emf).create(cdNotaFiscalNew, em);
            }
            pedido = em.merge(pedido);
            
            if (cdNotaFiscalOld != null && cdNotaFiscalNew != null && !cdNotaFiscalOld.getNumeroNotaFiscal().equals(cdNotaFiscalNew.getNumeroNotaFiscal())) {
                em.merge(cdNotaFiscalNew);
            }

            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = pedido.getCdPedido();
                if (findPedido(id) == null) {
                    throw new NonexistentEntityException("O pedido número " + id + " não existe.");
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
            Pedido pedido;
            try {
                pedido = em.getReference(Pedido.class, id);
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pedido with id " + id + " no longer exists.", enfe);
            }
            for (PedidoProduto pedidoProduto: pedido.getPedidoProdutoCollection()){
                //Atualizar estoque com o pedido excluído
                Produto produto = pedidoProduto.getCdProduto();
                if (produto != null) {
                    produto.getPedidoProdutoCollection().remove(pedidoProduto);
                    produto.setQtdeEstoque(produto.getQtdeEstoque() - pedidoProduto.getQtdeProduto());
                    produto = em.merge(produto);
                }     
                
                em.remove(pedidoProduto);
            }

            NotaFiscal cdNotaFiscal = pedido.getCdNotaFiscal();
            if (cdNotaFiscal != null) {
                cdNotaFiscal.getPedidoCollection().remove(pedido);
                cdNotaFiscal = em.merge(cdNotaFiscal);
            }
            Fornecedor cdFornecedor = pedido.getCdFornecedor();
            if (cdFornecedor != null) {
                cdFornecedor.getPedidoCollection().remove(pedido);
                cdFornecedor = em.merge(cdFornecedor);
            }
            em.remove(pedido);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Pedido> findPedidoEntities() {
        return findPedidoEntities(true, -1, -1);
    }

    public List<Pedido> findPedidoEntities(int maxResults, int firstResult) {
        return findPedidoEntities(false, maxResults, firstResult);
    }

    private List<Pedido> findPedidoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Pedido as o order by o.dataPedido");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Pedido findPedido(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Pedido.class, id);
        } finally {
            em.close();
        }
    }

    public int getPedidoCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Pedido as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
