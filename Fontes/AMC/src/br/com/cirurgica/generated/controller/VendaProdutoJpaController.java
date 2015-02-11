/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cirurgica.generated.controller;

import br.com.cirurgica.generated.controller.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import br.com.cirurgica.generated.model.Venda;
import br.com.cirurgica.model.Produto;
import br.com.cirurgica.generated.model.VendaProduto;
import br.com.cirurgica.generated.model.VendaProdutoPK;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author dfelix3
 */
public class VendaProdutoJpaController implements Serializable {

    public VendaProdutoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(VendaProduto vendaProduto) throws PreexistingEntityException, Exception {
        
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Venda venda = vendaProduto.getVendaProdutoPK().getCdVenda();
            if (venda != null) {
                venda = em.getReference(venda.getClass(), venda.getCdVenda());
                vendaProduto.getVendaProdutoPK().setCdVenda(venda);
            }
            Produto produto = vendaProduto.getVendaProdutoPK().getCdProduto();
            if (produto != null) {
                produto = em.getReference(produto.getClass(), produto.getCdProduto());
                vendaProduto.getVendaProdutoPK().setCdProduto(produto);
            }
            em.persist(vendaProduto);
            if (venda != null) {
                venda.getVendaProdutoCollection().add(vendaProduto);
                venda = em.merge(venda);
            }
            if (produto != null) {
                produto.getVendaProdutoCollection().add(vendaProduto);
                produto = em.merge(produto);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findVendaProduto(vendaProduto.getVendaProdutoPK()) != null) {
                throw new PreexistingEntityException("VendaProduto " + vendaProduto + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

   

    public List<VendaProduto> findVendaProdutoEntities() {
        return findVendaProdutoEntities(true, -1, -1);
    }

    public List<VendaProduto> findVendaProdutoEntities(int maxResults, int firstResult) {
        return findVendaProdutoEntities(false, maxResults, firstResult);
    }

    private List<VendaProduto> findVendaProdutoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from VendaProduto as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public VendaProduto findVendaProduto(VendaProdutoPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(VendaProduto.class, id);
        } finally {
            em.close();
        }
    }

    public int getVendaProdutoCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from VendaProduto as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
