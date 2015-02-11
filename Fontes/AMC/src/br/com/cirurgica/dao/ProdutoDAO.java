/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cirurgica.dao;

import br.com.cirurgica.generated.controller.ProdutoJpaController;
import br.com.cirurgica.generated.controller.exceptions.IllegalOrphanException;
import br.com.cirurgica.generated.controller.exceptions.NonexistentEntityException;
import br.com.cirurgica.model.Fornecedor;
import br.com.cirurgica.model.FornecedorProduto;
import br.com.cirurgica.model.Produto;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author dfelix3
 */
public final class ProdutoDAO extends GenericDAO<Produto>{

    private ProdutoJpaController jpa;

    public Produto getProdutoAlterar(Integer id){
        EntityManager em = MyEntityManager.getEntityManager();
        return em.find(Produto.class, id);
    }

    public ProdutoDAO() {
        super(Produto.class);
        jpa = new ProdutoJpaController(MyEntityManager.getEntityManagerFactory());
    }

    public List<Produto> getAllByNome(Produto produto) {
        TypedQuery<Produto> q =
                MyEntityManager.getEntityManager().createNamedQuery("Produto.findByNome", Produto.class);
        String parameter = getLikeValue(produto.getNome());
        q.setParameter("nome", parameter);

        return q.getResultList();
    }

    public List<Produto> getAllByCodigoFarmaceutico(Produto produto) {
        Query q =
                MyEntityManager.getEntityManager().createNamedQuery("Produto.findAllByCdFarmaceutico");
        String parameter = getLikeValue(produto.getCdFarmaceutico());
        q.setParameter("cdFarmaceutico", parameter);

        return (List<Produto>) q.getResultList();
    }

    public List<Produto> getAllByFornecedor(Fornecedor fornecedor) {
        List<FornecedorProduto> lista = this.getFornecedorProdutoByFornecedor(fornecedor);
        List<Produto> produtos = new ArrayList<>();

        for(FornecedorProduto fp: lista){
            produtos.add(fp.getProduto());
        }

        return produtos;
    }

    private List<FornecedorProduto> getFornecedorProdutoByFornecedor(Fornecedor fornecedor) {
        Query q =
                MyEntityManager.getEntityManager().createNamedQuery("Produto.findByFornecedor");
        q.setParameter("fornecedor", fornecedor);

        return (List<FornecedorProduto>) q.getResultList();
    }

    public Produto getByCodigoFarmaceutico(Produto produto) {
        Query q =
                MyEntityManager.getEntityManager().createNamedQuery("Produto.findByCdFarmaceutico");
        q.setParameter("cdFarmaceutico", produto.getCdFarmaceutico());
        return (Produto)q.getSingleResult();
    }
    public Produto getByCodigoFarmaceutico(String codigoFarmaceutico) {
        Produto p = new Produto();
        p.setCdFarmaceutico(codigoFarmaceutico);
        return this.getByCodigoFarmaceutico(p);
    }

    @Override
    public void excluir(Produto produto) throws Exception{
        try {
            jpa.destroy(produto.getCdProduto());
        } catch (IllegalOrphanException ex) {
            System.out.println("Produto duplicado em estoque!");
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.WARN, null, ex);
            throw ex;
        } catch (NonexistentEntityException ex) {
            System.out.println("Produto não encontrado!");
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.WARN, null, ex);
            throw ex;
        }
    }
    @Override
    public void incluir(Produto p) throws Exception {
        try {
            new ProdutoJpaController(MyEntityManager.getEntityManagerFactory()).create(p);
        } catch (Exception ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.WARN, null, ex);
            throw ex;
        }
    }
    @Override
    public void alterar(Produto p) throws Exception{
        try {
            jpa.edit(p);
        } catch (IllegalOrphanException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.WARN, null, ex);
            throw ex;
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.WARN, null, ex);
            throw ex;
        } catch (Exception ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.WARN, null, ex);
            throw ex;
        }
    }

    @Override
    protected String getOrderingFieldName() {
        return "cdFarmaceutico";
    }

}
