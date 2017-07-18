/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.model.service;

import br.cefetmg.respostaCerta.model.dao.ClosedAnswerDAO;
import br.cefetmg.respostaCerta.model.dao.ClosedAnswerDAOImpl;
import br.cefetmg.respostaCerta.model.dao.ClosedQuestionDAO;
import br.cefetmg.respostaCerta.model.dao.ClosedQuestionDAOImpl;
import br.cefetmg.respostaCerta.model.dao.ModuleDAO;
import br.cefetmg.respostaCerta.model.dao.ModuleDAOImpl;
import br.cefetmg.respostaCerta.model.dao.SubjectDAO;
import br.cefetmg.respostaCerta.model.dao.SubjectDAOImpl;
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
public class PerformanceManagementImplTest {
    private static PerformanceManagementImpl impl;
    private static ClosedAnswerDAO answer; 
    private static ModuleDAO module; 
    private static SubjectDAO subject; 
    private static ClosedQuestionDAO closed;
    
    public PerformanceManagementImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        answer = ClosedAnswerDAOImpl.getInstance();
        module = ModuleDAOImpl.getInstance();
        subject = SubjectDAOImpl.getInstance();
        closed = ClosedQuestionDAOImpl.getInstance();
        impl = new PerformanceManagementImpl(answer, module, subject, closed);
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        List<ClosedAnswer> cl;
        try {
            cl = answer.listAll();
            for(ClosedAnswer u : cl){
                answer.delete(u.getIdResposta());
            }
        } catch (PersistenceException ex) {
            System.out.println("Erro!");
        }
        List<Module> mo;
        try {
            mo = module.listAll();
            for(Module u : mo){
                module.delete(u.getIdModulo());
            }
        } catch (PersistenceException ex) {
            System.out.println("Erro!");
        }
        List<Subject> su;
        try {
            su = subject.listAll();
            for(Subject u : su){
                subject.delete(u.getIdDominio());
            }
        } catch (PersistenceException ex) {
            System.out.println("Erro!");
        }
        List<ClosedQuestion> cq;
        try {
            cq = closed.listAll();
            for(ClosedQuestion u : cq){
                closed.delete(u.getIdQuestao());
            }
        } catch (PersistenceException ex) {
            System.out.println("Erro!");
        }
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of calculateErrors method, of class PerformanceManagementImpl.
     */
    @Test
    public void testCalculateErrors1() throws Exception {
        System.out.println("calculateErrors1");
        User user = null;
        try{
            impl.calculateErrors(user);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("Usuario não pode ser nulo"));
        }
    }
    
    /**
     * Test of calculateErrors method, of class PerformanceManagementImpl.
     */
    @Test
    public void testCalculateErrors2() throws Exception {
        System.out.println("calculateErrors2");
        User user = new User("Joao", "joao@gmail.com", "senha", 'j');
        user.setIdUsuario(null);
        try{
            impl.calculateErrors(user);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("ID do usuario não pode ser nulo"));
        }
    }
    
    /**
     * Test of calculateErrors method, of class PerformanceManagementImpl.
     */
    @Test
    public void testCalculateErrors3() throws Exception {
        System.out.println("calculateErrors3");
        User user = new User("Joao", null, "senha", 'j');
        user.setIdUsuario(new Long(0));
        try{
            impl.calculateErrors(user);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("Login do usuario não pode ser nulo"));
        }
    }
    
    /**
     * Test of calculateErrors method, of class PerformanceManagementImpl.
     */
    @Test
    public void testCalculateErrors4() throws Exception {
        System.out.println("calculateErrors4");
        User user = new User(null, "joao@gmail.com", "senha", 'j');
        user.setIdUsuario(new Long(0));
        try{
            impl.calculateErrors(user);
        }catch(BusinessException ex){
            System.out.println(ex);
            assertTrue(ex.getMessage().equals("Nome do usuario não pode ser nulo"));
        }
    }
    
    /**
     * Test of calculateErrors method, of class PerformanceManagementImpl.
     */
    @Test
    public void testCalculateErrors5() throws Exception {
        System.out.println("calculateErrors5");
        User user = new User("Joao", "joao@gmail.com", null, 'j');
        user.setIdUsuario(new Long(0));
        try{
            impl.calculateErrors(user);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("Senha do usuario não pode ser nulo"));
        }
    }
    
    
  
    /**
     * Test of calculateErrors method, of class PerformanceManagementImpl.
     */
    @Test
    public void testCalculateErrors6() throws Exception {
        System.out.println("calculateErrors9");
        User user = new User("Joao", "joao@gmail.com", "senha", 'j');
        user.setIdUsuario(new Long(0));
        try{
            impl.calculateErrors(user);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("O usuario não respondeu nenhuma questão"));
        }
    }
    
    /**
     * Test of calculateErrors method, of class PerformanceManagementImpl.
     */
    @Test
    public void testCalculateErrorsByModule1() throws Exception {
        System.out.println("calculateErrorsByModule1");
        User user = null;
        Module m = new Module(new Subject(  "subject"),   "modulo");
        try{
            impl.calculateErrorsByModule(user, m);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("Usuario não pode ser nulo"));
        }
    }
    
    /**
     * Test of calculateErrors method, of class PerformanceManagementImpl.
     */
    @Test
    public void testCalculateErrorsByModule2() throws Exception {
        System.out.println("calculateErrorsByModule2");
        User user = new User("Joao", "joao@gmail.com", "senha", 'j');
        user.setIdUsuario(null);
        Module m = new Module(new Subject(  "subject"),   "modulo");
        try{
            impl.calculateErrorsByModule(user, m);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("ID do usuario não pode ser nulo"));
        }
    }
    
    /**
     * Test of calculateErrors method, of class PerformanceManagementImpl.
     */
    @Test
    public void testCalculateErrorsByModule3() throws Exception {
        System.out.println("calculateErrorsByModule3");
        User user = new User("Joao", null, "senha", 'j');
        user.setIdUsuario(new Long(0));
        Module m = new Module(new Subject(  "subject"),   "modulo");
        try{
            impl.calculateErrorsByModule(user, m);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("Login do usuario não pode ser nulo"));
        }
    }
    
    /**
     * Test of calculateErrors method, of class PerformanceManagementImpl.
     */
    @Test
    public void testCalculateErrorsByModule4() throws Exception {
        System.out.println("calculateErrorsByModule4");
        User user = new User(null, "joao@gmail.com", "senha", 'j');
        user.setIdUsuario(new Long(0));
        Module m = new Module(new Subject(  "subject"),   "modulo");
        try{
            impl.calculateErrorsByModule(user, m);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("Nome do usuario não pode ser nulo"));
        }
    }
    
    /**
     * Test of calculateErrors method, of class PerformanceManagementImpl.
     */
    @Test
    public void testCalculateErrorsByModule5() throws Exception {
        System.out.println("calculateErrorsByModule5");
        User user = new User("Joao", "joao@gmail.com", null, 'j');
        user.setIdUsuario(new Long(0));
        Module m = new Module(new Subject(  "subject"),   "modulo");
        try{
            impl.calculateErrorsByModule(user, m);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("Senha do usuario não pode ser nulo"));
        }
    }
    
    /**
     * Test of calculateErrors method, of class PerformanceManagementImpl.
     */
    @Test
    public void testCalculateErrorsByModule6() throws Exception {
        System.out.println("calculateErrorsByModule6");
        User user = new User("Joao", "joao@gmail.com", "senha", 'j');
        user.setIdUsuario(new Long(0));
        Module m = null;
        try{
            impl.calculateErrorsByModule(user, m);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("Modulo não pode ser nulo"));
        }
    }
    
     /**
     * Test of calculateErrors method, of class PerformanceManagementImpl.
     */
    @Test
    public void testCalculateErrorsByModule7() throws Exception {
        System.out.println("calculateErrorsByModule7");
        User user = new User("Joao", "joao@gmail.com", "senha", 'j');
        user.setIdUsuario(new Long(0));
        Module m = new Module(null,   "modulo");
        try{
            impl.calculateErrorsByModule(user, m);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("Dominio não pode ser nulo"));
        }
    }
    
     /**
     * Test of calculateErrors method, of class PerformanceManagementImpl.
     */
    @Test
    public void testCalculateErrorsByModule8() throws Exception {
        System.out.println("calculateErrorsByModule8");
        User user = new User("Joao", "joao@gmail.com", "senha", 'j');
        user.setIdUsuario(new Long(0));
        Module m = new Module(new Subject("subject"),null);
        try{
            impl.calculateErrorsByModule(user, m);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("Nome do modulo não pode ser nulo"));
        }
    }
    
    
    

    /**
     * Test of calculateErrors method, of class PerformanceManagementImpl.
     */
    @Test
    public void testCalculateErrorsBySubject1() throws Exception {
        System.out.println("calculateErrorsBySubject1");
        User user = null;
        Subject s = new Subject(  "subject");
        try{
            impl.calculateErrorsBySubject(user, s);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("Usuario não pode ser nulo"));
        }
    }
    
    /**
     * Test of calculateErrors method, of class PerformanceManagementImpl.
     */
    @Test
    public void testCalculateErrorsBySubject2() throws Exception {
        System.out.println("calculateErrorsBySubject2");
        User user = new User("Joao", "joao@gmail.com", "senha", 'j');
        user.setIdUsuario(null);
        Subject s = new Subject(  "subject");
        try{
            impl.calculateErrorsBySubject(user, s);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("ID do usuario não pode ser nulo"));
        }
    }
    
    /**
     * Test of calculateErrors method, of class PerformanceManagementImpl.
     */
    @Test
    public void testCalculateErrorsBySubject3() throws Exception {
        System.out.println("calculateErrorsBySubject3");
        User user = new User("Joao", null, "senha", 'j');
        user.setIdUsuario(new Long(0));
        Subject s = new Subject(  "subject");
        try{
            impl.calculateErrorsBySubject(user, s);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("Login do usuario não pode ser nulo"));
        }
    }
    
    /**
     * Test of calculateErrors method, of class PerformanceManagementImpl.
     */
    @Test
    public void testCalculateErrorsBySubject4() throws Exception {
        System.out.println("calculateErrorsBySubject4");
        User user = new User(null, "joao@gmail.com", "senha", 'j');
        user.setIdUsuario(new Long(0));
        Subject s = new Subject(  "subject");
        try{
            impl.calculateErrorsBySubject(user, s);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("Nome do usuario não pode ser nulo"));
        }
    }
    
    /**
     * Test of calculateErrors method, of class PerformanceManagementImpl.
     */
    @Test
    public void testCalculateErrorsBySubject5() throws Exception {
        System.out.println("calculateErrorsBySubject5");
        User user = new User("Joao", "joao@gmail.com", null, 'j');
        user.setIdUsuario(new Long(0));
        Subject s = new Subject(  "subject");
        try{
            impl.calculateErrorsBySubject(user, s);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("Senha do usuario não pode ser nulo"));
        }
    }
    
    /**
     * Test of calculateErrors method, of class PerformanceManagementImpl.
     */
    @Test
    public void testCalculateErrorsBySubject6() throws Exception {
        System.out.println("calculateErrorsBySubject6");
        User user = new User("Joao", "joao@gmail.com", "senha", 'j');
        user.setIdUsuario(new Long(0));
        Subject s = null;
        try{
            impl.calculateErrorsBySubject(user, s);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("Subject não pode ser nulo"));
        }
    }
    
     /**
     * Test of calculateErrors method, of class PerformanceManagementImpl.
     */
    @Test
    public void testCalculateErrorsBySubject7() throws Exception {
        System.out.println("calculateErrorsBySubject7");
        User user = new User("Joao", "joao@gmail.com", "senha", 'j');
        user.setIdUsuario(new Long(0));
        Subject s = new Subject(null);
        try{
            impl.calculateErrorsBySubject(user, s);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("Nome do dominio não pode ser nulo"));
        }
    }        
}
