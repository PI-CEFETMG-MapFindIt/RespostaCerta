/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.model.service;

import br.cefetmg.respostaCerta.model.dao.ClosedQuestionDAO;
import br.cefetmg.respostaCerta.model.dao.ClosedQuestionDAOImpl;
import br.cefetmg.respostaCerta.model.domain.ClosedQuestion;
import br.cefetmg.respostaCerta.model.domain.Module;
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
 * @author pedro
 */
public class ClosedQuestionManagementImplTest {
    private static ClosedQuestionDAO closedQuestionDAO;
    private static ClosedQuestionManagementImpl impl;
            
    public ClosedQuestionManagementImplTest() {

    }
    
    @BeforeClass
    public static void setUpClass() {
        closedQuestionDAO = ClosedQuestionDAOImpl.getInstance();
        impl = new ClosedQuestionManagementImpl(closedQuestionDAO);
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        List<ClosedQuestion> us;
        try {
            us = closedQuestionDAO.listAll();
            System.out.println(us);
            for(ClosedQuestion a : us){
                closedQuestionDAO.delete(a.getIdQuestao());
            }
        } catch (PersistenceException ex) {
            System.out.println(ex);
        }
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of registerQuestion method, of class ClosedQuestionManagementImpl.
     */
    @Test
    public void testRegisterQuestion1() throws Exception {
        System.out.println("RegisterQuestion1");
        ClosedQuestion question = null;
        try{
            impl.registerQuestion(question);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("Question não pode ser nulo"));
            return;
        }
        fail("Aceitou questão nula");
    }
    
