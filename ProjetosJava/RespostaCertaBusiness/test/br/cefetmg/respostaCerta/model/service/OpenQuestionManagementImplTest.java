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
            assertTrue("question não pode ser nulo".equals(ex.getMessage()));
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
        question.setModulo(new Module(new Subject(null, "dominio", "desc"), null, "modulo", "desc"));
        question.setEnunciadoQuestao("Enunciado");
        question.setTituloQuestao("Titulo");
        try{
            impl.registerQuestion(question);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("criador não pode ser nulo"));
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
        question.setModulo(new Module(new Subject(null, "dominio", "desc"), null, "modulo", "desc"));
        question.setEnunciadoQuestao("Enunciado");
        question.setTituloQuestao("Titulo");
        try{
            impl.registerQuestion(question);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("data de criação não pode ser nulo"));
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
            assertTrue(ex.getMessage().equals("modulo não pode ser nulo"));
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
        question.setModulo(new Module(new Subject(null, "dominio", "desc"), null, "modulo", "desc"));
        question.setEnunciadoQuestao(null);
        question.setTituloQuestao("Titulo");
        try{
            impl.registerQuestion(question);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("enunciado não pode ser nulo"));
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
        question.setModulo(new Module(new Subject(null, "dominio", "desc"), null, "modulo", "desc"));
        question.setEnunciadoQuestao("Enunciado");
        question.setTituloQuestao(null);
        try{
            impl.registerQuestion(question);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("titulo não pode ser nulo"));
            return;
        }
        fail("Aceitou questao com titulo nulo");
    }
    
    @Test
    public void testRegisterOpenQuestion7() throws Exception {
        System.out.println("registerQuestion7");
        Question question = new Question();
        question.setCriador(new User("Joao", "joao@gmail.com", "senha", 'j'));
        question.setDataCriacao(LocalDate.now());
        question.setModulo(new Module(new Subject(null, "dominio", "desc"), null, "modulo", "desc"));
        question.setEnunciadoQuestao("Enunciado");
        question.setTituloQuestao("Titulo");
        try{
            impl.registerQuestion(question);
            assertEquals(question, impl.getQuestionById(question.getIdQuestao()));
        }catch(BusinessException|PersistenceException ex){
            fail("Erro ao inserir");
        }
        
    }
    /**
     * Test of registerUser method, of class UserManagementImpl.
     */
    @Test
    public void testUpdateOpenQuestion1() throws Exception {
        System.out.println("updateOpenQuestion1");
        Question open = null;
        try{
            impl.updateQuestion(new Long(0), open);
        }catch(BusinessException ex){
            System.out.println(ex);
            assertTrue(ex.getMessage().equals("question não pode ser nulo"));
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
        question.setModulo(new Module(new Subject(null, "dominio", "desc"), null, "modulo", "desc"));
        question.setEnunciadoQuestao("Enunciado");
        question.setTituloQuestao("Titulo");
        try{
            impl.updateQuestion(new Long(0), question);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("criador não pode ser nulo"));
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
        question.setModulo(new Module(new Subject(null, "dominio", "desc"), null, "modulo", "desc"));
        question.setEnunciadoQuestao("Enunciado");
        question.setTituloQuestao("Titulo");
        try{
            impl.updateQuestion(new Long(0), question);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("data de criação não pode ser nulo"));
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
            assertTrue(ex.getMessage().equals("modulo não pode ser nulo"));
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
        question.setModulo(new Module(new Subject(null, "dominio", "desc"), null, "modulo", "desc"));
        question.setEnunciadoQuestao(null);
        question.setTituloQuestao("Titulo");
        try{
            impl.updateQuestion(new Long(0), question);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("enunciado não pode ser nulo"));
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
        question.setModulo(new Module(new Subject(null, "dominio", "desc"), null, "modulo", "desc"));
        question.setEnunciadoQuestao("Enunciado");
        question.setTituloQuestao(null);
        try{
            impl.updateQuestion(new Long(0), question);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("titulo não pode ser nulo"));
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
        question.setModulo(new Module(new Subject(null, "dominio", "desc"), null, "modulo", "desc"));
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
     * Test of updateUser method, of class UserManagementImpl.
     */
    @Test
    public void testUpdateUser8() throws Exception {
        System.out.println("updateOpenQuestion8");
        Question question = new Question();
        question.setCriador(new User("Joao", "joao@gmail.com", "senha", 'j'));
        question.setDataCriacao(LocalDate.now());
        question.setModulo(new Module(new Subject(null, "dominio", "desc"), null, "modulo", "desc"));
        question.setEnunciadoQuestao("Enunciado");
        question.setTituloQuestao("Titulo");
        impl.registerQuestion(question);
        Question question2 = new Question();
        question2.setCriador(new User("Maria", "maria@gmail.com", "senha", 'j'));
        question2.setDataCriacao(LocalDate.now());
        question2.setModulo(new Module(new Subject(null, "dominio2", "desc2"), null, "modulo2", "desc2"));
        question2.setEnunciadoQuestao("Enunciado2");
        question2.setTituloQuestao("Titulo2");
        impl.updateQuestion(question.getIdQuestao(), question2);
        assertTrue(impl.getQuestionById(question.getIdQuestao()).equals(question2));
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
    public void testRemoveOpenQuestion2() throws Exception {
        System.out.println("removeOpenQuestion2");
        try{
           impl.removeQuestion(new Long(0)); 
        }catch(PersistenceException ex){
           return;
        }
        fail("Removeu question inexistente");
    }
    
    /**
     * Test of removeUser method, of class UserManagementImpl.
     */
    @Test
    public void testRemoveOpenQuestion3() throws Exception {
        System.out.println("removeOpenQuestion3");
        Question question = new Question();
        question.setCriador(new User("Joao", "joao@gmail.com", "senha", 'j'));
        question.setDataCriacao(LocalDate.now());
        question.setModulo(new Module(new Subject(null, "dominio", "desc"), null, "modulo", "desc"));
        question.setEnunciadoQuestao("Enunciado");
        question.setTituloQuestao("Titulo");
        try{
           impl.registerQuestion(question);
           impl.removeQuestion(new Long(3)); 
           fail("Buscou question inexistente");
        }catch(PersistenceException ex){
           return;
        }
        fail("Removeu question inexistente");
    }
    
    /**
     * Test of removeUser method, of class UserManagementImpl.
     */
    @Test
    public void testRemoveOpenQuestion4() throws Exception {
        System.out.println("removeOpenQuestion4");
        Question question = new Question();
        question.setCriador(new User("Joao", "joao@gmail.com", "senha", 'j'));
        question.setDataCriacao(LocalDate.now());
        question.setModulo(new Module(new Subject(null, "dominio", "desc"), null, "modulo", "desc"));
        question.setEnunciadoQuestao("Enunciado");
        question.setTituloQuestao("Titulo");
        try{
           impl.registerQuestion(question);
           impl.removeQuestion(question.getIdQuestao());
        }catch(PersistenceException|BusinessException ex){
           fail("Erro ao remover");
        }
        try{
            impl.getQuestionById(question.getIdQuestao());
        }catch(Exception ex){
            return;
        }
        fail("Não removeu a question");
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
    
    /**
     * Test of removeUser method, of class UserManagementImpl.
     */
    @Test
    public void testGetOpenQuestionById2() throws Exception {
        System.out.println("getOpenQuestionById2");
        try{
           impl.getQuestionById(new Long(0)); 
        }catch(PersistenceException ex){
           return;
        }
        fail("Buscou resposta inexistente");
    }
    
    /**
     * Test of removeUser method, of class UserManagementImpl.
     */
    @Test
    public void testGetOpenQuestionById3() throws Exception {
        System.out.println("getOpenQuestionById3");
        Question question = new Question();
        question.setCriador(new User("Joao", "joao@gmail.com", "senha", 'j'));
        question.setDataCriacao(LocalDate.now());
        question.setModulo(new Module(new Subject(null, "dominio", "desc"), null, "modulo", "desc"));
        question.setEnunciadoQuestao("Enunciado");
        question.setTituloQuestao("Titulo");
        try{
           impl.registerQuestion(question);
           impl.getQuestionById(new Long(4)); 
           fail("Buscou question inexistente");
        }catch(PersistenceException ex){
           return;
        }
    }
    
    /**
     * Test of removeUser method, of class UserManagementImpl.
     */
    @Test
    public void testGetOpenQuestionById4() throws Exception {
        System.out.println("getOpenQuestionById4");
        Question question = new Question();
        question.setCriador(new User("Joao", "joao@gmail.com", "senha", 'j'));
        question.setDataCriacao(LocalDate.now());
        question.setModulo(new Module(new Subject(null, "dominio", "desc"), null, "modulo", "desc"));
        question.setEnunciadoQuestao("Enunciado");
        question.setTituloQuestao("Titulo");
        try{
           impl.registerQuestion(question);
           assertTrue(impl.getQuestionById(question.getIdQuestao())==question);
        }catch(PersistenceException|BusinessException ex){
           fail("Erro ao obter a question");
        }
    }

    /**
     * Test of getQuestionsByUser method, of class OpenQuestionManagementImpl.
     */
    @Test
    public void testGetQuestionsByUser1() throws Exception {
        System.out.println("getQuestionsByUser1");
        Long id = null;
        try{
            impl.getQuestionById(id);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("Id não pode ser nulo"));
        }
    }
    
    /**
     * Test of getQuestionsByUser method, of class OpenQuestionManagementImpl.
     */
    @Test
    public void testGetQuestionsByUser2() throws Exception {
        System.out.println("getQuestionsByUser2");
        Question question = new Question();
        question.setCriador(new User(new Long(0), "Joao", "joao@gmail.com", "senha", 'j'));
        question.setDataCriacao(LocalDate.now());
        question.setModulo(new Module(new Subject(null, "dominio", "desc"), null, "modulo", "desc"));
        question.setEnunciadoQuestao("Enunciado");
        question.setTituloQuestao("Titulo");
        impl.registerQuestion(question);
        try{
           List<Question> list = impl.getQuestionsByUser(new Long(0));
           if(list.size()!=1){
               fail("Lista errada ao retornar");
           }
           if(list.get(0)!=question){
               fail("Objetos errados na lista");
           }
        }catch(BusinessException | PersistenceException ex){
            fail("Erro :" + ex);
        }
        
    }
    
     
    /**
     * Test of getQuestionsByUser method, of class OpenQuestionManagementImpl.
     */
    @Test
    public void testGetQuestionsByUser3() throws Exception {
        System.out.println("getQuestionsByUser3");
        User user = new User(new Long(0), "Joao", "joao@gmail.com", "senha", 'j');
        Question question = new Question();
        question.setCriador(user);
        question.setDataCriacao(LocalDate.now());
        question.setModulo(new Module(new Subject(null, "dominio", "desc"), null, "modulo", "desc"));
        question.setEnunciadoQuestao("Enunciado");
        question.setTituloQuestao("Titulo");
        impl.registerQuestion(question);
        Question question2 = new Question();
        question2.setCriador(user);
        question2.setDataCriacao(LocalDate.now());
        question2.setModulo(new Module(new Subject(null, "dominio2", "desc2"), null, "modulo2", "desc2"));
        question2.setEnunciadoQuestao("Enunciado2");
        question2.setTituloQuestao("Titulo2");
        impl.registerQuestion(question2);
        try{
           List<Question> list = impl.getQuestionsByUser(new Long(0));
           if(list.size()!=2){
               fail("Lista errada ao retornar");
           }
           if(list.get(0)!=question && list.get(1)!=question2){
               fail("Objetos errados na lista");
           }
        }catch(BusinessException | PersistenceException ex){
            fail("Erro :" + ex);
        }
    }
    
     
    /**
     * Test of getQuestionsByUser method, of class OpenQuestionManagementImpl.
     */
    @Test
    public void testGetQuestionsByUser4() throws Exception {
        System.out.println("getQuestionsByUser4");
        User user = new User(new Long(1), "Joao", "joao@gmail.com", "senha", 'j');
        Question question = new Question();
        question.setCriador(user);
        question.setDataCriacao(LocalDate.now());
        question.setModulo(new Module(new Subject(null, "dominio", "desc"), null, "modulo", "desc"));
        question.setEnunciadoQuestao("Enunciado");
        question.setTituloQuestao("Titulo");
        impl.registerQuestion(question);
        Question question2 = new Question();
        question2.setCriador(user);
        question2.setDataCriacao(LocalDate.now());
        question2.setModulo(new Module(new Subject(null, "dominio2", "desc2"), null, "modulo2", "desc2"));
        question2.setEnunciadoQuestao("Enunciado2");
        question2.setTituloQuestao("Titulo2");
        impl.registerQuestion(question2);
        try{
           List<Question> list = impl.getQuestionsByUser(new Long(0));
           if(!list.isEmpty()){
               fail("Lista errada ao retornar");
           }
           
        }catch(BusinessException | PersistenceException ex){
            fail("Erro :" + ex);
        }
    }
    
     
    /**
     * Test of getQuestionsByUser method, of class OpenQuestionManagementImpl.
     */
    @Test
    public void testGetQuestionsByUser5() throws Exception {
        System.out.println("getQuestionsByUser5");
        Question question = new Question();
        question.setCriador(new User(new Long(0), "Joao", "joao@gmail.com", "senha", 'j'));
        question.setDataCriacao(LocalDate.now());
        question.setModulo(new Module(new Subject(null, "dominio", "desc"), null, "modulo", "desc"));
        question.setEnunciadoQuestao("Enunciado");
        question.setTituloQuestao("Titulo");
        impl.registerQuestion(question);
        Question question2 = new Question();
        question2.setCriador(new User(new Long(1), "Maria", "maria@gmail.com", "senha", 'j'));
        question2.setDataCriacao(LocalDate.now());
        question2.setModulo(new Module(new Subject(null, "dominio2", "desc2"), null, "modulo2", "desc2"));
        question2.setEnunciadoQuestao("Enunciado2");
        question2.setTituloQuestao("Titulo2");
        impl.registerQuestion(question2);
        try{
           List<Question> list = impl.getQuestionsByUser(new Long(0));
           if(list.size()!=1){
               fail("Lista errada ao retornar");
           }
           if(list.get(0)!=question){
               fail("Objeto errado na lista");
           }
           
           list = impl.getQuestionsByUser(new Long(1));
           if(list.size()!=1){
               fail("Lista errada ao retornar");
           }
           if(list.get(0)!=question2){
               fail("Objeto errado na lista");
           }
        }catch(BusinessException | PersistenceException ex){
            fail("Erro :" + ex);
        }
    }
    
}
