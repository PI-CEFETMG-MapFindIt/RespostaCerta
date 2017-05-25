/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.model.service;

import br.cefetmg.respostaCerta.model.dao.ForumDAO;
import br.cefetmg.respostaCerta.model.dao.ForumDAOImpl;
import br.cefetmg.respostaCerta.model.dao.TopicDAO;
import br.cefetmg.respostaCerta.model.dao.TopicDAOImpl;
import br.cefetmg.respostaCerta.model.domain.Forum;
import br.cefetmg.respostaCerta.model.domain.Question;
import br.cefetmg.respostaCerta.model.domain.Topic;
import br.cefetmg.respostaCerta.model.exception.BusinessException;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;
import java.time.LocalDate;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author pedro
 */
public class ForumManagementImplTest {
    private static ForumDAO forumDAO;
    private static TopicDAO topicDAO;
    private static ForumManagementImpl impl;
    
    public ForumManagementImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        forumDAO = ForumDAOImpl.getInstance();
        topicDAO = TopicDAOImpl.getInstance();
        impl = new ForumManagementImpl(forumDAO,topicDAO);
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        List<Forum> us;
        try {
            us = forumDAO.listAll();
            for(Forum a : us){
                forumDAO.delete(a.getIdForum());
            }
        } catch (PersistenceException ex) {
            System.out.println("Erro!");
        }
        
        List<Topic> us2;
        try {
            us2 = topicDAO.listAll();
            for(Topic a : us2){
                topicDAO.delete(a.getTopicoId());
            }
        } catch (PersistenceException ex) {
            System.out.println("Erro!");
        }
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of registerForum method, of class ForumManagementImpl.
     */
    @Test
    public void testRegisterForum1() throws Exception {
        System.out.println("RegisterForum1");
        try{
            impl.registerForum(null);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("O forum não pode ser nulo"));
            return;
        }
        fail("Aceitou forum nulo");
    }
    
    @Test
    public void testRegisterForum2() throws Exception {
        System.out.println("RegisterForum2");
        Forum forum = new Forum();
        forum.setQuestao(new Question());
        try{
            impl.registerForum(forum);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("Data de criação não pode ser nula"));
            return;
        }
        fail("Aceitou Data de Criação nula");
    }
    
    @Test
    public void testRegisterForum3() throws Exception {
        System.out.println("RegisterForum3");
        Forum forum = new Forum();
        forum.setDataCriacao(LocalDate.MIN);
        try{
            impl.registerForum(forum);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("O forum deve referenciar uma questao"));
            return;
        }
        fail("Aceitou Questão nula");
    }
    
    /**
     * Test of updateForum method, of class ForumManagementImpl.
     */
   
    @Test
    public void testUpdateForum1() throws Exception {
        System.out.println("UpdateForum1");
        try{
            impl.updateForum(null,new Forum());
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("Id não pode ser nulo"));
            return;
        }
        fail("Aceitou Id nulo");
    } 
    
    @Test
    public void testUpdateForum2() throws Exception {
        System.out.println("UpdateForum2");
        try{
            impl.updateForum(new Long(1),null);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("O forum não pode ser nulo"));
            return;
        }
        fail("Aceitou forum nulo");
    }
    
    @Test
    public void testUpdateForum3() throws Exception {
        System.out.println("UpdateForum3");
        Forum forum = new Forum();
        forum.setQuestao(new Question());
        try{
            impl.updateForum(new Long(1),forum);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("Data de criação não pode ser nula"));
            return;
        }
        fail("Aceitou data de criação nula");
    }

    @Test
    public void testUpdateForum4() throws Exception {
        System.out.println("UpdateForum4");
        Forum forum = new Forum();
        forum.setDataCriacao(LocalDate.MIN);
        try{
            impl.updateForum(new Long(1),forum);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("O forum deve referenciar uma questao"));
            return;
        }
        fail("Aceitou questão nula");
    } 
    
    /**
     * Test of removeForum method, of class ForumManagementImpl.
     */
    @Test
    public void testRemoveForum() throws Exception {
        System.out.println("RemoveForum");
        try{
            impl.removeForum(null);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("Id não pode ser nulo"));
            return;
        }
        fail("Aceitou Id nulo");
    }

    /**
     * Test of getForumById method, of class ForumManagementImpl.
     */
    @Test
    public void testGetForumById() throws Exception {
        System.out.println("GetForumById");
        try{
            impl.getForumById(null);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("Id não pode ser nulo"));
            return;
        }
        fail("Aceitou Id nulo");
    }
    
}
