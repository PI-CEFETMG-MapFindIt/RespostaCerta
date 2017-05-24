/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.model.service;

import br.cefetmg.respostaCerta.model.dao.OpenAnswerDAO;
import br.cefetmg.respostaCerta.model.dao.OpenAnswerDAOImpl;
import br.cefetmg.respostaCerta.model.dao.UserDAO;
import br.cefetmg.respostaCerta.model.dao.UserDAOImpl;
import br.cefetmg.respostaCerta.model.domain.OpenAnswer;
import br.cefetmg.respostaCerta.model.domain.Question;
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
public class OpenAnswerManagementImplTest {
    private static OpenAnswerDAO openAnswerDAO;
    private static OpenAnswerManagementImpl impl;
    
    public OpenAnswerManagementImplTest() {
        
    }
    
    @BeforeClass
    public static void setUpClass() {
        openAnswerDAO = OpenAnswerDAOImpl.getInstance();
        impl = new OpenAnswerManagementImpl(openAnswerDAO);
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        List<OpenAnswer> us;
        try {
            us = openAnswerDAO.listAll();
            for(OpenAnswer a : us){
                openAnswerDAO.delete(a.getIdResposta());
            }
        } catch (PersistenceException ex) {
            System.out.println("Erro!");
        }
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testRegisterQuestionAnswer1() throws Exception {
        System.out.println("registerOpenAnswer1");
        OpenAnswer answer = null;
        try{
            impl.registerQuestionAnswer(answer);
        }catch(BusinessException ex){
            assertTrue("A resposta não pode ser nula".equals(ex.getMessage()));
            return;
        }
        fail("Aceitou resposta nula");
    }
    
    @Test
    public void testRegisterQuestionAnswer2() throws Exception {
        System.out.println("registerOpenAnswer2");
        OpenAnswer openAnswer = new OpenAnswer();
        openAnswer.setAutor(null);
        openAnswer.setDataResposta(LocalDate.now());
        try{
            impl.registerQuestionAnswer(openAnswer);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("Autor não pode ser nulo"));
            return;
        }
        fail("Aceitou resposta com autor nulo");
    }
    
