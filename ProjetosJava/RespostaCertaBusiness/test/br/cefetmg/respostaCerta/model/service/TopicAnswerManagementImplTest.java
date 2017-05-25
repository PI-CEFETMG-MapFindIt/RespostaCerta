/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.model.service;

import br.cefetmg.respostaCerta.model.dao.TopicAnswerDAO;
import br.cefetmg.respostaCerta.model.dao.TopicAnswerDAOImpl;
import br.cefetmg.respostaCerta.model.domain.Topic;
import br.cefetmg.respostaCerta.model.domain.TopicAnswer;
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
public class TopicAnswerManagementImplTest {
    
    private static TopicAnswerDAO topicAnswerDAO;
    private static TopicAnswerManagementImpl impl;
    
    public TopicAnswerManagementImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        topicAnswerDAO = TopicAnswerDAOImpl.getInstance();
        impl = new TopicAnswerManagementImpl(topicAnswerDAO);
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        List<TopicAnswer> us;
        try {
            us = topicAnswerDAO.listAll();
            for(TopicAnswer u : us){
                topicAnswerDAO.delete(u.getIdMensagemResposta());
            }
        } catch (PersistenceException ex) {
            System.out.println("Erro!");
        }
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testRegisterTopicAnswer1() throws Exception {
        System.out.println("registerTopicAnswer1");
        TopicAnswer topic = null;
        try{
            impl.registerTopicAnswer(topic);
        }catch(BusinessException ex){
            assertTrue("TopicAnswer não pode ser nulo".equals(ex.getMessage()));
            return;
        }
        fail("Aceitou topico nulo");
    }
    
