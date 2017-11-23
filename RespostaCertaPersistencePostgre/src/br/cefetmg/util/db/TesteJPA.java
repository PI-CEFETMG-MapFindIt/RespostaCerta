/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.util.db;

import br.cefetmg.respostaCerta.model.domain.User;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Pós Graduação
 */
public class TesteJPA {
    public static void main(String[] args){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("RespostaCerta");
        EntityManager em = emf.createEntityManager();
        User us = new User(new Long(0), "joão", "joa", "coco", 'A', null);
        em.getTransaction().begin();
        em.persist(us);
        em.getTransaction().commit();
        System.out.println("Persistiu");
        User u = em.find(User.class, us.getIdUsuario());
        System.out.println(u.getNomeUsuario());
        em.close();
        emf.close();
    }
}
