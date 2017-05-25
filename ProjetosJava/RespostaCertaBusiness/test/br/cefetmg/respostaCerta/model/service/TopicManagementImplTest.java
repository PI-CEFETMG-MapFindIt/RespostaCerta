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
import br.cefetmg.respostaCerta.model.domain.Topic;
import br.cefetmg.respostaCerta.model.domain.User;
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
 * @author umcan
 */
public class TopicManagementImplTest { 
    private static TopicDAO topicDAO;
    private static ForumDAO forumDAO;
    private static TopicManagementImpl impl;
    
    public TopicManagementImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        topicDAO = TopicDAOImpl.getInstance();
        forumDAO = ForumDAOImpl.getInstance();
        impl = new TopicManagementImpl(topicDAO);
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
        List<Topic> us;
        try {
            us = topicDAO.listAll();
            for(Topic u : us){
                topicDAO.delete(u.getTopicoId());
            }
        } catch (PersistenceException ex) {
            System.out.println("Erro!");
        }
        
        List<Forum> fo;
        try {
            fo = forumDAO.listAll();
            for(Forum u : fo){
                forumDAO.delete(u.getIdForum());
            }
        } catch (PersistenceException ex) {
            System.out.println("Erro!");
        }
    }

    @Test
    public void testRegisterTopic1() throws Exception {
        System.out.println("registerTopic1");
        Topic topic = null;
        try{
            impl.registerTopic(topic);
        }catch(BusinessException ex){
            assertTrue("Topico não pode ser nulo".equals(ex.getMessage()));
            return;
        }
        fail("Aceitou topico nulo");
    }
    
    @Test
    public void testRegisterTopic2() throws Exception {
        System.out.println("registerTopic2");
        Topic topic = new Topic();
        topic.setDataPostagem(null);
        topic.setForum(new Forum());
        topic.setTopicoId(Long.MAX_VALUE);
        topic.setAutor(new User("joao", "joaoteste@gmail.com", "senha", 'p'));
        topic.setTxtMensagem("txt");
          
        try{
            impl.registerTopic(topic);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("Data da postagem não pode ser nulo"));
            return;
        }
        fail("Aceitou topico com data de postagem nula");
    }
    
    @Test
    public void testRegisterTopic3() throws Exception {
        System.out.println("registerTopic3");
        Topic topic = new Topic();
        topic.setDataPostagem(LocalDate.now());
        topic.setForum(new Forum());
        topic.setTopicoId(Long.MAX_VALUE);
        topic.setAutor(null);
        topic.setTxtMensagem("txt");
        try{
            impl.registerTopic(topic);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("Autor não pode ser nulo"));
            return;
        }
        fail("Aceitou topico com autor nulo");
    }
    
    @Test
    public void testRegisterTopic4() throws Exception {
        System.out.println("registerTopic4");
        Topic topic = new Topic();
        topic.setAutor(new User("Joao", "joao@gmail.com", "senha", 'j'));
        topic.setDataPostagem(LocalDate.now());
        topic.setForum(new Forum());
        topic.setTopicoId(Long.MAX_VALUE);
        topic.setTxtMensagem(null);
        try{
            impl.registerTopic(topic);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("Texto da mensagem não pode ser nulo"));
            return;
        }
        fail("Aceitou topico com mensagem nula");
    }
    
    @Test
    public void testRegisterTopic5() throws Exception {
        System.out.println("registerQuestion5");
        Topic topic = new Topic();
        topic.setAutor(new User("Joao", "joao@gmail.com", "senha", 'j'));
        topic.setDataPostagem(LocalDate.now());
        topic.setForum(new Forum());
        topic.setTopicoId(Long.MAX_VALUE);
        topic.setTxtMensagem("txt");
        try{
            impl.registerTopic(topic);
            assertEquals(topic, impl.getTopicById(topic.getTopicoId()));
        }catch(BusinessException|PersistenceException ex){
            fail("Erro ao inserir");
        }
        
    }
    /**
     * Test of updateTopic method, of class TopicManagementImpl.
     */
    @Test
    public void testUpdateTopic1() throws Exception {
        System.out.println("updateTopic1");
        Topic topic = new Topic();
        topic.setAutor(new User("Joao", "joao@gmail.com", "senha", 'j'));
        topic.setDataPostagem(LocalDate.now());
        topic.setForum(new Forum());
        topic.setTopicoId(Long.MAX_VALUE);
        topic.setTxtMensagem("txt");
        try{
            impl.updateTopic(null, topic);
        }catch(BusinessException ex){
            System.out.println(ex);
            assertTrue(ex.getMessage().equals("Id não pode ser nulo"));
            return;
        }
        fail("Aceitou Id nulo");
    }
    
    @Test
    public void testUpdateTopic2() throws Exception {
        System.out.println("updateTopic2");
        Topic topic = null;
        try{
            impl.updateTopic(new Long(1), topic);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("Topico não pode ser nulo"));
            return;
        }
        fail("Aceitou topico nulo");
    }
    
    @Test
    public void testUpdateTopic3() throws Exception {
        System.out.println("updateTopic3");
        Topic topic = new Topic();
        topic.setAutor(new User("Joao", "joao@gmail.com", "senha", 'j'));
        topic.setDataPostagem(null);
        topic.setForum(new Forum());
        topic.setTopicoId(Long.MAX_VALUE);
        topic.setTxtMensagem("txt");
        try{
            impl.updateTopic(new Long(1), topic);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("Data da postagem não pode ser nulo"));
            return;
        }
        fail("Aceitou topico com data de postagem nula");
    }
    
    @Test
    public void testUpdateTopic4() throws Exception {
        System.out.println("updateTopic4");
        Topic topic = new Topic();
        topic.setAutor(null);
        topic.setDataPostagem(LocalDate.now());
        topic.setForum(new Forum());
        topic.setTopicoId(Long.MAX_VALUE);
        topic.setTxtMensagem("txt");
        try{
            impl.updateTopic(new Long(0), topic);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("Autor não pode ser nulo"));
            return;
        }
        fail("Aceitou topico com autor nulo");
    }
    
    @Test
    public void testUpdateTopic5() throws Exception {
        System.out.println("updateTopic5");
        Topic topic = new Topic();
        topic.setAutor(new User("Joao", "joao@gmail.com", "senha", 'j'));
        topic.setDataPostagem(LocalDate.now());
        topic.setForum(new Forum());
        topic.setTopicoId(Long.MAX_VALUE);
        topic.setTxtMensagem(null);
        
        try{
            impl.updateTopic(new Long(0), topic);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("Texto da mensagem não pode ser nulo"));
            return;
        }
        fail("Aceitou topico com texto nulo");
    }
    
    @Test
    public void testUpdateTopic6() throws Exception {
        System.out.println("updateTopic6");
        Topic topic = new Topic();
        topic.setAutor(new User("Joao", "joao@gmail.com", "senha", 'j'));
        topic.setDataPostagem(LocalDate.now());
        topic.setForum(new Forum());
        topic.setTopicoId(Long.MAX_VALUE);
        topic.setTxtMensagem("txt");
        impl.registerTopic(topic);
        topic.setTxtMensagem("txt");
        topic.setForum(new Forum());
        try{
            impl.updateTopic(topic.getTopicoId(), topic);
            assertEquals(topic, impl.getTopicById(topic.getTopicoId()));
        }catch(PersistenceException ex){
            fail("Erro ao atualizar");
            return;
        }
        
    }
   
    /**
     * Test of removeTopic method, of class TopicManagementImpl.
     */
    @Test
    public void testRemoveTopic1() throws Exception {
        System.out.println("removeTopic1");
        Long id = null;
        try{
           impl.removeTopic(id); 
        }catch(BusinessException ex){
           assertEquals(ex.getMessage(), "ID não pode ser nulo");
           return;
        }
        fail("Aceitou id nulo");
    }
    
    /**
     * Test of removeUser method, of class UserManagementImpl.
     */
    @Test
    public void testRemoveTopic2() throws Exception {
        System.out.println("removeTopic2");
        try{
           impl.removeTopic(new Long(0)); 
        }catch(PersistenceException ex){
           return;
        }
        fail("Removeu topico inexistente");
    }
    
    /**
     * Test of removeUser method, of class UserManagementImpl.
     */
    @Test
    public void testRemoveTopic3() throws Exception {
        System.out.println("removeTopic3");
        Topic topic = new Topic();
        topic.setAutor(new User("Joao", "joao@gmail.com", "senha", 'j'));
        topic.setDataPostagem(LocalDate.now());
        topic.setForum(new Forum());
        topic.setTxtMensagem("txt");
        try{
           impl.registerTopic(topic);
           impl.removeTopic(new Long(10)); 
           fail("Removeu topico inexistente");
        }catch(PersistenceException ex){
           return;
        }
    }
    
    /**
     * Test of removeUser method, of class UserManagementImpl.
     */
    @Test
    public void testRemoveTopic4() throws Exception {
        System.out.println("removeTopic4");
        Topic topic = new Topic();
        topic.setAutor(new User("Joao", "joao@gmail.com", "senha", 'j'));
        topic.setDataPostagem(LocalDate.now());
        topic.setForum(new Forum());
        topic.setTopicoId(Long.MAX_VALUE);
        topic.setTxtMensagem("txt");
        try{
           impl.registerTopic(topic);
           impl.removeTopic(topic.getTopicoId());
        }catch(PersistenceException|BusinessException ex){
           fail("Erro ao remover");
        }
        try{
            impl.getTopicById(topic.getTopicoId());
        }catch(Exception ex){
            return;
        }
        fail("Não removeu o topico");
    }

     /**
     * Test of removeUser method, of class UserManagementImpl.
     */
    @Test
    public void testGetTopicById1() throws Exception {
        System.out.println("getTopicById1");
        Long id = null;
        try{
           impl.getTopicById(id);
        }catch(BusinessException ex){
           assertTrue(ex.getMessage().equals("ID não pode ser nulo"));
           return;
        }
        
        fail("Aceitou id nulo");
    }
    
    /**
     * Test of removeUser method, of class UserManagementImpl.
     */
    @Test
    public void testGetTopicById2() throws Exception {
        System.out.println("getTopicById2");
        try{
           impl.getTopicById(new Long(0)); 
        }catch(PersistenceException ex){
           return;
        }
        fail("Buscou resposta inexistente");
    }
    
    /**
     * Test of removeUser method, of class UserManagementImpl.
     */
    @Test
    public void testGetTopicById3() throws Exception {
        System.out.println("getTopicById3");
        Topic topic = new Topic();
        topic.setAutor(new User("Joao", "joao@gmail.com", "senha", 'j'));
        topic.setDataPostagem(LocalDate.now());
        topic.setForum(new Forum());
        topic.setTopicoId(Long.MAX_VALUE);
        topic.setTxtMensagem("txt");
        try{
           impl.registerTopic(topic);
           impl.getTopicById(new Long(4)); 
           fail("Buscou topic inexistente");
        }catch(PersistenceException | BusinessException ex){
           return;
        }
    }
    
    /**
     * Test of removeUser method, of class UserManagementImpl.
     */
    @Test
    public void testGetTopicById4() throws Exception {
        System.out.println("getTopicById4");
        Topic topic = new Topic();
        topic.setAutor(new User("Joao", "joao@gmail.com", "senha", 'j'));
        topic.setDataPostagem(LocalDate.now());
        topic.setForum(new Forum());
        topic.setTopicoId(Long.MAX_VALUE);
        topic.setTxtMensagem("txt");
        try{
           impl.registerTopic(topic);
           assertTrue(impl.getTopicById(topic.getTopicoId())==topic);
        }catch(PersistenceException|BusinessException ex){
           fail("Erro ao obter o topico");
        }
    }

    /**
     * Test of getQuestionsByUser method, of class TopicManagementImpl.
     */
    @Test
    public void testGetTopicsForum1() throws Exception {
        System.out.println("getTopicsForum1");
        Long id = null;
        try{
            impl.getTopicsForum(id);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("Id do forum nao pode ser nulo"));
        }
    }
    
    /**
     * Test of getQuestionsByUser method, of class TopicManagementImpl.
     */
    @Test
    public void testGetTopicsForum2() throws Exception {
        System.out.println("getTopicsByTopicsForum2");
        Forum f = new Forum();
        forumDAO.insert(f);
        Topic topic = new Topic();
        topic.setAutor(new User("Joao", "joao@gmail.com", "senha", 'j'));
        topic.setDataPostagem(LocalDate.now());
        topic.setForum(f);
        topic.setTopicoId(Long.MAX_VALUE);
        topic.setTxtMensagem("txt");
        impl.registerTopic(topic);
        try{
           List list = impl.getTopicsForum(f.getIdForum());
           if(list.size()!=1){
               fail("Lista errada ao retornar");
           }
           if(list.get(0)!=topic){
               fail("Objetos errados na lista");
           }
        }catch(BusinessException | PersistenceException ex){
            fail("Erro :" + ex);
        }
        
    }
    
     
    /**
     * Test of getQuestionsByUser method, of class TopicManagementImpl.
     */
    @Test
    public void testGetTopicsForum3() throws Exception {
        System.out.println("getQuestionsByUser3");
        Forum f = new Forum();
        forumDAO.insert(f);
        
        User user = new User(new Long(0), "Joao", "joao@gmail.com", "senha", 'j');
        Topic topic = new Topic();
        topic.setAutor(user);
        topic.setDataPostagem(LocalDate.now());
        topic.setForum(f);
        topic.setTopicoId(Long.MAX_VALUE);
        topic.setTxtMensagem("txt");
        impl.registerTopic(topic);
        
        Topic topic2 = new Topic();
        topic2.setAutor(user);
        topic2.setDataPostagem(LocalDate.now());
        topic2.setForum(f);
        topic2.setTopicoId(Long.MAX_VALUE);
        topic2.setTxtMensagem("txt");
        impl.registerTopic(topic2);
        try{
           List list = impl.getTopicsForum(f.getIdForum());
           if(list.size()!=2){
               fail("Lista errada ao retornar");
           }
           if(list.get(0)!=topic && list.get(1)!=topic2){
               fail("Objetos errados na lista");
           }
        }catch(BusinessException | PersistenceException ex){
            fail("Erro :" + ex);
        }
    }
    
     
    /**
     * Test of getQuestionsByUser method, of class TopicManagementImpl.
     */
    @Test
    public void testGetTopicsForum4() throws Exception {
        System.out.println("getQuestionsByUser4");
        User user = new User(new Long(1), "Joao", "joao@gmail.com", "senha", 'j');
        Forum f = new Forum();
        forumDAO.insert(f);
        
        Topic topic = new Topic();
        topic.setAutor(user);
        topic.setDataPostagem(LocalDate.now());
        topic.setForum(f);
        topic.setTopicoId(Long.MAX_VALUE);
        topic.setTxtMensagem("msg");
        impl.registerTopic(topic);
        
        Topic topic2 = new Topic();
        topic2.setAutor(user);
        topic2.setDataPostagem(LocalDate.now());
        topic2.setForum(f);
        topic2.setTopicoId(Long.MAX_VALUE);
        topic2.setTxtMensagem("msg");
        impl.registerTopic(topic2);
        
        try{
           List list = impl.getTopicsForum(f.getIdForum());
           if(!list.isEmpty()){
               fail("Lista errada ao retornar");
           }
           
        }catch(BusinessException | PersistenceException ex){
            fail("Erro :" + ex);
        }
    }
    
     
    /**
     * Test of getQuestionsByUser method, of class TopicManagementImpl.
     */
    @Test
    public void testGetTopicsForum5() throws Exception {
        System.out.println("getQuestionsByUser5");
        Forum f2 = new Forum();
        Forum f1 = new Forum();
        forumDAO.insert(f1);
        forumDAO.insert(f2);
        Topic topic = new Topic();
        topic.setAutor(new User("Joao", "joao@gmail.com", "senha", 'j'));
        topic.setDataPostagem(LocalDate.now());
        topic.setForum(f1);
        topic.setTopicoId(Long.MAX_VALUE);
        topic.setTxtMensagem("msg");
        impl.registerTopic(topic);
        Topic topic2 = new Topic();
        topic2.setAutor(new User("Joao", "joao@gmail.com", "senha", 'j'));
        topic2.setDataPostagem(LocalDate.now());
        topic2.setForum(f2);
        topic2.setTopicoId(Long.MAX_VALUE);
        topic2.setTxtMensagem("msg");
        impl.registerTopic(topic2);
        try{
           List list = impl.getTopicsForum(f1.getIdForum());
           if(list.size()!=1){
               fail("Lista errada ao retornar");
           }
           if(list.get(0)!=topic){
               fail("Objeto errado na lista");
           }
           
           list = impl.getTopicsForum(f2.getIdForum());
           if(list.size()!=1){
               fail("Lista errada ao retornar");
           }
           if(list.get(0)!=topic2){
               fail("Objeto errado na lista");
           }
        }catch(BusinessException | PersistenceException ex){
            fail("Erro :" + ex);
        }
    }
    
}
