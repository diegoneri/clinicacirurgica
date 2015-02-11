/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cirurgica.dao;

import java.net.URLClassLoader;
import java.util.Arrays;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author dfelix3
 */
public class MyEntityManager {

    private static EntityManagerFactory emf = null;
    private static EntityManager em = null;

    public static EntityManagerFactory getEntityManagerFactory() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("bdcirurgicaPU");
        }

        return emf;
    }

    public static EntityManager getEntityManager() {
        if (!(em != null && em.isOpen())) {
            em = getEntityManagerFactory().createEntityManager();
        }
        return em;
    }

    public static void main(String args[]) {
        URLClassLoader classLoader = (URLClassLoader) MyEntityManager.class.getClassLoader();
        String texto = Arrays.toString(classLoader.getURLs()).replace(',', '\n');
        System.out.println(texto);
        getEntityManager();
    }
}
