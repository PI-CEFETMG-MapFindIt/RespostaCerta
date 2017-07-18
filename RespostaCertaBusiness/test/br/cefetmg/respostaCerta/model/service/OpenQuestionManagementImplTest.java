/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.model.service;

import br.cefetmg.respostaCerta.model.dao.OpenQuestionDAO;
import br.cefetmg.respostaCerta.model.dao.OpenQuestionDAOImpl;
import br.cefetmg.respostaCerta.model.domain.Module;
import br.cefetmg.respostaCerta.model.domain.Question;
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
public class OpenQuestionManagementImplTest {
    
    private static OpenQuestionDAO openQuestionDAO;
    private static OpenQuestionManagementImpl impl;
    
    public OpenQuestionManagementImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        openQuestionDAO = OpenQuestionDAOImpl.getInstance();
        impl = new OpenQuestionManagementImpl(openQuestionDAO);
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        List<Question> us;
        try {
            us = openQuestionDAO.listAll();
            for(Question u : us){
                openQuestionDAO.delete(u.getIdQuestao());
            }
        } catch (PersistenceException ex) {
            System.out.println("Erro!");
        }
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testRegisterOpenQuestion1() throws Exception {
        System.out.println("registerOpenQuestion1");
        Question question = null;
        try{
            impl.registerQuestion(question);
        }catch(BusinessException ex){
            assertTrue("Question não pode ser nulo".equals(ex.getMessage()));
            return;
        }
        fail("Aceitou question nula");
    }
    