    @Test
    public void testRegisterTopicAnswer2() throws Exception {
        System.out.println("registerTopicAnswer2");
        TopicAnswer topic = new TopicAnswer();
        topic.setAutor(new User("Joao", "joao@gmail.com", "senha", 'j'));
        topic.setDataResposta(null);
        topic.setMensagem(new Topic());
        topic.setIdMensagemResposta(Long.MAX_VALUE);
        topic.setTxtMensagem("txt");
          
        try{
            impl.registerTopicAnswer(topic);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("Data da resposta não pode ser nula"));
            return;
        }
        fail("Aceitou mensagem com data de resposta nula");
    }
    
    @Test
    public void testRegisterTopicAnswer3() throws Exception {
        System.out.println("registerTopicAnswer3");
        TopicAnswer topic = new TopicAnswer();
        topic.setAutor(null);
        topic.setDataResposta(LocalDate.now());
        topic.setMensagem(new Topic());
        topic.setIdMensagemResposta(Long.MAX_VALUE);
        topic.setTxtMensagem("txt");
        try{
            impl.registerTopicAnswer(topic);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("Autor da resposta não pode ser nulo"));
            return;
        }
        fail("Aceitou topico com autor nulo");
    }
    
    @Test
    public void testRegisterTopicAnswer4() throws Exception {
        System.out.println("registerTopicAnswer4");
        TopicAnswer topic = new TopicAnswer();
        topic.setAutor(new User("Joao", "joao@gmail.com", "senha", 'j'));
        topic.setDataResposta(LocalDate.now());
        topic.setMensagem(new Topic());
        topic.setIdMensagemResposta(Long.MAX_VALUE);
        topic.setTxtMensagem(null);
        
        try{
            impl.registerTopicAnswer(topic);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("Texto não pode ser nulo"));
            return;
        }
        fail("Aceitou topico com mensagem nula");
    }
    
    @Test
    public void testRegisterTopicAnswer5() throws Exception {
        System.out.println("registerQuestion5");
        TopicAnswer topic = new TopicAnswer();
        topic.setAutor(new User("Joao", "joao@gmail.com", "senha", 'j'));
        topic.setDataResposta(LocalDate.now());
        topic.setMensagem(new Topic());
        topic.setIdMensagemResposta(Long.MAX_VALUE);
        topic.setTxtMensagem("txt");
        try{
            impl.registerTopicAnswer(topic);
            assertEquals(topic, impl.getTopicAnswerById(topic.getIdMensagemResposta()));
        }catch(BusinessException|PersistenceException ex){
            fail("Erro ao inserir");
        }
        
    }
    /**
     * Test of updateTopicAnswer method, of class TopicAnswerManagementImpl.
     */
    @Test
    public void testUpdateTopicAnswer1() throws Exception {
        System.out.println("updateTopicAnswer1");
        TopicAnswer topic = new TopicAnswer();
        topic.setAutor(new User("Joao", "joao@gmail.com", "senha", 'j'));
        topic.setDataResposta(LocalDate.now());
        topic.setMensagem(new Topic());
        topic.setIdMensagemResposta(Long.MAX_VALUE);
        topic.setTxtMensagem("txt");
        try{
            impl.updateTopicAnswer(null, topic);
        }catch(BusinessException ex){
            System.out.println(ex);
            assertTrue(ex.getMessage().equals("ID não pode ser nulo"));
            return;
        }
        fail("Aceitou Id nulo");
    }
    
    @Test
    public void testUpdateTopicAnswer2() throws Exception {
        System.out.println("updateTopicAnswer2");
        TopicAnswer topic = null;
        try{
            impl.updateTopicAnswer(new Long(1), topic);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("TopicAnswer não pode ser nulo"));
            return;
        }
        fail("Aceitou topico nulo");
    }
    
    @Test
    public void testUpdateTopicAnswer3() throws Exception {
        System.out.println("updateTopicAnswer3");
        TopicAnswer topic = new TopicAnswer();
        topic.setAutor(new User("Joao", "joao@gmail.com", "senha", 'j'));
        topic.setDataResposta(null);
        topic.setMensagem(new Topic());
        topic.setIdMensagemResposta(Long.MAX_VALUE);
        topic.setTxtMensagem("txt");
        try{
            impl.updateTopicAnswer(new Long(1), topic);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("Data de resposta não pode ser nula"));
            return;
        }
        fail("Aceitou topico com data de postagem nula");
    }
    
    @Test
    public void testUpdateTopicAnswer4() throws Exception {
        System.out.println("updateTopicAnswer4");
        TopicAnswer topic = new TopicAnswer();
        topic.setAutor(null);
        topic.setDataResposta(LocalDate.now());
        topic.setMensagem(new Topic());
        topic.setIdMensagemResposta(Long.MAX_VALUE);
        topic.setTxtMensagem("txt");
        try{
            impl.updateTopicAnswer(new Long(0), topic);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("Autor da resposta não pode ser nulo"));
            return;
        }
        fail("Aceitou topico com autor nulo");
    }
    
    @Test
    public void testUpdateTopicAnswer5() throws Exception {
        System.out.println("updateTopicAnswer5");
        TopicAnswer topic = new TopicAnswer();
        topic.setAutor(new User("Joao", "joao@gmail.com", "senha", 'j'));
        topic.setDataResposta(LocalDate.now());
        topic.setMensagem(new Topic());
        topic.setIdMensagemResposta(Long.MAX_VALUE);
        topic.setTxtMensagem(null);
        try{
            impl.updateTopicAnswer(new Long(0), topic);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("Texto não pode ser nulo"));
            return;
        }
        fail("Aceitou topico com texto nulo");
    }
    
    @Test
    public void testUpdateTopicAnswer6() throws Exception {
        System.out.println("updateTopicAnswer6");
        TopicAnswer topic = new TopicAnswer();
        topic.setAutor(new User("Joao", "joao@gmail.com", "senha", 'j'));
        topic.setDataResposta(LocalDate.now());
        topic.setMensagem(new Topic());
        topic.setTxtMensagem("txt");
        impl.registerTopicAnswer(topic);
        topic.setMensagem(new Topic());
        topic.setTxtMensagem("txt2");
        try{
            impl.updateTopicAnswer(topic.getIdMensagemResposta(), topic);
            assertEquals(topic, impl.getTopicAnswerById(topic.getIdMensagemResposta()));
        }catch(BusinessException ex){
            fail("Erro ao atualizar");
        }
        fail("Aceitou id nulo");
    }
   
    /**
     * Test of removeTopicAnswer method, of class TopicAnswerManagementImpl.
     */
    @Test
    public void testRemoveTopicAnswer1() throws Exception {
        System.out.println("removeTopicAnswer1");
        Long id = null;
        try{
           impl.removeTopicAnswer(id); 
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
    public void testRemoveTopicAnswer2() throws Exception {
        System.out.println("removeTopicAnswer2");
        try{
           impl.removeTopicAnswer(new Long(0)); 
        }catch(PersistenceException ex){
           return;
        }
        fail("Removeu topico inexistente");
    }
    
    /**
     * Test of removeUser method, of class UserManagementImpl.
     */
    @Test
    public void testRemoveTopicAnswer3() throws Exception {
        System.out.println("removeTopicAnswer3");
        TopicAnswer topic = new TopicAnswer();
        topic.setAutor(new User("Joao", "joao@gmail.com", "senha", 'j'));
        topic.setDataResposta(LocalDate.now());
        topic.setMensagem(new Topic());
        topic.setIdMensagemResposta(Long.MAX_VALUE);
        topic.setTxtMensagem("txt");
        try{
           impl.registerTopicAnswer(topic);
           impl.removeTopicAnswer(new Long(3)); 
           fail("Removeu topic inexistente");
        }catch(PersistenceException ex){
           return;
        }
        fail("Removeu topic inexistente");
    }
    
    /**
     * Test of removeUser method, of class UserManagementImpl.
     */
    @Test
    public void testRemoveTopicAnswer4() throws Exception {
        System.out.println("removeTopicAnswer4");
        TopicAnswer topic = new TopicAnswer();
        topic.setAutor(new User("Joao", "joao@gmail.com", "senha", 'j'));
        topic.setDataResposta(LocalDate.now());
        topic.setMensagem(new Topic());
        topic.setIdMensagemResposta(Long.MAX_VALUE);
        try{
           impl.registerTopicAnswer(topic);
           impl.removeTopicAnswer(topic.getIdMensagemResposta());
        }catch(PersistenceException|BusinessException ex){
           fail("Erro ao remover");
        }
        try{
            impl.getTopicAnswerById(topic.getIdMensagemResposta());
        }catch(Exception ex){
            return;
        }
        fail("Não removeu o topico");
    }

     /**
     * Test of removeUser method, of class UserManagementImpl.
     */
    @Test
    public void testGetTopicAnswerById1() throws Exception {
        System.out.println("getTopicAnswerById1");
        Long id = null;
        try{
           impl.getTopicAnswerById(id);
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
    public void testGetTopicAnswerById2() throws Exception {
        System.out.println("getTopicAnswerById2");
        try{
           impl.getTopicAnswerById(new Long(0)); 
        }catch(PersistenceException ex){
           return;
        }
        fail("Buscou topico inexistente");
    }
    
    /**
     * Test of removeUser method, of class UserManagementImpl.
     */
    @Test
    public void testGetTopicAnswerById3() throws Exception {
        System.out.println("getTopicAnswerById3");
        TopicAnswer topic = new TopicAnswer();
        topic.setAutor(new User("Joao", "joao@gmail.com", "senha", 'j'));
        topic.setDataResposta(LocalDate.now());
        topic.setMensagem(new Topic());
        topic.setIdMensagemResposta(Long.MAX_VALUE);
        topic.setTxtMensagem("txt");
        try{
           impl.registerTopicAnswer(topic);
           impl.getTopicAnswerById(new Long(4)); 
           fail("Buscou topico inexistente");
        }catch(PersistenceException ex){
           return;
        }
    }
    
    /**
     * Test of removeUser method, of class UserManagementImpl.
     */
    @Test
    public void testGetTopicAnswerById4() throws Exception {
        System.out.println("getTopicAnswerById4");
        TopicAnswer topic = new TopicAnswer();
        topic.setAutor(new User("Joao", "joao@gmail.com", "senha", 'j'));
        topic.setDataResposta(LocalDate.now());
        topic.setMensagem(new Topic());
        topic.setIdMensagemResposta(Long.MAX_VALUE);
        try{
           impl.registerTopicAnswer(topic);
           assertTrue(impl.getTopicAnswerById(topic.getIdMensagemResposta())==topic);
        }catch(PersistenceException|BusinessException ex){
           fail("Erro ao obter o topico");
        }
    }

    /**
     * Test of getQuestionsByUser method, of class TopicAnswerManagementImpl.
     */
    @Test
    public void testGetAnswersTopic1() throws Exception {
        System.out.println("getAnswersTopic1");
        Long id = null;
        try{
            impl.getTopicAnswerById(id);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("ID não pode ser nulo"));
        }
    }
    
    /**
     * Test of getQuestionsByUser method, of class TopicAnswerManagementImpl.
     */
    @Test
    public void testGetAnswersTopic2() throws Exception {
        System.out.println("getAnswersTopic2");
        TopicAnswer topic = new TopicAnswer();
        topic.setAutor(new User("Joao", "joao@gmail.com", "senha", 'j'));
        topic.setDataResposta(LocalDate.now());
        topic.setMensagem(new Topic());
        topic.setIdMensagemResposta(Long.MAX_VALUE);
        topic.setTxtMensagem("txt");
        impl.registerTopicAnswer(topic);
        try{
           List list = impl.getAnswersTopic(new Long(0));
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
     * Test of getQuestionsByUser method, of class TopicAnswerManagementImpl.
     */
    @Test
    public void testGetAnswersTopic3() throws Exception {
        System.out.println("getAnswersTopic3");
        User user = new User(new Long(0), "Joao", "joao@gmail.com", "senha", 'j');
        TopicAnswer topic = new TopicAnswer();
        topic.setAutor(user);
        topic.setDataResposta(LocalDate.now());
        topic.setMensagem(new Topic());
        topic.setIdMensagemResposta(Long.MAX_VALUE);
        topic.setTxtMensagem("txt");
        impl.registerTopicAnswer(topic);
        
        TopicAnswer topic2 = new TopicAnswer();
        topic2.setAutor(user);
        topic2.setDataResposta(LocalDate.now());
        topic2.setMensagem(new Topic());
        topic2.setIdMensagemResposta(Long.MAX_VALUE);
        topic2.setTxtMensagem("txt");
        impl.registerTopicAnswer(topic2);
        try{
           List list = impl.getAnswersTopic(new Long(0));
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
     * Test of getQuestionsByUser method, of class TopicAnswerManagementImpl.
     */
    @Test
    public void testGetAnswersTopic4() throws Exception {
        System.out.println("getAnswersTopic4");
        User user = new User(new Long(1), "Joao", "joao@gmail.com", "senha", 'j');
        
        TopicAnswer topic = new TopicAnswer();
        topic.setAutor(user);
        topic.setDataResposta(LocalDate.now());
        topic.setMensagem(new Topic());
        topic.setIdMensagemResposta(Long.MAX_VALUE);
        topic.setTxtMensagem("txt");
        impl.registerTopicAnswer(topic);
        
        TopicAnswer topic2 = new TopicAnswer();
        topic2.setAutor(user);
        topic2.setDataResposta(LocalDate.now());
        topic2.setMensagem(new Topic());
        topic2.setIdMensagemResposta(Long.MAX_VALUE);
        topic2.setTxtMensagem("txt");
        impl.registerTopicAnswer(topic2);
        try{
           List list = impl.getAnswersTopic(new Long(0));
           if(!list.isEmpty()){
               fail("Lista errada ao retornar");
           }
           
        }catch(BusinessException | PersistenceException ex){
            fail("Erro :" + ex);
        }
    }
    
     
    /**
     * Test of getQuestionsByUser method, of class TopicAnswerManagementImpl.
     */
    @Test
    public void testGetAnswersTopic5() throws Exception {
        System.out.println("getAnswersTopic5");
        TopicAnswer topic = new TopicAnswer();
        topic.setAutor(new User(new Long(0), "Joao", "joao@gmail.com", "senha", 'j'));
        topic.setDataResposta(LocalDate.now());
        topic.setMensagem(new Topic());
        topic.setIdMensagemResposta(Long.MAX_VALUE);
        topic.setTxtMensagem("txt");
        impl.registerTopicAnswer(topic);
        
        TopicAnswer topic2 = new TopicAnswer();
        topic2.setAutor(new User(new Long(1), "Joao", "joao@gmail.com", "senha", 'j'));
        topic2.setDataResposta(LocalDate.now());
        topic2.setMensagem(new Topic());
        topic2.setIdMensagemResposta(Long.MAX_VALUE);
        topic2.setTxtMensagem("txt");
        impl.registerTopicAnswer(topic2);
        try{
           List list = impl.getAnswersTopic(new Long(0));
           if(list.size()!=1){
               fail("Lista errada ao retornar");
           }
           if(list.get(0)!=topic){
               fail("Objeto errado na lista");
           }
           
           list = impl.getAnswersTopic(new Long(1));
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
