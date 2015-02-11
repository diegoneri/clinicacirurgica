/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cirurgica.generated;

import br.com.cirurgica.generated.controller.ProdutoJpaController;
import br.com.cirurgica.model.Produto;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author dfelix3
 */
public class MainGenerated {
    
    private static EntityManagerFactory emf = null;
    private static EntityManager em = null;
    
    public static EntityManagerFactory getEntityManagerFactory() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("bdcirurgicaPU");
        }
        return emf;
    }
    
    public static EntityManager getEntityManager() {
        if (em != null && em.isOpen()) {
            return em;
        } else {
            em = getEntityManagerFactory().createEntityManager();
            return em;
        }
    }    
    
    public static void main(String args[]) {
        
//        EntityManager em = getEntityManager();
//        EntityTransaction transaction = em.getTransaction();
//        transaction.begin();
//        transaction.commit();
//        boolean isconectado = em.isOpen();
//        em.close();        
        EntityManagerFactory emf = getEntityManagerFactory();
        ProdutoJpaController produtoController = new ProdutoJpaController(emf);
        
        List<Produto> list = produtoController.findProdutoEntities();
        
        for (Produto p : list) {
            System.out.println(p);
        }        

        Query q = getEntityManager().createNamedQuery("Produto.findByCdFarmaceutico");
        q.setParameter("cdFarmaceutico", "ABC1234");
        Produto p2 = (Produto) q.getSingleResult();
        System.out.println(p2.toString());
        
                
        
    }
}
