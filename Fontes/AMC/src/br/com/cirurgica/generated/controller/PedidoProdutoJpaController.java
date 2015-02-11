/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cirurgica.generated.controller;

import br.com.cirurgica.generated.controller.exceptions.NonexistentEntityException;
import br.com.cirurgica.generated.controller.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import br.com.cirurgica.model.Produto;
import br.com.cirurgica.model.Pedido;
import br.com.cirurgica.model.PedidoProduto;
import br.com.cirurgica.model.PedidoProdutoPK;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author dfelix3
 */
public class PedidoProdutoJpaController implements Serializable {

    public PedidoProdutoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PedidoProduto pedidoProduto, EntityManager em) throws PreexistingEntityException, Exception {
        try {
            em.persist(pedidoProduto);
            Produto produto = pedidoProduto.getCdProduto();
            if (produto != null) {
                produto.getPedidoProdutoCollection().add(pedidoProduto);
                produto.setValorCompra(pedidoProduto.getVlProduto());
                produto.setQtdeEstoque(produto.getQtdeEstoque() + pedidoProduto.getQtdeProduto());
                produto = em.merge(produto);
            }

        } catch (Exception ex) {
            throw ex;
        } 
    }    
    
    public void create(PedidoProduto pedidoProduto) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();

            this.create(pedidoProduto, em);
            
            em.getTransaction().commit();
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PedidoProduto pedidoProduto) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PedidoProduto persistentPedidoProduto = em.find(PedidoProduto.class, pedidoProduto.getCdPedidoproduto());
            Produto produtoOld = persistentPedidoProduto.getCdProduto();
            Produto produtoNew = pedidoProduto.getCdProduto();
            Pedido pedidoOld = persistentPedidoProduto.getCdPedido();
            Pedido pedidoNew = pedidoProduto.getCdPedido();
            if (produtoNew != null) {
                produtoNew = em.getReference(produtoNew.getClass(), produtoNew.getCdProduto());
                pedidoProduto.setCdProduto(produtoNew);
            }
            if (pedidoNew != null) {
                pedidoNew = em.getReference(pedidoNew.getClass(), pedidoNew.getCdPedido());
                pedidoProduto.setCdPedido(pedidoNew);
            }
            pedidoProduto = em.merge(pedidoProduto);
            if (produtoOld != null && !produtoOld.equals(produtoNew)) {
                produtoOld.getPedidoProdutoCollection().remove(pedidoProduto);
                produtoOld = em.merge(produtoOld);
            }
            if (produtoNew != null && !produtoNew.equals(produtoOld)) {
                produtoNew.getPedidoProdutoCollection().add(pedidoProduto);
                produtoNew = em.merge(produtoNew);
            }
            if (pedidoOld != null && !pedidoOld.equals(pedidoNew)) {
                pedidoOld.getPedidoProdutoCollection().remove(pedidoProduto);
                pedidoOld = em.merge(pedidoOld);
            }
            if (pedidoNew != null && !pedidoNew.equals(pedidoOld)) {
                pedidoNew.getPedidoProdutoCollection().add(pedidoProduto);
                pedidoNew = em.merge(pedidoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = pedidoProduto.getCdPedidoproduto();
                if (findPedidoProduto(id) == null) {
                    throw new NonexistentEntityException("The pedidoProduto with id " + id + " no longer exists.");
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
            PedidoProduto pedidoProduto;
            try {
                pedidoProduto = em.getReference(PedidoProduto.class, id);
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pedidoProduto with id " + id + " no longer exists.", enfe);
            }
            Produto produto = pedidoProduto.getCdProduto();
            if (produto != null) {
                produto.getPedidoProdutoCollection().remove(pedidoProduto);
                produto = em.merge(produto);
            }
            Pedido pedido = pedidoProduto.getCdPedido();
            if (pedido != null) {
                pedido.getPedidoProdutoCollection().remove(pedidoProduto);
                pedido = em.merge(pedido);
            }
            em.remove(pedidoProduto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PedidoProduto> findPedidoProdutoEntities() {
        return findPedidoProdutoEntities(true, -1, -1);
    }

    public List<PedidoProduto> findPedidoProdutoEntities(int maxResults, int firstResult) {
        return findPedidoProdutoEntities(false, maxResults, firstResult);
    }

    private List<PedidoProduto> findPedidoProdutoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from PedidoProduto as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public PedidoProduto findPedidoProduto(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PedidoProduto.class, id);
        } finally {
            em.close();
        }
    }

    public int getPedidoProdutoCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from PedidoProduto as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