    @Test
    public void testRegisterQuestionAnswer3() throws Exception {
       System.out.println("registerOpenAnswer2");
        OpenAnswer openAnswer = new OpenAnswer();
        openAnswer.setAutor(new User("Joao", "jao", "senha", 'p'));
        openAnswer.setDataResposta(null);
        try{
            impl.registerQuestionAnswer(openAnswer);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("Data da resposta não pode ser nula"));
            return;
        }
        fail("Aceitou resposta com data nula");
    }
    @Test
    public void testRegisterQuestionAnswer4() throws Exception {
        System.out.println("registerOpenAnswer4");
        User us = new User("Joao", "joao@oi.com", "senha", 'p');
        Question q = new Question(null, null, null, new Long(0), "enunciado", true, LocalDate.now(), "titulo", null);
        OpenAnswer open = new OpenAnswer("a", us, q, LocalDate.now(), 'f', true);
        try{
            impl.registerQuestionAnswer(open);
            assertEquals(open, impl.getQuestionAnswerById(open.getIdResposta()));
        }catch(BusinessException|PersistenceException ex){
            fail("Erro ao inserir");
        }
        
    }
    /**
     * Test of registerUser method, of class UserManagementImpl.
     */
    @Test
    public void testUpdateQuestionAnswer1() throws Exception {
        System.out.println("updateQuestionAnswer1");
        OpenAnswer open = null;
        try{
            impl.updateQuestionAnswer(new Long(0), open);
        }catch(BusinessException ex){
            System.out.println(ex);
            assertTrue(ex.getMessage().equals("A reposta não pode ser nula"));
            return;
        }
        fail("Aceitou resposta nula");
    }
    
    @Test
    public void testUpdateQuestionAnswer2() throws Exception {
        System.out.println("updateQuestionAnswer2");
        OpenAnswer openAnswer = new OpenAnswer();
        openAnswer.setAutor(new User("Joao", "jao", "senha", 'p'));
        openAnswer.setDataResposta(null);
        try{
            impl.updateQuestionAnswer(new Long(0), openAnswer);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("Data da resposta não pode ser nula"));
            return;
        }
        fail("Aceitou resposta com data nula");
    }
    
    @Test
    public void testUpdateQuestionAnswer3() throws Exception {
       System.out.println("updateQuestionAnswer3");
        OpenAnswer openAnswer = new OpenAnswer();
        openAnswer.setAutor(null);
        openAnswer.setDataResposta(LocalDate.now());
        try{
            impl.updateQuestionAnswer(new Long(0), openAnswer);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("Autor não pode ser nulo"));
            return;
        }
        fail("Aceitou usuario com autor nulo");
    }
    
    @Test
    public void testUpdateQuestionAnswer4() throws Exception {
        System.out.println("updateQuestionAnswer4");
        OpenAnswer openAnswer = new OpenAnswer();
        openAnswer.setAutor(new User("Joao", "jao", "senha", 'p'));
        openAnswer.setDataResposta(LocalDate.now());
        try{
            impl.updateQuestionAnswer(null, openAnswer);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("ID não pode ser nulo"));
            return;
        }
        fail("Aceitou id nulo");
    }
   
    /**
     * Test of updateUser method, of class UserManagementImpl.
     */
    @Test
    public void testUpdateUser5() throws Exception {
        System.out.println("updateQuestionAnswer5");
        OpenAnswer openAnswer = new OpenAnswer();
        openAnswer.setAutor(new User("Joao", "jao", "senha", 'p'));
        openAnswer.setDataResposta(LocalDate.now());
        impl.registerQuestionAnswer(openAnswer);
        OpenAnswer openAnswer2 = new OpenAnswer();
        openAnswer2.setAutor(new User("Maria", "maria@gmail.com", "senha", 'p'));
        openAnswer2.setDataResposta(LocalDate.now());
        impl.updateQuestionAnswer(openAnswer.getIdResposta(), openAnswer2);
        assertTrue(impl.getQuestionAnswerById(openAnswer.getIdResposta()).equals(openAnswer2));
    }

   
    /**
     * Test of removeUser method, of class UserManagementImpl.
     */
    @Test
    public void testRemoveQuestionAnswer1() throws Exception {
        System.out.println("removeQuestionAnswer1");
        Long id = null;
        try{
           impl.removeQuestionAnswer(id); 
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
    public void testRemoveQuestionAnswer2() throws Exception {
        System.out.println("removeQuestionAnswer2");
        try{
           impl.removeQuestionAnswer(new Long(0)); 
        }catch(PersistenceException ex){
           return;
        }
        fail("Removeu resposta inexistente");
    }
    
    /**
     * Test of removeUser method, of class UserManagementImpl.
     */
    @Test
    public void testRemoveUser3() throws Exception {
        System.out.println("removeQuestionAnswer3");
        OpenAnswer openAnswer = new OpenAnswer();
        openAnswer.setAutor(new User("Joao", "jao", "senha", 'p'));
        openAnswer.setDataResposta(LocalDate.now());
        try{
           impl.registerQuestionAnswer(openAnswer);
           impl.removeQuestionAnswer(new Long(3)); 
        }catch(PersistenceException ex){
           return;
        }
        fail("Removeu resposta inexistente");
    }
    
    /**
     * Test of removeUser method, of class UserManagementImpl.
     */
    @Test
    public void testRemoveQuestionAnswer4() throws Exception {
        System.out.println("removeQuestionAnswer4");
        OpenAnswer openAnswer = new OpenAnswer();
        openAnswer.setAutor(new User("Joao", "jao", "senha", 'p'));
        openAnswer.setDataResposta(LocalDate.now());
        try{
           impl.registerQuestionAnswer(openAnswer);
           impl.removeQuestionAnswer(openAnswer.getIdResposta());
        }catch(PersistenceException|BusinessException ex){
           fail("Erro ao remover");
        }
        try{
            impl.getQuestionAnswerById(openAnswer.getIdResposta());
        }catch(Exception ex){
            return;
        }
        fail("Não removeu a resposta");
    }

     /**
     * Test of removeUser method, of class UserManagementImpl.
     */
    @Test
    public void testGetQuestionAnswerById1() throws Exception {
        System.out.println("getQuestionAnswerById1");
        Long id = null;
        try{
           impl.getQuestionAnswerById(id);
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
    public void testGetQuestionAnswerById2() throws Exception {
        System.out.println("getQuestionAnswerById2");
        try{
           impl.getQuestionAnswerById(new Long(0)); 
        }catch(PersistenceException ex){
           return;
        }
        fail("Buscou resposta inexistente");
    }
    
    /**
     * Test of removeUser method, of class UserManagementImpl.
     */
    @Test
    public void testGetQuestionAnswerById3() throws Exception {
        System.out.println("getQuestionAnswerById3");
        OpenAnswer openAnswer = new OpenAnswer();
        openAnswer.setAutor(new User("Joao", "jao", "senha", 'p'));
        openAnswer.setDataResposta(LocalDate.now());
        try{
           impl.registerQuestionAnswer(openAnswer);
           impl.removeQuestionAnswer(new Long(4)); 
        }catch(PersistenceException ex){
           return;
        }
        fail("Buscou resposta inexistente");
    }
    
    /**
     * Test of removeUser method, of class UserManagementImpl.
     */
    @Test
    public void testGetQuestionAnswerById4() throws Exception {
        System.out.println("getQuestionAnswerById4");
        OpenAnswer openAnswer = new OpenAnswer();
        openAnswer.setAutor(new User("Joao", "jao", "senha", 'p'));
        openAnswer.setDataResposta(LocalDate.now());
        try{
           impl.registerQuestionAnswer(openAnswer);
           assertTrue(impl.getQuestionAnswerById(openAnswer.getIdResposta())==openAnswer);
        }catch(PersistenceException|BusinessException ex){
           fail("Erro ao obter o usuario");
        }
    }
    
}