    @Test
    public void testRegisterOpenQuestion2() throws Exception {
        System.out.println("registerOpenQuestion2");
        Question question = new Question();
        question.setCriador(null);
        question.setDataCriacao(LocalDate.now());
        question.setModulo(new Module(new Subject("dominio"),"modulo"));
        question.setEnunciadoQuestao("Enunciado");
        question.setTituloQuestao("Titulo");
        try{
            impl.registerQuestion(question);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("Criador não pode ser nulo"));
            return;
        }
        fail("Aceitou questao com criador nulo");
    }
    
    @Test
    public void testRegisterOpenQuestion3() throws Exception {
        System.out.println("registerOpenQuestion3");
        Question question = new Question();
        question.setCriador(new User("Joao", "joao@gmail.com", "senha", 'j'));
        question.setDataCriacao(null);
        question.setModulo(new Module(new Subject("dominio"), "modulo"));
        question.setEnunciadoQuestao("Enunciado");
        question.setTituloQuestao("Titulo");
        try{
            impl.registerQuestion(question);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("Data de criação não pode ser nulo"));
            return;
        }
        fail("Aceitou questao com data nula");
    }
    
    @Test
    public void testRegisterOpenQuestion4() throws Exception {
        System.out.println("registerOpenQuestion4");
        Question question = new Question();
        question.setCriador(new User("Joao", "joao@gmail.com", "senha", 'j'));
        question.setDataCriacao(LocalDate.now());
        question.setModulo(null);
        question.setEnunciadoQuestao("Enunciado");
        question.setTituloQuestao("Titulo");
        try{
            impl.registerQuestion(question);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("Modulo não pode ser nulo"));
            return;
        }
        fail("Aceitou questao com modulo nulo");
    }
    
    @Test
    public void testRegisterOpenQuestion5() throws Exception {
        System.out.println("registerOpenQuestion5");
        Question question = new Question();
        question.setCriador(new User("Joao", "joao@gmail.com", "senha", 'j'));
        question.setDataCriacao(LocalDate.now());
        question.setModulo(new Module(new Subject("dominio"),"modulo"));
        question.setEnunciadoQuestao(null);
        question.setTituloQuestao("Titulo");
        try{
            impl.registerQuestion(question);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("Enunciado não pode ser nulo"));
            return;
        }
        fail("Aceitou questao com enunciado nulo");
    }
    
    @Test
    public void testRegisterOpenQuestion6() throws Exception {
        System.out.println("registerOpenQuestion6");
        Question question = new Question();
        question.setCriador(new User("Joao", "joao@gmail.com", "senha", 'j'));
        question.setDataCriacao(LocalDate.now());
        question.setModulo(new Module(new Subject("dominio"), "modulo"));
        question.setEnunciadoQuestao("Enunciado");
        question.setTituloQuestao(null);
        try{
            impl.registerQuestion(question);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("Titulo não pode ser nulo"));
            return;
        }
        fail("Aceitou questao com titulo nulo");
    }
    
    /**
     * Test of registerUser method, of class UserManagementImpl.
     * @throws java.lang.Exception
     */
    @Test
    public void testUpdateOpenQuestion1() throws Exception {
        System.out.println("updateOpenQuestion1");
        Question open = null;
        try{
            impl.updateQuestion(new Long(0), open);
        }catch(BusinessException ex){
            System.out.println(ex);
            assertTrue(ex.getMessage().equals("Question não pode ser nulo"));
            return;
        }
        fail("Aceitou question nula");
    }
    
    @Test
    public void testUpdateOpenQuestion2() throws Exception {
        System.out.println("updateOpenQuestion2");
        Question question = new Question();
        question.setCriador(null);
        question.setDataCriacao(LocalDate.now());
        question.setModulo(new Module(new Subject("dominio"),"modulo"));
        question.setEnunciadoQuestao("Enunciado");
        question.setTituloQuestao("Titulo");
        try{
            impl.updateQuestion(new Long(0), question);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("Criador não pode ser nulo"));
            return;
        }
        fail("Aceitou question com criador nulo");
    }
    
    @Test
    public void testUpdateOpenQuestion3() throws Exception {
        System.out.println("updateOpenQuestion3");
        Question question = new Question();
        question.setCriador(new User("Joao", "joao@gmail.com", "senha", 'j'));
        question.setDataCriacao(null);
        question.setModulo(new Module(new Subject("dominio"), "modulo"));
        question.setEnunciadoQuestao("Enunciado");
        question.setTituloQuestao("Titulo");
        try{
            impl.updateQuestion(new Long(0), question);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("Data de criação não pode ser nulo"));
            return;
        }
        fail("Aceitou question com data nula");
    }
    
    @Test
    public void testUpdateOpenQuestion4() throws Exception {
        System.out.println("updateOpenQuestion4");
        Question question = new Question();
        question.setCriador(new User("Joao", "joao@gmail.com", "senha", 'j'));
        question.setDataCriacao(LocalDate.now());
        question.setModulo(null);
        question.setEnunciadoQuestao("Enunciado");
        question.setTituloQuestao("Titulo");
        try{
            impl.updateQuestion(new Long(0), question);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("Modulo não pode ser nulo"));
            return;
        }
        fail("Aceitou question com modulo nulo");
    }
    
    @Test
    public void testUpdateOpenQuestion5() throws Exception {
        System.out.println("updateOpenQuestion5");
        Question question = new Question();
        question.setCriador(new User("Joao", "joao@gmail.com", "senha", 'j'));
        question.setDataCriacao(LocalDate.now());
        question.setModulo(new Module(new Subject("dominio"), "modulo"));
        question.setEnunciadoQuestao(null);
        question.setTituloQuestao("Titulo");
        try{
            impl.updateQuestion(new Long(0), question);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("Enunciado não pode ser nulo"));
            return;
        }
        fail("Aceitou question com enunciado nulo");
    }
    
    @Test
    public void testUpdateOpenQuestion6() throws Exception {
        System.out.println("updateOpenQuestion6");
        Question question = new Question();
        question.setCriador(new User("Joao", "joao@gmail.com", "senha", 'j'));
        question.setDataCriacao(LocalDate.now());
        question.setModulo(new Module(new Subject("dominio"), "modulo"));
        question.setEnunciadoQuestao("Enunciado");
        question.setTituloQuestao(null);
        try{
            impl.updateQuestion(new Long(0), question);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("Titulo não pode ser nulo"));
            return;
        }
        fail("Aceitou question com titulo nulo");
    }
    
    
    
    @Test
    public void testUpdateOpenQuestion7() throws Exception {
        System.out.println("updateOpenQuestion7");
        Question question = new Question();
        question.setCriador(new User("Joao", "joao@gmail.com", "senha", 'j'));
        question.setDataCriacao(LocalDate.now());
        question.setModulo(new Module(new Subject("dominio"), "modulo"));
        question.setEnunciadoQuestao("Enunciado");
        question.setTituloQuestao("Titulo");
        try{
            impl.updateQuestion(null, question);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("Id não pode ser nulo"));
            return;
        }
        fail("Aceitou id nulo");
    }
   
   

   
    /**
     * Test of removeUser method, of class UserManagementImpl.
     */
    @Test
    public void testRemoveOpenQuestion1() throws Exception {
        System.out.println("removeOpenQuestion1");
        Long id = null;
        try{
           impl.removeQuestion(id); 
        }catch(BusinessException ex){
           assertEquals(ex.getMessage(), "Id não pode ser nulo");
           return;
        }
        fail("Aceitou id nulo");
    }
    

     /**
     * Test of removeUser method, of class UserManagementImpl.
     */
    @Test
    public void testGetOpenQuestionById1() throws Exception {
        System.out.println("getOpenQuestionById1");
        Long id = null;
        try{
           impl.getQuestionById(id);
        }catch(BusinessException ex){
           assertTrue(ex.getMessage().equals("Id não pode ser nulo"));
           return;
        }
        
        fail("Aceitou id nulo");
    } 
}
