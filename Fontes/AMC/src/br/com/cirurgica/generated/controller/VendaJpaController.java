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
import br.com.cirurgica.generated.model.FormaPagamento;
import br.com.cirurgica.generated.model.Cliente;
import br.com.cirurgica.generated.model.NotaFiscal;
import br.com.cirurgica.generated.model.TipoNotaFiscal;
import br.com.cirurgica.generated.model.Venda;
import br.com.cirurgica.generated.model.VendaProduto;
import br.com.cirurgica.model.Produto;
import br.com.cirurgica.model.Usuario;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author dfelix3
 */
public class VendaJpaController implements Serializable {

    public VendaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Venda venda) throws Exception {
        if (venda.getVendaProdutoCollection() == null || venda.getVendaProdutoCollection().size() <= 0) {
            throw new Exception("A venda deve conter ao menos um item!");
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            FormaPagamento cdFormaPagamento = venda.getCdFormaPagamento();
            if (cdFormaPagamento != null) {
                cdFormaPagamento = em.getReference(cdFormaPagamento.getClass(), cdFormaPagamento.getCdFormaPagamento());
                venda.setCdFormaPagamento(cdFormaPagamento);
            }
            Cliente cdCliente = venda.getCdCliente();
            if (cdCliente != null) {
                cdCliente = em.getReference(cdCliente.getClass(), cdCliente.getCdCliente());
                venda.setCdCliente(cdCliente);
            }
            NotaFiscal cdNotaFiscal = venda.getCdNotaFiscal();

            if (cdNotaFiscal == null) {
                cdNotaFiscal = new NotaFiscal(-1);
            }
            cdNotaFiscal.setCdTipoNotaFiscal(new TipoNotaFiscal(2));
            if (cdNotaFiscal.getCdNotaFiscal() == null && !cdNotaFiscal.getNumeroNotaFiscal().trim().equals("")) {
                new NotaFiscalJpaController(this.emf).create(cdNotaFiscal, em);
            } else {
                cdNotaFiscal = null;
            }
            venda.setCdNotaFiscal(cdNotaFiscal);

            Usuario cdUsuario = venda.getCdUsuario();
            if (cdUsuario != null) {
                cdUsuario = em.getReference(cdUsuario.getClass(), cdUsuario.getCdUsuario());
                venda.setCdUsuario(cdUsuario);
            }

            em.persist(venda);

//            for (VendaProduto vendaProdutoInsert : venda.getVendaProdutoCollection()) {
//                this.updateProduto(vendaProdutoInsert, em, true);
//            }

            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    private void updateProduto(VendaProduto vendaProduto, EntityManager em, Boolean include) {
        try {
            Produto produto = vendaProduto.getVendaProdutoPK().getCdProduto();
            if (produto != null) {
                produto.getVendaProdutoCollection().add(vendaProduto);
                Integer novoEstoque = (include ? produto.getQtdeEstoque() - vendaProduto.getQtdeProduto() : produto.getQtdeEstoque() + vendaProduto.getQtdeProduto());
                produto.setQtdeEstoque(novoEstoque);
                produto = em.merge(produto);
            }

        } catch (Exception ex) {
            throw ex;
        }
    }

    public void edit(Venda venda) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Venda persistentVenda = em.find(Venda.class, venda.getCdVenda());

            NotaFiscal cdNotaFiscalNew = venda.getCdNotaFiscal();

            List<VendaProduto> vendaProdutoCollectionOld = persistentVenda.getVendaProdutoCollection();
            List<VendaProduto> vendaProdutoCollectionNew = venda.getVendaProdutoCollection();

            List<VendaProduto> vendaProdutoToBeRemoved = new ArrayList<>();

            for (VendaProduto vendaProdutoCollectionOldVendaProduto : vendaProdutoCollectionOld) {
                if (!vendaProdutoCollectionNew.contains(vendaProdutoCollectionOldVendaProduto)) {
                    vendaProdutoToBeRemoved.add(vendaProdutoCollectionOldVendaProduto);
                }
            }

            List<VendaProduto> vendaProdutoToBeAdded = new ArrayList<>();

            for (VendaProduto vendaProdutoCollectionNewVendaProdutoToAttach : vendaProdutoCollectionNew) {
                if (!vendaProdutoToBeRemoved.contains(vendaProdutoCollectionNewVendaProdutoToAttach)) {
                    vendaProdutoToBeAdded.add(vendaProdutoCollectionNewVendaProdutoToAttach);
                }
            }
            vendaProdutoCollectionNew = vendaProdutoToBeAdded;
            venda.setVendaProdutoCollection(vendaProdutoCollectionNew);
            venda = em.merge(venda);

            if (cdNotaFiscalNew != null) {
                cdNotaFiscalNew = em.merge(cdNotaFiscalNew);
            }
            /*
             for (VendaProduto vendaProdutoCollectionNewVendaProduto : vendaProdutoCollectionNew) {
             if (!vendaProdutoCollectionOld.contains(vendaProdutoCollectionNewVendaProduto)) {
             Venda oldVendaOfVendaProdutoCollectionNewVendaProduto = vendaProdutoCollectionNewVendaProduto.getVendaProdutoPK().getCdVenda();
             vendaProdutoCollectionNewVendaProduto.getVendaProdutoPK().setCdVenda(venda);
             vendaProdutoCollectionNewVendaProduto = em.merge(vendaProdutoCollectionNewVendaProduto);
             if (oldVendaOfVendaProdutoCollectionNewVendaProduto != null && !oldVendaOfVendaProdutoCollectionNewVendaProduto.equals(venda)) {
             oldVendaOfVendaProdutoCollectionNewVendaProduto.getVendaProdutoCollection().remove(vendaProdutoCollectionNewVendaProduto);
             oldVendaOfVendaProdutoCollectionNewVendaProduto = em.merge(oldVendaOfVendaProdutoCollectionNewVendaProduto);
             }
             }
             }*/
            for (VendaProduto vp : vendaProdutoToBeRemoved) {
                em.remove(vp);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = venda.getCdVenda();
                if (findVenda(id) == null) {
                    throw new NonexistentEntityException("The venda with id " + id + " no longer exists.");
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
            Venda venda;
            try {
                venda = em.getReference(Venda.class, id);
                venda.getCdVenda();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("A venda com o id " + id + " não existe.", enfe);
            }

            FormaPagamento cdFormaPagamento = venda.getCdFormaPagamento();
            if (cdFormaPagamento != null) {
                cdFormaPagamento.getVendaCollection().remove(venda);
                cdFormaPagamento = em.merge(cdFormaPagamento);
            }
            Cliente cdCliente = venda.getCdCliente();
            if (cdCliente != null) {
                cdCliente.getVendaCollection().remove(venda);
                cdCliente = em.merge(cdCliente);
            }
            //Exclui a NF da venda
            NotaFiscal cdNotaFiscal = venda.getCdNotaFiscal();
            if (cdNotaFiscal != null) {
                cdNotaFiscal.getVendaCollection().remove(venda);
                em.remove(cdNotaFiscal);
            }

            Usuario cdUsuario = venda.getCdUsuario();
            if (cdUsuario != null) {
                cdUsuario.getVendaCollection().remove(venda);
                cdUsuario = em.merge(cdUsuario);
            }

            em.remove(venda);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Venda> findVendaEntities() {
        return findVendaEntities(true, -1, -1);
    }

    public List<Venda> findVendaEntities(int maxResults, int firstResult) {
        return findVendaEntities(false, maxResults, firstResult);
    }

    private List<Venda> findVendaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Venda as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Venda findVenda(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Venda.class, id);
        } finally {
            em.close();
        }
    }

    public int getVendaCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Venda as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
