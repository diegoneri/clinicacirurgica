/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cirurgica.dao;

import br.com.cirurgica.generated.controller.PedidoJpaController;
import br.com.cirurgica.generated.controller.exceptions.IllegalOrphanException;
import br.com.cirurgica.generated.controller.exceptions.NonexistentEntityException;
import br.com.cirurgica.model.Pedido;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.TypedQuery;

/**
 *
 * @author dfelix3
 */
public final class PedidoDAO extends GenericDAO<Pedido>{
    private final PedidoJpaController jpa;

    public PedidoDAO(){
        super(Pedido.class);
        jpa = new PedidoJpaController(MyEntityManager.getEntityManagerFactory());
    }

    @Override
    public void incluir(Pedido value) throws Exception{
        try {
            jpa.create(value);
        } catch (Exception ex) {
            Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }

    @Override
    public void alterar(Pedido value) throws Exception{
        try {
            jpa.edit(value);
        } catch (IllegalOrphanException ex) {
            Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        } catch (Exception ex) {
            Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }

    @Override
    public void excluir(Pedido value) throws Exception{
        try {
            jpa.destroy(value.getCdPedido());
        } catch (IllegalOrphanException ex) {
            System.out.println("Pedido duplicado!");
            Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        } catch (NonexistentEntityException ex) {
            System.out.println("Pedido não encontrado!");
            Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }

    public List<Pedido> getByDataPedido(Date dataPedido) {
        TypedQuery<Pedido> q = MyEntityManager.getEntityManager().createNamedQuery("Pedido.findByDataPedido", Pedido.class);
        q.setParameter("dataPedido", dataPedido);
        return q.getResultList();
    }

    @Override
    protected String getOrderingFieldName() {
        return "dataPedido";
    }
}
