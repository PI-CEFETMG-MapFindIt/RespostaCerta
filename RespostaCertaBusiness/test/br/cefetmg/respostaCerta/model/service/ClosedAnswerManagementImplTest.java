/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.model.service;

import br.cefetmg.respostaCerta.model.dao.ClosedAnswerDAO;
import br.cefetmg.respostaCerta.model.dao.ClosedAnswerDAOImpl;
import br.cefetmg.respostaCerta.model.domain.ClosedAnswer;
import br.cefetmg.respostaCerta.model.domain.ClosedQuestion;
import br.cefetmg.respostaCerta.model.domain.Module;
import br.cefetmg.respostaCerta.model.domain.Subject;
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
public class ClosedAnswerManagementImplTest {
    private static ClosedAnswerDAO closedAnswerDAO;
    private static ClosedAnswerManagementImpl impl;
    
    public ClosedAnswerManagementImplTest() {
        
    }
    
    @BeforeClass
    public static void setUpClass() {
        closedAnswerDAO = ClosedAnswerDAOImpl.getInstance();
        impl = new ClosedAnswerManagementImpl(closedAnswerDAO);
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        List<ClosedAnswer> us;
        try {
            us = closedAnswerDAO.listAll();
            for(ClosedAnswer a : us){
                closedAnswerDAO.delete(a.getIdResposta());
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
        System.out.println("registerClosedAnswer1");
        ClosedAnswer answer = null;
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
        System.out.println("registerClosedAnswer2");
        ClosedAnswer closedAnswer = new ClosedAnswer();
        closedAnswer.setAutor(null);
        closedAnswer.setDataResposta(LocalDate.now());
        try{
            impl.registerQuestionAnswer(closedAnswer);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("Autor não pode ser nulo"));
            return;
        }
        fail("Aceitou resposta com autor nulo");
    }
    
    @Test
    public void testRegisterQuestionAnswer3() throws Exception {
       System.out.println("registerClosedAnswer2");
        ClosedAnswer closedAnswer = new ClosedAnswer();
        closedAnswer.setAutor(new User("Joao", "jao", "senha", 'p'));
        closedAnswer.setDataResposta(null);
        try{
            impl.registerQuestionAnswer(closedAnswer);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("Data da resposta não pode ser nula"));
            return;
        }
        fail("Aceitou resposta com data nula");
    }
    @Test
    public void testRegisterQuestionAnswer4() throws Exception {
        System.out.println("registerClosedAnswer4");
        User us = new User("Joao", "joao@oi.com", "senha", 'p');
        Module m = new Module(new Subject("a"), "a");
        m.setIdModulo(new Long(0));
        us.setIdUsuario(new Long(0));
        ClosedQuestion q = new ClosedQuestion("a", "a", "a", "a", "a", 0, m, us, "enunciado", true, LocalDate.now(), "titulo", null, 'F');
        ClosedAnswer closed = new ClosedAnswer(0, us, q, LocalDate.now(), 'f', true);
        try{
            impl.registerQuestionAnswer(closed);
            assertEquals(closed, impl.getQuestionAnswerById(closed.getIdResposta()));
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
        ClosedAnswer closed = null;
        try{
            impl.updateQuestionAnswer(new Long(0), closed);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("A resposta não pode ser nula"));
            return;
        }
        fail("Aceitou resposta nula");
    }
    
    @Test
    public void testUpdateQuestionAnswer2() throws Exception {
        System.out.println("updateQuestionAnswer2");
        ClosedAnswer closedAnswer = new ClosedAnswer();
        closedAnswer.setAutor(new User("Joao", "jao", "senha", 'p'));
        closedAnswer.setDataResposta(null);
        try{
            impl.updateQuestionAnswer(new Long(0), closedAnswer);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("Data da resposta não pode ser nula"));
            return;
        }
        fail("Aceitou resposta com data nula");
    }
    
    @Test
    public void testUpdateQuestionAnswer3() throws Exception {
       System.out.println("updateQuestionAnswer3");
        ClosedAnswer closedAnswer = new ClosedAnswer();
        closedAnswer.setAutor(null);
        closedAnswer.setDataResposta(LocalDate.now());
        try{
            impl.updateQuestionAnswer(new Long(0), closedAnswer);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("Autor não pode ser nulo"));
            return;
        }
        fail("Aceitou usuario com autor nulo");
    }
    
    @Test
    public void testUpdateQuestionAnswer4() throws Exception {
        System.out.println("updateQuestionAnswer4");
        ClosedAnswer closedAnswer = new ClosedAnswer();
        closedAnswer.setAutor(new User("Joao", "jao", "senha", 'p'));
        closedAnswer.setDataResposta(LocalDate.now());
        try{
            impl.updateQuestionAnswer(null, closedAnswer);
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
    public void testUpdateQuestionAnswer5() throws Exception {
        System.out.println("updateQuestionAnswer5");
        ClosedAnswer closedAnswer = new ClosedAnswer();
        closedAnswer.setAutor(new User("Joao", "jao", "senha", 'p'));
        closedAnswer.setDataResposta(LocalDate.now());
        impl.registerQuestionAnswer(closedAnswer);
        ClosedAnswer closedAnswer2 = new ClosedAnswer();
        closedAnswer2.setAutor(new User("Maria", "maria@gmail.com", "senha", 'p'));
        closedAnswer2.setDataResposta(LocalDate.now());
        impl.updateQuestionAnswer(closedAnswer.getIdResposta(), closedAnswer2);
        assertTrue(impl.getQuestionAnswerById(closedAnswer.getIdResposta()).equals(closedAnswer2));
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
    public void testRemoveQuestionAnswer3() throws Exception {
        System.out.println("removeQuestionAnswer3");
        ClosedAnswer closedAnswer = new ClosedAnswer();
        closedAnswer.setAutor(new User("Joao", "jao", "senha", 'p'));
        closedAnswer.setDataResposta(LocalDate.now());
        try{
           impl.registerQuestionAnswer(closedAnswer);
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
        ClosedAnswer closedAnswer = new ClosedAnswer();
        closedAnswer.setAutor(new User("Joao", "jao", "senha", 'p'));
        closedAnswer.setDataResposta(LocalDate.now());
        try{
           impl.registerQuestionAnswer(closedAnswer);
           impl.removeQuestionAnswer(closedAnswer.getIdResposta());
        }catch(PersistenceException|BusinessException ex){
           fail("Erro ao remover");
        }
        try{
            impl.getQuestionAnswerById(closedAnswer.getIdResposta());
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
        ClosedAnswer closedAnswer = new ClosedAnswer();
        closedAnswer.setAutor(new User("Joao", "jao", "senha", 'p'));
        closedAnswer.setDataResposta(LocalDate.now());
        try{
           impl.registerQuestionAnswer(closedAnswer);
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
        ClosedAnswer closedAnswer = new ClosedAnswer();
        closedAnswer.setAutor(new User("Joao", "jao", "senha", 'p'));
        closedAnswer.setDataResposta(LocalDate.now());
        try{
           impl.registerQuestionAnswer(closedAnswer);
           assertTrue(impl.getQuestionAnswerById(closedAnswer.getIdResposta())==closedAnswer);
        }catch(PersistenceException|BusinessException ex){
           fail("Erro ao obter o usuario");
        }
    }
    
}
