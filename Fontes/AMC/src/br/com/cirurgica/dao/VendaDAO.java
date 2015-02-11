/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cirurgica.dao;

import br.com.cirurgica.generated.controller.VendaJpaController;
import br.com.cirurgica.generated.controller.VendaProdutoJpaController;
import br.com.cirurgica.generated.controller.exceptions.IllegalOrphanException;
import br.com.cirurgica.generated.controller.exceptions.NonexistentEntityException;
import br.com.cirurgica.generated.model.Venda;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author dfelix3
 */
public class VendaDAO {

    private final VendaJpaController controller;
    private final VendaProdutoJpaController vpController;

    public VendaDAO() {
        EntityManagerFactory emf = MyEntityManager.getEntityManagerFactory();
        controller = new VendaJpaController(emf);
        vpController = new VendaProdutoJpaController(emf);
    }

    public void incluir(br.com.cirurgica.generated.model.Venda venda) throws Exception {
        try {
            controller.create(venda);
        } catch (Exception ex) {
            Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }

    public void alterar(Venda vendaBean) throws Exception {
        try {
            controller.edit(vendaBean);
        } catch (Exception ex) {
            Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }

    public void excluir(Integer id) throws Exception {
        try {
            controller.destroy(id);
        } catch (IllegalOrphanException | NonexistentEntityException ex) {
            Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("Erro ao excluir a venda", ex);
        }
    }
    /*
     public void excluirVenda(Venda venda) throws SQLException {
     Connection conn = ConnectionManager.getConnection();
     conn.setAutoCommit(false);

     String sql = " SELECT v.cd_venda"
     + "         , v.vl_desconto_produto"
     + "         , v.cd_produto"
     + "         , v.qtde_produto"
     + "         , v.vl_venda_produto"
     + "      FROM vendaproduto v"
     + "     WHERE cd_venda = ?";
     String sqlUpdateProduto = "UPDATE produto "
     + "                   SET qtde_estoque = qtde_estoque + ? "
     + "                 WHERE cd_produto = ?";
     String sqlDeleteVendaProduto = "DELETE FROM vendaproduto WHERE cd_produto = ? AND cd_venda = ?";
     String sqlExcluiVenda = "DELETE FROM venda WHERE cd_venda = ?";

     List<br.com.cirurgica.model.VendaProduto> produtosVenda = null;
     try (
     PreparedStatement pstmt = conn.prepareStatement(sql);
     PreparedStatement pstmt1 = conn.prepareStatement(sqlUpdateProduto);
     PreparedStatement pstmt2 = conn.prepareStatement(sqlDeleteVendaProduto);
     PreparedStatement pstmt3 = conn.prepareStatement(sqlExcluiVenda);) {

     pstmt.setInt(1, venda.getCdVenda());
     ResultSet rs = pstmt.executeQuery();
     produtosVenda = new ArrayList<>();
     while (rs.next()) {
     VendaProduto v = new VendaProduto();
     v.setCdVenda(rs.getInt("cd_venda"));
     v.setCdProduto(rs.getInt("cd_produto"));
     v.setQtdeProduto(rs.getInt("qtde_produto"));
     produtosVenda.add(v);

     pstmt3.setInt(1, venda.getCdVenda());
     }
     if (produtosVenda != null && produtosVenda.size() > 0) {
     for (VendaProduto pvenda : produtosVenda) {

     pstmt1.setInt(1, pvenda.getQtdeProduto());
     pstmt1.setInt(2, pvenda.getCdProduto());
     pstmt1.executeUpdate();

     pstmt2.setInt(1, pvenda.getCdProduto());
     pstmt2.setInt(2, pvenda.getCdVenda());
     pstmt2.executeUpdate();
     }
     pstmt3.executeUpdate();
     }
     conn.commit();
     } catch (Exception e) {
     Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, e);
     conn.rollback();
     } finally {
     conn.setAutoCommit(true);
     }

     }
     */

    public List<Venda> findVendaByData(Date data) {
        EntityManager em = MyEntityManager.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Venda> cq = cb.createQuery(Venda.class);
        Root<Venda> vendaQuery = cq.from(Venda.class);

        Predicate eqData = cb.equal(vendaQuery.get("dataVenda"), data);
        cq.where(eqData);

        TypedQuery<Venda> tq = em.createQuery(cq);
        return tq.getResultList();
    }
    /*  
     public List<Venda> obterVendas(Date data) {
     String dataFormat = new SimpleDateFormat("yyyy-MM-dd").format(data);
     String dataInicio = dataFormat + " 00:00:00";
     String dataFim = dataFormat + " 23:59:59";
     List<Venda> list = new ArrayList<>();
     Connection conn = ConnectionManager.getConnection();
     String sql = " SELECT v.cd_venda"
     + "         , v.valor_total_venda"
     + "         , v.data_venda"
     + "         , v.cd_usuario"
     + "         , v.cd_nota_fiscal"
     + "         , v.cd_cliente"
     + "         , v.cd_forma_pagamento"
     + "         , v.nr_parcelas"
     + "         , v.vl_dinheiro"
     + "         , v.vl_troco"
     + "         , v.vl_parcela"
     + "         , v.nm_cupom_cartao"
     + "         , v.nm_tipo_venda "
     + "      FROM venda v"
     + "     WHERE data_venda >= ?"
     + "       AND data_venda <= ?"
     + "  ORDER BY data_venda DESC ";
     try (
     PreparedStatement pstmt = conn.prepareStatement(sql);) {
     pstmt.setString(1, dataInicio);
     pstmt.setString(2, dataFim);
     ResultSet rs = pstmt.executeQuery();
     while (rs.next()) {

     Venda v = new Venda();
     v.setCdVenda(rs.getInt("cd_venda"));
     v.setValorTotalVenda(rs.getDouble("valor_total_venda"));
     v.setDataVenda(new Date(rs.getTimestamp(("data_venda")).getTime()));
     v.setUsuario(new Usuario(rs.getInt("cd_usuario")));
     v.setNf(new NotaFiscal(rs.getInt("cd_nota_fiscal")));
     v.setTipoVenda(rs.getString("nm_tipo_venda"));
     list.add(v);
     }
     } catch (SQLException ex) {
     //TODO log
     }
     return list;
     }
     */

    public List<Venda> findVendas() {
        EntityManager em = MyEntityManager.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Venda> cq = cb.createQuery(Venda.class);
        cq.from(Venda.class);

        TypedQuery<Venda> tq = em.createQuery(cq);
        return tq.getResultList();
    }
}
