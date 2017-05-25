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
            assertTrue(ex.getMessage().equals("usuarionão pode ser nulo"));
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
            assertTrue(ex.getMessage().equals("ID dousuario não pode ser nulo"));
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
        System.out.println("calculateErrors6");
        User user = new User("Joao", "joao@gmail.com", "senha", 'j');
        user.setIdUsuario(new Long(0));
        ClosedAnswer resp1;
        resp1 = new ClosedAnswer(0, user, new Question(new Module(new Subject(new Long(0),"subject","desc"),new Long(0),"modulo","desc"), user, new Long(0), "enunciado", true, LocalDate.now(), "titulo", null), LocalDate.now(), 'f', true);
        ClosedAnswer resp2;
        resp2 = new ClosedAnswer(0, user, new Question(new Module(new Subject(new Long(1),"subject2","desc2"),new Long(1),"modulo2","desc2"), user, new Long(1), "enunciado2", true, LocalDate.now(), "titulo2", null), LocalDate.now(), 'f', false);
        answer.insert(resp1);
        answer.insert(resp2);
        try{
            if(impl.calculateErrors(user)!=50){
                fail("Valor errado");
            }
            
        }catch(BusinessException | PersistenceException ex){
            fail("Erro ao calcular");
        }
    }
    
    /**
     * Test of calculateErrors method, of class PerformanceManagementImpl.
     */
    @Test
    public void testCalculateErrors7() throws Exception {
        System.out.println("calculateErrors7");
        User user = new User("Joao", "joao@gmail.com", "senha", 'j');
        user.setIdUsuario(new Long(0));
        ClosedAnswer resp1;
        resp1 = new ClosedAnswer(0, user, new Question(new Module(new Subject(new Long(0),"subject","desc"),new Long(0),"modulo","desc"), user, new Long(0), "enunciado", true, LocalDate.now(), "titulo", null), LocalDate.now(), 'f', true);
        ClosedAnswer resp2;
        resp2 = new ClosedAnswer(0, user, new Question(new Module(new Subject(new Long(1),"subject2","desc2"),new Long(1),"modulo2","desc2"), user, new Long(1), "enunciado2", true, LocalDate.now(), "titulo2", null), LocalDate.now(), 'f', true);
        answer.insert(resp1);
        answer.insert(resp2);
        try{
            if(impl.calculateErrors(user)!=0){
                fail("Valor errado");
            }
            
        }catch(BusinessException | PersistenceException ex){
            fail("Erro ao calcular");
        }
    }
    
    /**
     * Test of calculateErrors method, of class PerformanceManagementImpl.
     */
    @Test
    public void testCalculateErrors8() throws Exception {
        System.out.println("calculateErrors8");
        User user = new User("Joao", "joao@gmail.com", "senha", 'j');
        user.setIdUsuario(new Long(0));
        ClosedAnswer resp1;
        resp1 = new ClosedAnswer(0, user, new Question(new Module(new Subject(new Long(0),"subject","desc"),new Long(0),"modulo","desc"), user, new Long(0), "enunciado", true, LocalDate.now(), "titulo", null), LocalDate.now(), 'f', true);
        ClosedAnswer resp2;
        resp2 = new ClosedAnswer(0, user, new Question(new Module(new Subject(new Long(1),"subject2","desc2"),new Long(1),"modulo2","desc2"), user, new Long(1), "enunciado2", true, LocalDate.now(), "titulo2", null), LocalDate.now(), 'f', false);
        ClosedAnswer resp3;
        resp3 = new ClosedAnswer(0, user, new Question(new Module(new Subject(new Long(2),"subject3","desc3"),new Long(2),"modulo3","desc3"), user, new Long(2), "enunciado3", true, LocalDate.now(), "titulo3", null), LocalDate.now(), 'f', false);
        answer.insert(resp1);
        answer.insert(resp2);
        answer.insert(resp3);
        try{
            if(impl.calculateErrors(user)!=((2.0*100)/3.0)){
                fail("Valor errado");
            }
            
        }catch(BusinessException | PersistenceException ex){
            fail("Erro ao calcular");
        }
    }
    
    /**
     * Test of calculateErrors method, of class PerformanceManagementImpl.
     */
    @Test
    public void testCalculateErrors9() throws Exception {
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
        Module m = new Module(new Subject(new Long(0), "subject", "desc"), new Long(0), "modulo", "desc");
        try{
            impl.calculateErrorsByModule(user, m);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("usuarionão pode ser nulo"));
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
        Module m = new Module(new Subject(new Long(0), "subject", "desc"), new Long(0), "modulo", "desc");
        try{
            impl.calculateErrorsByModule(user, m);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("ID dousuario não pode ser nulo"));
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
        Module m = new Module(new Subject(new Long(0), "subject", "desc"), new Long(0), "modulo", "desc");
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
        Module m = new Module(new Subject(new Long(0), "subject", "desc"), new Long(0), "modulo", "desc");
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
        Module m = new Module(new Subject(new Long(0), "subject", "desc"), new Long(0), "modulo", "desc");
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
        Module m = new Module(null, new Long(0), "modulo", "desc");
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
        Module m = new Module(new Subject(new Long(0), "subject", "desc"), new Long(0), null, "desc");
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
    public void testCalculateErrorsByModule9() throws Exception {
        System.out.println("calculateErrorsByModule9");
        User user = new User("Joao", "joao@gmail.com", "senha", 'j');
        user.setIdUsuario(new Long(0));
        Module m = new Module(new Subject(new Long(0), "subject", "desc"), new Long(0), "modulo", null);
        try{
            impl.calculateErrorsByModule(user, m);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("descrição do modulo não pode ser nulo"));
        }
    }
    
    /**
     * Test of calculateErrors method, of class PerformanceManagementImpl.
     */
    @Test
    public void testCalculateErrorsByModule10() throws Exception {
        System.out.println("calculateErrorsByModule10");
        User user = new User("Joao", "joao@gmail.com", "senha", 'j');
        user.setIdUsuario(new Long(0));
        Module m = new Module(new Subject(new Long(0),"subject","desc"),new Long(0),"modulo","desc");
        ClosedAnswer resp1;
        resp1 = new ClosedAnswer(0, user, new Question(m, user, new Long(0), "enunciado", true, LocalDate.now(), "titulo", null), LocalDate.now(), 'f', true);
        ClosedAnswer resp2;
        resp2 = new ClosedAnswer(0, user, new Question(new Module(new Subject(new Long(1),"subject2","desc2"),new Long(1),"modulo2","desc2"), user, new Long(1), "enunciado2", true, LocalDate.now(), "titulo2", null), LocalDate.now(), 'f', false);
        ClosedAnswer resp3;
        resp3 = new ClosedAnswer(0, user, new Question(new Module(new Subject(new Long(2),"subject3","desc3"),new Long(2),"modulo3","desc3"), user, new Long(2), "enunciado3", true, LocalDate.now(), "titulo3", null), LocalDate.now(), 'f', false);
        answer.insert(resp1);
        answer.insert(resp2);
        answer.insert(resp3);
        try{
            if(impl.calculateErrorsByModule(user, m)!=0){
                fail("Valor errado");
            }
            
        }catch(BusinessException | PersistenceException ex){
            fail("Erro ao calcular");
        }
    }
    
    /**
     * Test of calculateErrors method, of class PerformanceManagementImpl.
     */
    @Test
    public void testCalculateErrorsByModule11() throws Exception {
        System.out.println("calculateErrorsByModule11");
        User user = new User("Joao", "joao@gmail.com", "senha", 'j');
        user.setIdUsuario(new Long(0));
        Module m = new Module(new Subject(new Long(0),"subject","desc"),new Long(0),"modulo","desc");
        ClosedAnswer resp1;
        resp1 = new ClosedAnswer(0, user, new Question(m , user, new Long(0), "enunciado", true, LocalDate.now(), "titulo", null), LocalDate.now(), 'f', true);
        ClosedAnswer resp2;
        resp2 = new ClosedAnswer(0, user, new Question(m , user, new Long(1), "enunciado2", true, LocalDate.now(), "titulo2", null), LocalDate.now(), 'f', false);
        ClosedAnswer resp3;
        resp3 = new ClosedAnswer(0, user, new Question(new Module(new Subject(new Long(2),"subject3","desc3"),new Long(2),"modulo3","desc3"), user, new Long(2), "enunciado3", true, LocalDate.now(), "titulo3", null), LocalDate.now(), 'f', false);
        answer.insert(resp1);
        answer.insert(resp2);
        answer.insert(resp3);
        try{
            if(impl.calculateErrorsByModule(user, m)!=50){
                fail("Valor errado");
            }
            
        }catch(BusinessException | PersistenceException ex){
            fail("Erro ao calcular");
        }
    }
    
    /**
     * Test of calculateErrors method, of class PerformanceManagementImpl.
     */
    @Test
    public void testCalculateErrorsByModule12() throws Exception {
        System.out.println("calculateErrorsByModule12");
        User user = new User("Joao", "joao@gmail.com", "senha", 'j');
        user.setIdUsuario(new Long(0));
        Module m = new Module(new Subject(new Long(0),"subject","desc"),new Long(0),"modulo","desc");
        ClosedAnswer resp1;
        resp1 = new ClosedAnswer(0, user, new Question(new Module(new Subject(new Long(2),"subject3","desc3"), new Long(2),"modulo3","desc3") , user, new Long(0), "enunciado", true, LocalDate.now(), "titulo", null), LocalDate.now(), 'f', true);
        ClosedAnswer resp2;
        resp2 = new ClosedAnswer(0, user, new Question(new Module(new Subject(new Long(2),"subject3","desc3"), new Long(2),"modulo3","desc3") , user, new Long(1), "enunciado2", true, LocalDate.now(), "titulo2", null), LocalDate.now(), 'f', false);
        ClosedAnswer resp3;
        resp3 = new ClosedAnswer(0, user, new Question(new Module(new Subject(new Long(2),"subject3","desc3"),new Long(2),"modulo3","desc3"), user, new Long(2), "enunciado3", true, LocalDate.now(), "titulo3", null), LocalDate.now(), 'f', false);
        answer.insert(resp1);
        answer.insert(resp2);
        answer.insert(resp3);
        try{
            impl.calculateErrorsByModule(user, m);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("O usuario não respondeu nenhuma questão desse módulo"));
        }
    }

    /**
     * Test of calculateErrors method, of class PerformanceManagementImpl.
     */
    @Test
    public void testCalculateErrorsBySubject1() throws Exception {
        System.out.println("calculateErrorsBySubject1");
        User user = null;
        Subject s = new Subject(new Long(0), "subject", "desc");
        try{
            impl.calculateErrorsBySubject(user, s);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("usuarionão pode ser nulo"));
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
        Subject s = new Subject(new Long(0), "subject", "desc");
        try{
            impl.calculateErrorsBySubject(user, s);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("ID dousuario não pode ser nulo"));
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
        Subject s = new Subject(new Long(0), "subject", "desc");
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
        Subject s = new Subject(new Long(0), "subject", "desc");
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
        Subject s = new Subject(new Long(0), "subject", "desc");
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
            assertTrue(ex.getMessage().equals("subject não pode ser nulo"));
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
        Subject s = new Subject(new Long(0), null, "desc");
        try{
            impl.calculateErrorsBySubject(user, s);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("Nome do dominio não pode ser nulo"));
        }
    }
    
     /**
     * Test of calculateErrors method, of class PerformanceManagementImpl.
     */
    @Test
    public void testCalculateErrorsBySubject8() throws Exception {
        System.out.println("calculateErrorsBySubject8");
        User user = new User("Joao", "joao@gmail.com", "senha", 'j');
        user.setIdUsuario(new Long(0));
        Subject s = new Subject(new Long(0), "subject", null);
        try{
            impl.calculateErrorsBySubject(user, s);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("desc do dominio não pode ser nula"));
        }
    }
    
    /**
     * Test of calculateErrors method, of class PerformanceManagementImpl.
     */
    @Test
    public void testCalculateErrorsBySubject9() throws Exception {
        System.out.println("calculateErrorsBySubject9");
        User user = new User("Joao", "joao@gmail.com", "senha", 'j');
        user.setIdUsuario(new Long(0));
        Subject s = new Subject(new Long(0), "subject", "desc");
        ClosedAnswer resp1;
        resp1 = new ClosedAnswer(0, user, new Question(new Module(s, new Long(0), "nome", "desc"), user, new Long(0), "enunciado", true, LocalDate.now(), "titulo", null), LocalDate.now(), 'f', true);
        ClosedAnswer resp2;
        resp2 = new ClosedAnswer(0, user, new Question(new Module(new Subject(new Long(1),"subject2","desc2"),new Long(1),"modulo2","desc2"), user, new Long(1), "enunciado2", true, LocalDate.now(), "titulo2", null), LocalDate.now(), 'f', false);
        ClosedAnswer resp3;
        resp3 = new ClosedAnswer(0, user, new Question(new Module(new Subject(new Long(2),"subject3","desc3"),new Long(2),"modulo3","desc3"), user, new Long(2), "enunciado3", true, LocalDate.now(), "titulo3", null), LocalDate.now(), 'f', false);
        answer.insert(resp1);
        answer.insert(resp2);
        answer.insert(resp3);
        try{
            if(impl.calculateErrorsBySubject(user, s)!=0){
                fail("Valor errado");
            }
            
        }catch(BusinessException | PersistenceException ex){
            fail("Erro ao calcular");
        }
    }
    
    /**
     * Test of calculateErrors method, of class PerformanceManagementImpl.
     */
    @Test
    public void testCalculateErrorsBySubject10() throws Exception {
        System.out.println("calculateErrorsBySubject10");
        User user = new User("Joao", "joao@gmail.com", "senha", 'j');
        user.setIdUsuario(new Long(0));
        Subject s = new Subject(new Long(0), "subject", "desc");
        ClosedAnswer resp1;
        resp1 = new ClosedAnswer(0, user, new Question(new Module(s, new Long(0), "nome", "desc") , user, new Long(0), "enunciado", true, LocalDate.now(), "titulo", null), LocalDate.now(), 'f', true);
        ClosedAnswer resp2;
        resp2 = new ClosedAnswer(0, user, new Question(new Module(s, new Long(1), "nome2", "desc2") , user, new Long(1), "enunciado2", true, LocalDate.now(), "titulo2", null), LocalDate.now(), 'f', false);
        ClosedAnswer resp3;
        resp3 = new ClosedAnswer(0, user, new Question(new Module(new Subject(new Long(2),"subject3","desc3"),new Long(2),"modulo3","desc3"), user, new Long(2), "enunciado3", true, LocalDate.now(), "titulo3", null), LocalDate.now(), 'f', false);
        answer.insert(resp1);
        answer.insert(resp2);
        answer.insert(resp3);
        try{
            if(impl.calculateErrorsBySubject(user, s)!=50){
                fail("Valor errado");
            }
            
        }catch(BusinessException | PersistenceException ex){
            fail("Erro ao calcular");
        }
    }
    
    /**
     * Test of calculateErrors method, of class PerformanceManagementImpl.
     */
    @Test
    public void testCalculateErrorsBySubject11() throws Exception {
        System.out.println("calculateErrorsBySubject11");
        User user = new User("Joao", "joao@gmail.com", "senha", 'j');
        user.setIdUsuario(new Long(0));
        Subject s = new Subject(new Long(0), "subject", "desc");
        ClosedAnswer resp1;
        resp1 = new ClosedAnswer(0, user, new Question(new Module(new Subject(new Long(2),"subject3","desc3"), new Long(2),"modulo3","desc3") , user, new Long(0), "enunciado", true, LocalDate.now(), "titulo", null), LocalDate.now(), 'f', true);
        ClosedAnswer resp2;
        resp2 = new ClosedAnswer(0, user, new Question(new Module(new Subject(new Long(2),"subject3","desc3"), new Long(2),"modulo3","desc3") , user, new Long(1), "enunciado2", true, LocalDate.now(), "titulo2", null), LocalDate.now(), 'f', false);
        ClosedAnswer resp3;
        resp3 = new ClosedAnswer(0, user, new Question(new Module(new Subject(new Long(2),"subject3","desc3"),new Long(2),"modulo3","desc3"), user, new Long(2), "enunciado3", true, LocalDate.now(), "titulo3", null), LocalDate.now(), 'f', false);
        answer.insert(resp1);
        answer.insert(resp2);
        answer.insert(resp3);
        try{
            impl.calculateErrorsBySubject(user, s);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("O usuario não respondeu nenhuma questão desse dominio"));
        }
    }
    
}
