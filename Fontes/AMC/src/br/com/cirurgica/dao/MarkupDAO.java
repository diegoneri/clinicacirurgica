/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cirurgica.dao;

import br.com.cirurgica.generated.model.Markup;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author dfelix3
 */
public class MarkupDAO extends GenericDAO<Markup> {

    public MarkupDAO() {
        super(Markup.class);
    }

    @Override
    public void alterar(Markup m) throws Exception {
        try {
            EntityManager em = MyEntityManager.getEntityManager();
            em.getTransaction().begin();
            Query q =
                    em.createNamedQuery("Markup.update");
            q.setParameter("vlMarkup", m.getVlMarkup());
            q.executeUpdate();
            em.getTransaction().commit();
        } catch (Exception ex) {
            Logger.getLogger(MarkupDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }

    @Override
    public void incluir(Markup value) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void excluir(Markup value) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected String getOrderingFieldName() {
        return "vlMarkup";
    }

}
