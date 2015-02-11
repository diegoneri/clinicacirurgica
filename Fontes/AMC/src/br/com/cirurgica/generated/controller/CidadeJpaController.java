/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cirurgica.generated.controller;

import br.com.cirurgica.generated.controller.exceptions.NonexistentEntityException;
import br.com.cirurgica.generated.model.Cidade;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import br.com.cirurgica.generated.model.Estado;
import br.com.cirurgica.model.Fornecedor;
import java.util.ArrayList;
import java.util.Collection;
import br.com.cirurgica.model.Pessoa;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author dfelix3
 */
public class CidadeJpaController implements Serializable {

    public CidadeJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cidade cidade) {
        if (cidade.getFornecedorCollection() == null) {
            cidade.setFornecedorCollection(new ArrayList<Fornecedor>());
        }
        if (cidade.getPessoaCollection() == null) {
            cidade.setPessoaCollection(new ArrayList<Pessoa>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estado estado = cidade.getEstado();
            if (estado != null) {
                estado = em.getReference(estado.getClass(), estado.getCdEstado());
                cidade.setEstado(estado);
            }
            Collection<Fornecedor> attachedFornecedorCollection = new ArrayList<Fornecedor>();
            for (Fornecedor fornecedorCollectionFornecedorToAttach : cidade.getFornecedorCollection()) {
                fornecedorCollectionFornecedorToAttach = em.getReference(fornecedorCollectionFornecedorToAttach.getClass(), fornecedorCollectionFornecedorToAttach.getCdFornecedor());
                attachedFornecedorCollection.add(fornecedorCollectionFornecedorToAttach);
            }
            cidade.setFornecedorCollection(attachedFornecedorCollection);
            Collection<Pessoa> attachedPessoaCollection = new ArrayList<Pessoa>();
            for (Pessoa pessoaCollectionPessoaToAttach : cidade.getPessoaCollection()) {
                pessoaCollectionPessoaToAttach = em.getReference(pessoaCollectionPessoaToAttach.getClass(), pessoaCollectionPessoaToAttach.getCdPessoa());
                attachedPessoaCollection.add(pessoaCollectionPessoaToAttach);
            }
            cidade.setPessoaCollection(attachedPessoaCollection);
            em.persist(cidade);
            if (estado != null) {
                estado.getCidadeCollection().add(cidade);
                estado = em.merge(estado);
            }
            for (Fornecedor fornecedorCollectionFornecedor : cidade.getFornecedorCollection()) {
                Cidade oldCdCidadeOfFornecedorCollectionFornecedor = fornecedorCollectionFornecedor.getCdCidade();
                fornecedorCollectionFornecedor.setCdCidade(cidade);
                fornecedorCollectionFornecedor = em.merge(fornecedorCollectionFornecedor);
                if (oldCdCidadeOfFornecedorCollectionFornecedor != null) {
                    oldCdCidadeOfFornecedorCollectionFornecedor.getFornecedorCollection().remove(fornecedorCollectionFornecedor);
                    oldCdCidadeOfFornecedorCollectionFornecedor = em.merge(oldCdCidadeOfFornecedorCollectionFornecedor);
                }
            }
            for (Pessoa pessoaCollectionPessoa : cidade.getPessoaCollection()) {
                Cidade oldCdCidadeOfPessoaCollectionPessoa = pessoaCollectionPessoa.getCdCidade();
                pessoaCollectionPessoa.setCdCidade(cidade);
                pessoaCollectionPessoa = em.merge(pessoaCollectionPessoa);
                if (oldCdCidadeOfPessoaCollectionPessoa != null) {
                    oldCdCidadeOfPessoaCollectionPessoa.getPessoaCollection().remove(pessoaCollectionPessoa);
                    oldCdCidadeOfPessoaCollectionPessoa = em.merge(oldCdCidadeOfPessoaCollectionPessoa);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cidade cidade) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cidade persistentCidade = em.find(Cidade.class, cidade.getCdCidade());
            Estado estadoOld = persistentCidade.getEstado();
            Estado estadoNew = cidade.getEstado();
            Collection<Fornecedor> fornecedorCollectionOld = persistentCidade.getFornecedorCollection();
            Collection<Fornecedor> fornecedorCollectionNew = cidade.getFornecedorCollection();
            Collection<Pessoa> pessoaCollectionOld = persistentCidade.getPessoaCollection();
            Collection<Pessoa> pessoaCollectionNew = cidade.getPessoaCollection();
            if (estadoNew != null) {
                estadoNew = em.getReference(estadoNew.getClass(), estadoNew.getCdEstado());
                cidade.setEstado(estadoNew);
            }
            Collection<Fornecedor> attachedFornecedorCollectionNew = new ArrayList<Fornecedor>();
            for (Fornecedor fornecedorCollectionNewFornecedorToAttach : fornecedorCollectionNew) {
                fornecedorCollectionNewFornecedorToAttach = em.getReference(fornecedorCollectionNewFornecedorToAttach.getClass(), fornecedorCollectionNewFornecedorToAttach.getCdFornecedor());
                attachedFornecedorCollectionNew.add(fornecedorCollectionNewFornecedorToAttach);
            }
            fornecedorCollectionNew = attachedFornecedorCollectionNew;
            cidade.setFornecedorCollection(fornecedorCollectionNew);
            Collection<Pessoa> attachedPessoaCollectionNew = new ArrayList<Pessoa>();
            for (Pessoa pessoaCollectionNewPessoaToAttach : pessoaCollectionNew) {
                pessoaCollectionNewPessoaToAttach = em.getReference(pessoaCollectionNewPessoaToAttach.getClass(), pessoaCollectionNewPessoaToAttach.getCdPessoa());
                attachedPessoaCollectionNew.add(pessoaCollectionNewPessoaToAttach);
            }
            pessoaCollectionNew = attachedPessoaCollectionNew;
            cidade.setPessoaCollection(pessoaCollectionNew);
            cidade = em.merge(cidade);
            if (estadoOld != null && !estadoOld.equals(estadoNew)) {
                estadoOld.getCidadeCollection().remove(cidade);
                estadoOld = em.merge(estadoOld);
            }
            if (estadoNew != null && !estadoNew.equals(estadoOld)) {
                estadoNew.getCidadeCollection().add(cidade);
                estadoNew = em.merge(estadoNew);
            }
            for (Fornecedor fornecedorCollectionOldFornecedor : fornecedorCollectionOld) {
                if (!fornecedorCollectionNew.contains(fornecedorCollectionOldFornecedor)) {
                    fornecedorCollectionOldFornecedor.setCdCidade(null);
                    fornecedorCollectionOldFornecedor = em.merge(fornecedorCollectionOldFornecedor);
                }
            }
            for (Fornecedor fornecedorCollectionNewFornecedor : fornecedorCollectionNew) {
                if (!fornecedorCollectionOld.contains(fornecedorCollectionNewFornecedor)) {
                    Cidade oldCdCidadeOfFornecedorCollectionNewFornecedor = fornecedorCollectionNewFornecedor.getCdCidade();
                    fornecedorCollectionNewFornecedor.setCdCidade(cidade);
                    fornecedorCollectionNewFornecedor = em.merge(fornecedorCollectionNewFornecedor);
                    if (oldCdCidadeOfFornecedorCollectionNewFornecedor != null && !oldCdCidadeOfFornecedorCollectionNewFornecedor.equals(cidade)) {
                        oldCdCidadeOfFornecedorCollectionNewFornecedor.getFornecedorCollection().remove(fornecedorCollectionNewFornecedor);
                        oldCdCidadeOfFornecedorCollectionNewFornecedor = em.merge(oldCdCidadeOfFornecedorCollectionNewFornecedor);
                    }
                }
            }
            for (Pessoa pessoaCollectionOldPessoa : pessoaCollectionOld) {
                if (!pessoaCollectionNew.contains(pessoaCollectionOldPessoa)) {
                    pessoaCollectionOldPessoa.setCdCidade(null);
                    pessoaCollectionOldPessoa = em.merge(pessoaCollectionOldPessoa);
                }
            }
            for (Pessoa pessoaCollectionNewPessoa : pessoaCollectionNew) {
                if (!pessoaCollectionOld.contains(pessoaCollectionNewPessoa)) {
                    Cidade oldCdCidadeOfPessoaCollectionNewPessoa = pessoaCollectionNewPessoa.getCdCidade();
                    pessoaCollectionNewPessoa.setCdCidade(cidade);
                    pessoaCollectionNewPessoa = em.merge(pessoaCollectionNewPessoa);
                    if (oldCdCidadeOfPessoaCollectionNewPessoa != null && !oldCdCidadeOfPessoaCollectionNewPessoa.equals(cidade)) {
                        oldCdCidadeOfPessoaCollectionNewPessoa.getPessoaCollection().remove(pessoaCollectionNewPessoa);
                        oldCdCidadeOfPessoaCollectionNewPessoa = em.merge(oldCdCidadeOfPessoaCollectionNewPessoa);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = cidade.getCdCidade();
                if (findCidade(id) == null) {
                    throw new NonexistentEntityException("The cidade with id " + id + " no longer exists.");
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
            Cidade cidade;
            try {
                cidade = em.getReference(Cidade.class, id);
                cidade.getCdCidade();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cidade with id " + id + " no longer exists.", enfe);
            }
            Estado estado = cidade.getEstado();
            if (estado != null) {
                estado.getCidadeCollection().remove(cidade);
                estado = em.merge(estado);
            }
            Collection<Fornecedor> fornecedorCollection = cidade.getFornecedorCollection();
            for (Fornecedor fornecedorCollectionFornecedor : fornecedorCollection) {
                fornecedorCollectionFornecedor.setCdCidade(null);
                fornecedorCollectionFornecedor = em.merge(fornecedorCollectionFornecedor);
            }
            Collection<Pessoa> pessoaCollection = cidade.getPessoaCollection();
            for (Pessoa pessoaCollectionPessoa : pessoaCollection) {
                pessoaCollectionPessoa.setCdCidade(null);
                pessoaCollectionPessoa = em.merge(pessoaCollectionPessoa);
            }
            em.remove(cidade);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cidade> findCidadeEntities() {
        return findCidadeEntities(true, -1, -1);
    }

    public List<Cidade> findCidadeEntities(int maxResults, int firstResult) {
        return findCidadeEntities(false, maxResults, firstResult);
    }

    private List<Cidade> findCidadeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Cidade as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Cidade findCidade(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cidade.class, id);
        } finally {
            em.close();
        }
    }

    public int getCidadeCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Cidade as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
