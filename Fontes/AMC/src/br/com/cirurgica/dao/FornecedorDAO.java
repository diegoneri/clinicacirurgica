/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cirurgica.dao;

import br.com.cirurgica.generated.controller.FornecedorJpaController;
import br.com.cirurgica.model.Fornecedor;
import br.com.cirurgica.model.FornecedorProduto;
import br.com.cirurgica.model.Produto;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;

/**
 *
 * @author dfelix3
 */
public final class FornecedorDAO extends GenericDAO<Fornecedor> {

    private FornecedorJpaController jpa;

    public FornecedorDAO() {
        super(Fornecedor.class);
        jpa = new FornecedorJpaController(getEntityManagerFactory());
    }

    public static void main(String args[]){
        FornecedorDAO dao = new FornecedorDAO();
        for(Fornecedor f: dao.findEntities(2)){
            System.out.println(f);
        }
    }
    public Fornecedor getByCodigo(Integer codigo) {
        TypedQuery<Fornecedor> q =
                MyEntityManager.getEntityManager().createNamedQuery("Fornecedor.findByCdFornecedor", Fornecedor.class);
        q.setParameter("cdFornecedor", codigo);
        return (Fornecedor) q.getSingleResult();
    }

    public List<Fornecedor> getAllByNomeFantasia(String nome) {
        TypedQuery<Fornecedor> q =
                MyEntityManager.getEntityManager().createNamedQuery("Fornecedor.findByNomeFantasia", Fornecedor.class);
        String parameter = getLikeValue(nome);
        q.setParameter("nomeFantasia", parameter);

        return q.getResultList();
    }

    public List<Fornecedor> getAllByRazaoSocial(String nome) {
        TypedQuery<Fornecedor> q =
                MyEntityManager.getEntityManager().createNamedQuery("Fornecedor.findByNmRazaoSocial", Fornecedor.class);
        String parameter = getLikeValue(nome);
        q.setParameter("nmRazaoSocial", parameter);

        return q.getResultList();
    }

    public List<Fornecedor> getAllByCNPJ(String cnpj) {
        TypedQuery<Fornecedor> q =
                MyEntityManager.getEntityManager().createNamedQuery("Fornecedor.findByCnpj", Fornecedor.class);
        String parameter = getLikeValue(cnpj);
        q.setParameter("cnpj", parameter);

        return q.getResultList();
    }

    public List<FornecedorProduto> getAllByProduto(Produto produto) {
        TypedQuery<FornecedorProduto> q =
                MyEntityManager.getEntityManager().createNamedQuery("FornecedorProduto.findByCdProduto", FornecedorProduto.class);
        q.setParameter("cdProduto", produto.getCdProduto());

        return q.getResultList();
    }

    @Override
    protected String getOrderingFieldName() {
        return "nmRazaoSocial";
    }

    @Override
    public void incluir(Fornecedor value) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void alterar(Fornecedor value) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void excluir(Fornecedor value) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