    @Test
    public void testRegisterQuestion2() throws Exception {
        System.out.println("RegisterQuestion2");
        ClosedQuestion question = new ClosedQuestion();
        question.setDataCriacao(LocalDate.MIN);
        question.setModulo(new Module());
        question.setEnunciadoQuestao("");
        question.setTituloQuestao("");
        question.setAlt1("");
        question.setAlt2("");
        question.setAlt3("");
        question.setAlt4("");
        question.setAlt5("");
        try{
            impl.registerQuestion(question);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("Criador não pode ser nulo"));
            return;
        }
        fail("Aceitou criador nulo");
    }
    
    @Test
    public void testRegisterQuestion3() throws Exception {
        System.out.println("RegisterQuestion3");
        ClosedQuestion question = new ClosedQuestion();
        question.setCriador(new User());
        question.setModulo(new Module());
        question.setEnunciadoQuestao("");
        question.setTituloQuestao("");
        question.setAlt1("");
        question.setAlt2("");
        question.setAlt3("");
        question.setAlt4("");
        question.setAlt5("");
        try{
            impl.registerQuestion(question);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("Data de criação não pode ser nulo"));
            return;
        }
        fail("Aceitou data de criação nula");
    }
    
    @Test
    public void testRegisterQuestion4() throws Exception {
        System.out.println("RegisterQuestion4");
        ClosedQuestion question = new ClosedQuestion();
        question.setCriador(new User());
        question.setDataCriacao(LocalDate.MIN);
        question.setEnunciadoQuestao("");
        question.setTituloQuestao("");
        question.setAlt1("");
        question.setAlt2("");
        question.setAlt3("");
        question.setAlt4("");
        question.setAlt5("");
        
        try{
            impl.registerQuestion(question);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("Modulo não pode ser nulo"));
            return;
        }
        fail("Aceitou modulo nulo");
    }
    
    @Test
    public void testRegisterQuestion5() throws Exception {
        System.out.println("RegisterQuestion5");
        ClosedQuestion question = new ClosedQuestion();
        question.setCriador(new User());
        question.setDataCriacao(LocalDate.MIN);
        question.setModulo(new Module());
        question.setTituloQuestao("");
        question.setAlt1("");
        question.setAlt2("");
        question.setAlt3("");
        question.setAlt4("");
        question.setAlt5("");
        try{
            impl.registerQuestion(question);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("Enunciado não pode ser nulo"));
            return;
        }
        fail("Aceitou enunciado nulo");
    }
    
    @Test
    public void testRegisterQuestion6() throws Exception {
        System.out.println("RegisterQuestion6");
        ClosedQuestion question = new ClosedQuestion();
        question.setCriador(new User());
        question.setDataCriacao(LocalDate.MIN);
        question.setModulo(new Module());
        question.setEnunciadoQuestao("");
        question.setAlt1("");
        question.setAlt2("");
        question.setAlt3("");
        question.setAlt4("");
        question.setAlt5("");
        try{
            impl.registerQuestion(question);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("Titulo não pode ser nulo"));
            return;
        }
        fail("Aceitou título nulo");
    }
    
    @Test
    public void testRegisterQuestion7() throws Exception {
        System.out.println("RegisterQuestion7");
        ClosedQuestion question = new ClosedQuestion();
        question.setCriador(new User());
        question.setDataCriacao(LocalDate.MIN);
        question.setModulo(new Module());
        question.setEnunciadoQuestao("");
        question.setTituloQuestao("");
        question.setAlt2("");
        question.setAlt3("");
        question.setAlt4("");
        question.setAlt5("");
        try{
            impl.registerQuestion(question);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("Alternativas não podem ser nulas"));
            return;
        }
        fail("Aceitou alternativa nula");
    }
    
    @Test
    public void testRegisterQuestion8() throws Exception {
        System.out.println("RegisterQuestion8");
        ClosedQuestion question = new ClosedQuestion();
        question.setCriador(new User());
        question.setDataCriacao(LocalDate.MIN);
        question.setModulo(new Module());
        question.setEnunciadoQuestao("");
        question.setTituloQuestao("");
        question.setAlt1("");
        question.setAlt3("");
        question.setAlt4("");
        question.setAlt5("");
        try{
            impl.registerQuestion(question);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("Alternativas não podem ser nulas"));
            return;
        }
        fail("Aceitou alternativa nula");
    }
    
    @Test
    public void testRegisterQuestion9() throws Exception {
        System.out.println("RegisterQuestion9");
        ClosedQuestion question = new ClosedQuestion();
        question.setCriador(new User());
        question.setDataCriacao(LocalDate.MIN);
        question.setModulo(new Module());
        question.setEnunciadoQuestao("");
        question.setTituloQuestao("");
        question.setAlt1("");
        question.setAlt2("");
        question.setAlt4("");
        question.setAlt5("");
        try{
            impl.registerQuestion(question);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("Alternativas não podem ser nulas"));
            return;
        }
        fail("Aceitou alternativa nula");
    }
    
    @Test
    public void testRegisterQuestion10() throws Exception {
        System.out.println("RegisterQuestion10");
        ClosedQuestion question = new ClosedQuestion();
        question.setCriador(new User());
        question.setDataCriacao(LocalDate.MIN);
        question.setModulo(new Module());
        question.setEnunciadoQuestao("");
        question.setTituloQuestao("");
        question.setAlt1("");
        question.setAlt2("");
        question.setAlt3("");
        question.setAlt5("");
        try{
            impl.registerQuestion(question);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("Alternativas não podem ser nulas"));
            return;
        }
        fail("Aceitou alternativa nula");
    }
    
    @Test
    public void testRegisterQuestion11() throws Exception {
        System.out.println("RegisterQuestion11");
        ClosedQuestion question = new ClosedQuestion();
        question.setCriador(new User());
        question.setDataCriacao(LocalDate.MIN);
        question.setModulo(new Module());
        question.setEnunciadoQuestao("");
        question.setTituloQuestao("");
        question.setAlt1("");
        question.setAlt2("");
        question.setAlt3("");
        question.setAlt4("");
        try{
            impl.registerQuestion(question);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("Alternativas não podem ser nulas"));
            return;
        }
        fail("Aceitou alternativa nula");
    }
    
    @Test
    public void testRegisterQuestion12() throws Exception {
        System.out.println("RegisterQuestion12");
        ClosedQuestion question = new ClosedQuestion("1", "2", "3", "4", "5", 1, new Module(), 
        new User(), "Test", true, LocalDate.MIN, "Test", null, 'F');
        
        impl.registerQuestion(question);
        assertTrue(impl.getQuestionById(question.getIdQuestao()) == question);
    }

    /**
     * Test of updateQuestion method, of class ClosedQuestionManagementImpl.
     */
    @Test
    public void testUpdateQuestion1() throws Exception {
        System.out.println("UpdateQuestion1");
        ClosedQuestion question = null;
        try{
            impl.updateQuestion(new Long(1),question);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("Question não pode ser nulo"));
            return;
        }
        fail("Aceitou questão nula");
    }
    
    @Test
    public void testUpdateQuestion2() throws Exception {
        System.out.println("UpdateQuestion2");
        ClosedQuestion question = new ClosedQuestion();
        question.setDataCriacao(LocalDate.MIN);
        question.setModulo(new Module());
        question.setEnunciadoQuestao("");
        question.setTituloQuestao("");
        question.setAlt1("");
        question.setAlt2("");
        question.setAlt3("");
        question.setAlt4("");
        question.setAlt5("");
        try{
            impl.updateQuestion(new Long(1),question);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("Criador não pode ser nulo"));
            return;
        }
        fail("Aceitou criador nulo");
    }
    
    @Test
    public void testUpdateQuestion3() throws Exception {
        System.out.println("UpdateQuestion3");
        ClosedQuestion question = new ClosedQuestion();
        question.setCriador(new User());
        question.setModulo(new Module());
        question.setEnunciadoQuestao("");
        question.setTituloQuestao("");
        question.setAlt1("");
        question.setAlt2("");
        question.setAlt3("");
        question.setAlt4("");
        question.setAlt5("");
        try{
            impl.updateQuestion(new Long(1),question);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("Data de criação não pode ser nulo"));
            return;
        }
        fail("Aceitou data de criação nula");
    }  
    
    @Test
    public void testUpdateQuestion4() throws Exception {
        System.out.println("UpdateQuestion4");
        ClosedQuestion question = new ClosedQuestion();
        question.setCriador(new User());
        question.setDataCriacao(LocalDate.MIN);
        question.setEnunciadoQuestao("");
        question.setTituloQuestao("");
        question.setAlt1("");
        question.setAlt2("");
        question.setAlt3("");
        question.setAlt4("");
        question.setAlt5("");
        
        try{
            impl.updateQuestion(new Long(1),question);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("Modulo não pode ser nulo"));
            return;
        }
        fail("Aceitou modulo nulo");
    }
    
    @Test
    public void testUpdateQuestion5() throws Exception {
        System.out.println("UpdateQuestion5");
        ClosedQuestion question = new ClosedQuestion();
        question.setCriador(new User());
        question.setDataCriacao(LocalDate.MIN);
        question.setModulo(new Module());
        question.setTituloQuestao("");
        question.setAlt1("");
        question.setAlt2("");
        question.setAlt3("");
        question.setAlt4("");
        question.setAlt5("");
        try{
            impl.updateQuestion(new Long(1),question);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("Enunciado não pode ser nulo"));
            return;
        }
        fail("Aceitou enunciado nulo");
    }    
    
    @Test
    public void testUpdateQuestion6() throws Exception {
        System.out.println("UpdateQuestion6");
        ClosedQuestion question = new ClosedQuestion();
        question.setCriador(new User());
        question.setDataCriacao(LocalDate.MIN);
        question.setModulo(new Module());
        question.setEnunciadoQuestao("");
        question.setAlt1("");
        question.setAlt2("");
        question.setAlt3("");
        question.setAlt4("");
        question.setAlt5("");
        try{
            impl.updateQuestion(new Long(1),question);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("Titulo não pode ser nulo"));
            return;
        }
        fail("Aceitou título nulo");
    } 
    
    @Test
    public void testUpdateQuestion7() throws Exception {
        System.out.println("UpdateQuestion7");
        ClosedQuestion question = new ClosedQuestion();
        question.setCriador(new User());
        question.setDataCriacao(LocalDate.MIN);
        question.setModulo(new Module());
        question.setEnunciadoQuestao("");
        question.setTituloQuestao("");
        question.setAlt2("");
        question.setAlt3("");
        question.setAlt4("");
        question.setAlt5("");
        try{
            impl.updateQuestion(new Long(1),question);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("Alternativas não podem ser nulas"));
            return;
        }
        fail("Aceitou alternativa nula");
    }  
    
    @Test
    public void testUpdateQuestion8() throws Exception {
        System.out.println("UpdateQuestion8");
        ClosedQuestion question = new ClosedQuestion();
        question.setCriador(new User());
        question.setDataCriacao(LocalDate.MIN);
        question.setModulo(new Module());
        question.setEnunciadoQuestao("");
        question.setTituloQuestao("");
        question.setAlt1("");
        question.setAlt3("");
        question.setAlt4("");
        question.setAlt5("");
        try{
            impl.updateQuestion(new Long(1),question);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("Alternativas não podem ser nulas"));
            return;
        }
        fail("Aceitou alternativa nula");
    }

    @Test
    public void testUpdateQuestion9() throws Exception {
        System.out.println("UpdateQuestion9");
        ClosedQuestion question = new ClosedQuestion();
        question.setCriador(new User());
        question.setDataCriacao(LocalDate.MIN);
        question.setModulo(new Module());
        question.setEnunciadoQuestao("");
        question.setTituloQuestao("");
        question.setAlt1("");
        question.setAlt2("");
        question.setAlt4("");
        question.setAlt5("");
        try{
            impl.updateQuestion(new Long(1),question);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("Alternativas não podem ser nulas"));
            return;
        }
        fail("Aceitou alternativa nula");
    }    
    
    @Test
    public void testUpdateQuestion10() throws Exception {
        System.out.println("UpdateQuestion10");
        ClosedQuestion question = new ClosedQuestion();
        question.setCriador(new User());
        question.setDataCriacao(LocalDate.MIN);
        question.setModulo(new Module());
        question.setEnunciadoQuestao("");
        question.setTituloQuestao("");
        question.setAlt1("");
        question.setAlt2("");
        question.setAlt3("");
        question.setAlt5("");
        try{
            impl.updateQuestion(new Long(1),question);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("Alternativas não podem ser nulas"));
            return;
        }
        fail("Aceitou alternativa nula");
    } 
    
    @Test
    public void testUpdateQuestion11() throws Exception {
        System.out.println("UpdateQuestion11");
        ClosedQuestion question = new ClosedQuestion();
        question.setCriador(new User());
        question.setDataCriacao(LocalDate.MIN);
        question.setModulo(new Module());
        question.setEnunciadoQuestao("");
        question.setTituloQuestao("");
        question.setAlt1("");
        question.setAlt2("");
        question.setAlt3("");
        question.setAlt4("");
        try{
            impl.updateQuestion(new Long(1),question);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("Alternativas não podem ser nulas"));
            return;
        }
        fail("Aceitou alternativa nula");
    }
    
    @Test
    public void testUpdateQuestion12() throws Exception {
        System.out.println("UpdateQuestion12");
        ClosedQuestion question = new ClosedQuestion();
        question.setCriador(new User());
        question.setDataCriacao(LocalDate.MIN);
        question.setModulo(new Module());
        question.setEnunciadoQuestao("");
        question.setTituloQuestao("");
        question.setAlt1("");
        question.setAlt2("");
        question.setAlt3("");
        question.setAlt4("");
        question.setAlt5("");
        try{
            impl.updateQuestion(null,question);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("Id não pode ser nulo"));
            return;
        }
        fail("Aceitou Id nulo");
    }
    
    @Test
    public void testUpdateQuestion13() throws Exception {
        System.out.println("UpdateQuestion13");
        ClosedQuestion question = new ClosedQuestion("1", "2", "3", "4", "5", 1, new Module(), 
        new User(), "Test", true, LocalDate.MIN, "Test", null, 'F');
      
        impl.registerQuestion(question);
        question.setCorreta(2);
        impl.updateQuestion(new Long(1), question);
        assertTrue(impl.getQuestionById(new Long(1)).getCorreta() == 2);        
    }

    /**
     * Test of removeQuestion method, of class ClosedQuestionManagementImpl.
     */
    @Test
    public void testRemoveQuestion() throws Exception {
        System.out.println("removeQuestion");
        try{
            impl.removeQuestion(null);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("Id não pode ser nulo"));
            return;
        }
        fail("Aceitou Id nulo");
    }

    /**
     * Test of getQuestionById method, of class ClosedQuestionManagementImpl.
     */
    @Test
    public void testGetQuestionById() throws Exception {
        System.out.println("GetQuestionById");
        try{
            impl.getQuestionById(null);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("Id não pode ser nulo"));
            return;
        }
        fail("Aceitou Id nulo");
    }

    /**
     * Test of getQuestionsByUser method, of class ClosedQuestionManagementImpl.
     */
    @Test
    public void testGetQuestionsByUser() throws Exception {
        System.out.println("GetQuestionByUser");
        try{
            impl.getQuestionsByUser(null);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("Id não pode ser nulo"));
            return;
        }
        fail("Aceitou Id nulo");
    }   
}
