/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cirurgica.dao;

import br.com.cirurgica.model.Login;
import javax.persistence.TypedQuery;

/**
 *
 * @author dfelix3
 */
public class LoginDAO extends GenericDAO<Login>{

    public LoginDAO(){
        super(Login.class);
    }
    public Login getUser(Login login){
        TypedQuery<Login> q =
                MyEntityManager.getEntityManager().createNamedQuery("Login.findByNmLoginSenha", Login.class);
        q.setParameter("nmLogin", login.getNmLogin());
        q.setParameter("nmSenha", login.getNmSenha());
        return q.getSingleResult();
    }

    @Override
    public void incluir(Login value) throws Exception {
    }

    @Override
    public void alterar(Login value) throws Exception {
    }

    @Override
    public void excluir(Login value) throws Exception {
    }

    @Override
    protected String getOrderingFieldName() {
        return "nmLogin";
    }

}
