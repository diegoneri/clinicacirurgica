/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cirurgica.generated.controller;

import br.com.cirurgica.generated.controller.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import br.com.cirurgica.generated.model.Cidade;
import br.com.cirurgica.generated.model.Estadocivil;
import br.com.cirurgica.model.Pessoa;
import br.com.cirurgica.model.Usuario;
import java.beans.PropertyVetoException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author dfelix3
 */
public class PessoaJpaController implements Serializable {

    public PessoaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Pessoa pessoa) {
        if (pessoa.getUsuarioCollection() == null) {
            pessoa.setUsuarioCollection(new ArrayList<Usuario>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cidade cdCidade = pessoa.getCdCidade();
            if (cdCidade != null) {
                cdCidade = em.getReference(cdCidade.getClass(), cdCidade.getCdCidade());
                pessoa.setCdCidade(cdCidade);
            }
            Estadocivil cdEstadoCivil = pessoa.getCdEstadoCivil();
            if (cdEstadoCivil != null) {
                cdEstadoCivil = em.getReference(cdEstadoCivil.getClass(), cdEstadoCivil.getCdEstadoCivil());
                pessoa.setCdEstadoCivil(cdEstadoCivil);
            }
            Collection<Usuario> attachedUsuarioCollection = new ArrayList<Usuario>();
            for (Usuario usuarioCollectionUsuarioToAttach : pessoa.getUsuarioCollection()) {
                usuarioCollectionUsuarioToAttach = em.getReference(usuarioCollectionUsuarioToAttach.getClass(), usuarioCollectionUsuarioToAttach.getCdUsuario());
                attachedUsuarioCollection.add(usuarioCollectionUsuarioToAttach);
            }
            pessoa.setUsuarioCollection(attachedUsuarioCollection);
            em.persist(pessoa);
            if (cdCidade != null) {
                cdCidade.getPessoaCollection().add(pessoa);
                cdCidade = em.merge(cdCidade);
            }
            if (cdEstadoCivil != null) {
                cdEstadoCivil.getPessoaCollection().add(pessoa);
                cdEstadoCivil = em.merge(cdEstadoCivil);
            }

            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Pessoa pessoa) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pessoa persistentPessoa = em.find(Pessoa.class, pessoa.getCdPessoa());
            Cidade cdCidadeOld = persistentPessoa.getCdCidade();
            Cidade cdCidadeNew = pessoa.getCdCidade();
            Estadocivil cdEstadoCivilOld = persistentPessoa.getCdEstadoCivil();
            Estadocivil cdEstadoCivilNew = pessoa.getCdEstadoCivil();
            Collection<Usuario> usuarioCollectionOld = persistentPessoa.getUsuarioCollection();
            Collection<Usuario> usuarioCollectionNew = pessoa.getUsuarioCollection();
            if (cdCidadeNew != null) {
                cdCidadeNew = em.getReference(cdCidadeNew.getClass(), cdCidadeNew.getCdCidade());
                pessoa.setCdCidade(cdCidadeNew);
            }
            if (cdEstadoCivilNew != null) {
                cdEstadoCivilNew = em.getReference(cdEstadoCivilNew.getClass(), cdEstadoCivilNew.getCdEstadoCivil());
                pessoa.setCdEstadoCivil(cdEstadoCivilNew);
            }
            Collection<Usuario> attachedUsuarioCollectionNew = new ArrayList<Usuario>();
            for (Usuario usuarioCollectionNewUsuarioToAttach : usuarioCollectionNew) {
                usuarioCollectionNewUsuarioToAttach = em.getReference(usuarioCollectionNewUsuarioToAttach.getClass(), usuarioCollectionNewUsuarioToAttach.getCdUsuario());
                attachedUsuarioCollectionNew.add(usuarioCollectionNewUsuarioToAttach);
            }
            usuarioCollectionNew = attachedUsuarioCollectionNew;
            pessoa.setUsuarioCollection(usuarioCollectionNew);
            pessoa = em.merge(pessoa);
            if (cdCidadeOld != null && !cdCidadeOld.equals(cdCidadeNew)) {
                cdCidadeOld.getPessoaCollection().remove(pessoa);
                cdCidadeOld = em.merge(cdCidadeOld);
            }
            if (cdCidadeNew != null && !cdCidadeNew.equals(cdCidadeOld)) {
                cdCidadeNew.getPessoaCollection().add(pessoa);
                cdCidadeNew = em.merge(cdCidadeNew);
            }
            if (cdEstadoCivilOld != null && !cdEstadoCivilOld.equals(cdEstadoCivilNew)) {
                cdEstadoCivilOld.getPessoaCollection().remove(pessoa);
                cdEstadoCivilOld = em.merge(cdEstadoCivilOld);
            }
            if (cdEstadoCivilNew != null && !cdEstadoCivilNew.equals(cdEstadoCivilOld)) {
                cdEstadoCivilNew.getPessoaCollection().add(pessoa);
                cdEstadoCivilNew = em.merge(cdEstadoCivilNew);
            }
            for (Usuario usuarioCollectionOldUsuario : usuarioCollectionOld) {
                if (!usuarioCollectionNew.contains(usuarioCollectionOldUsuario)) {
                    usuarioCollectionOldUsuario.setCdPessoa(null);
                    usuarioCollectionOldUsuario = em.merge(usuarioCollectionOldUsuario);
                }
            }

            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = pessoa.getCdPessoa();
                if (findPessoa(id) == null) {
                    throw new NonexistentEntityException("The pessoa with id " + id + " no longer exists.");
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
            Pessoa pessoa;
            try {
                pessoa = em.getReference(Pessoa.class, id);
                pessoa.getCdPessoa();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pessoa with id " + id + " no longer exists.", enfe);
            }
            Cidade cdCidade = pessoa.getCdCidade();
            if (cdCidade != null) {
                cdCidade.getPessoaCollection().remove(pessoa);
                cdCidade = em.merge(cdCidade);
            }
            Estadocivil cdEstadoCivil = pessoa.getCdEstadoCivil();
            if (cdEstadoCivil != null) {
                cdEstadoCivil.getPessoaCollection().remove(pessoa);
                cdEstadoCivil = em.merge(cdEstadoCivil);
            }
            Collection<Usuario> usuarioCollection = pessoa.getUsuarioCollection();
            for (Usuario usuarioCollectionUsuario : usuarioCollection) {
                try {
                    usuarioCollectionUsuario.setCdPessoa(null);
                } catch (PropertyVetoException ex) {
                    Logger.getLogger(PessoaJpaController.class.getName()).log(Level.SEVERE, null, ex);
                }
                usuarioCollectionUsuario = em.merge(usuarioCollectionUsuario);
            }
            em.remove(pessoa);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Pessoa> findPessoaEntities() {
        return findPessoaEntities(true, -1, -1);
    }

    public List<Pessoa> findPessoaEntities(int maxResults, int firstResult) {
        return findPessoaEntities(false, maxResults, firstResult);
    }

    private List<Pessoa> findPessoaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Pessoa as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Pessoa findPessoa(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Pessoa.class, id);
        } finally {
            em.close();
        }
    }

    public int getPessoaCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Pessoa as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
