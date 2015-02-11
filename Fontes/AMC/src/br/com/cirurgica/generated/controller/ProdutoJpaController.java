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
import br.com.cirurgica.model.PedidoProduto;
import br.com.cirurgica.model.Produto;
import java.util.ArrayList;
import java.util.Collection;
import br.com.cirurgica.generated.model.VendaProduto;
import br.com.cirurgica.model.Fornecedor;
import br.com.cirurgica.model.FornecedorProduto;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author dfelix3
 */
public class ProdutoJpaController implements Serializable {

    public ProdutoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Produto produto) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();

            em.getTransaction().begin();
            //produto.setCdProduto(null);

            em.persist(produto);
            
            for (FornecedorProduto objToAttach : produto.getFornecedorCollection()) {
                objToAttach.setProduto(produto);
                objToAttach.generatePK();
                em.persist(objToAttach);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            Logger.getLogger(ProdutoJpaController.class.getName()).log(Level.ERROR, null, ex);
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }

    }
    private FornecedorProduto generateFP(Fornecedor fornecedor, Produto produto){
        FornecedorProduto fp = new FornecedorProduto();
        fp.setDataInclusao(new Date());
        fp.setFornecedor(fornecedor);
        fp.setProduto(produto);
        fp.generatePK();
        return fp;
    }
    public void edit(Produto produto) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();

            TypedQuery<FornecedorProduto> tq =
                    em.createNamedQuery("FornecedorProduto.findByCdProduto", FornecedorProduto.class);
            tq.setParameter("cdProduto", produto.getCdProduto());
            List<FornecedorProduto> fornecedoresBD = tq.getResultList();

            List<FornecedorProduto> fornecedoresIncluir =
                    produto.getFornecedorCollection();

            for (FornecedorProduto fp : fornecedoresIncluir) {
                fp.setProduto(produto);
                fp.generatePK();
            }

            for (FornecedorProduto fp : fornecedoresBD) {
                //TODO o indexOf nao estava localizando!!
                if (fornecedoresIncluir.indexOf(fp) == -1) {
                    em.remove(fp);
                }
            }


            produto = em.merge(produto);

            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = produto.getCdProduto();
                if (findProduto(id) == null) {
                    throw new NonexistentEntityException("O produto de código " + id + " não existe.");
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
            Produto produto;
            try {
                produto = em.getReference(Produto.class, id);
                produto.getCdProduto();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The produto with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<PedidoProduto> pedidoProdutoCollectionOrphanCheck = produto.getPedidoProdutoCollection();
            for (PedidoProduto pedidoProdutoCollectionOrphanCheckPedidoProduto : pedidoProdutoCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<>();
                }
                illegalOrphanMessages.add("This Produto (" + produto + ") cannot be destroyed since the PedidoProduto " + pedidoProdutoCollectionOrphanCheckPedidoProduto + " in its pedidoProdutoCollection field has a non-nullable produto field.");
            }
            Collection<VendaProduto> vendaProdutoCollectionOrphanCheck = produto.getVendaProdutoCollection();
            for (VendaProduto vendaProdutoCollectionOrphanCheckVendaProduto : vendaProdutoCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<>();
                }
                illegalOrphanMessages.add("This Produto (" + produto + ") cannot be destroyed since the VendaProduto " + vendaProdutoCollectionOrphanCheckVendaProduto + " in its vendaProdutoCollection field has a non-nullable produto field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }

            em.remove(produto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Produto> findProdutoEntities() {
        return findProdutoEntities(true, -1, -1);
    }

    public List<Produto> findProdutoEntities(int maxResults, int firstResult) {
        return findProdutoEntities(false, maxResults, firstResult);
    }

    private List<Produto> findProdutoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Produto as o ORDER BY o.nome");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Produto findProduto(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Produto.class, id);
        } finally {
            em.close();
        }
    }

    public int getProdutoCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Produto as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
