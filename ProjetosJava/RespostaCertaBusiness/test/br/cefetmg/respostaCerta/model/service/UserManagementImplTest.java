/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.model.service;

import br.cefetmg.respostaCerta.model.dao.UserDAO;
import br.cefetmg.respostaCerta.model.dao.UserDAOImpl;
import br.cefetmg.respostaCerta.model.domain.User;
import br.cefetmg.respostaCerta.model.exception.BusinessException;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author aluno
 */
public class UserManagementImplTest {
    
    private static UserDAO userDAO;
    private static UserManagementImpl impl;
    
    public UserManagementImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        userDAO = UserDAOImpl.getInstance();
        impl = new UserManagementImpl(userDAO);
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        List<User> us;
        try {
            us = userDAO.listAll();
            for(User u : us){
                userDAO.delete(u.getIdUsuario());
            }
        } catch (PersistenceException ex) {
            System.out.println("Erro!");
        }
        
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of registerUser method, of class UserManagementImpl.
     */
    @Test
    public void testRegisterUser1() throws Exception {
        System.out.println("registerUser1");
        User user = null;
        try{
            impl.registerUser(user);
        }catch(BusinessException ex){
            assertTrue("Usuario não pode ser nulo".equals(ex.getMessage()));
            return;
        }
        fail("Aceitou usuario nulo");
    }
    
    @Test
    public void testRegisterUser2() throws Exception {
        System.out.println("registerUser2");
        User user = new User();
        user.setLoginUsuario(null);
        user.setNomeUsuario("Joao");
        user.setSenhaUsuario("senha");
        user.setIdtUsuario('p');
        try{
            impl.registerUser(user);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("Login do usuario não pode ser null"));
            return;
        }
        fail("Aceitou usuario com login nulo");
    }
    
    @Test
    public void testRegisterUser3() throws Exception {
       System.out.println("registerUser3");
        User user = new User();
        user.setNomeUsuario("Joao");
        user.setLoginUsuario("joao@oi.com");
        user.setIdtUsuario('p');
        user.setSenhaUsuario(null);
        try{
            impl.registerUser(user);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("Senha não pode ser null"));
            return;
        }
        fail("Aceitou usuario com senha nula");
    }
    
    @Test
    public void testRegisterUser4() throws Exception {
        System.out.println("registerUser4");
        User user = new User();
        user.setLoginUsuario("joao@oi.com");
        user.setSenhaUsuario("senha");
        user.setIdtUsuario('p');
        user.setNomeUsuario(null);
        try{
            impl.registerUser(user);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("Nome usuario não pode ser null"));
            return;
        }
        fail("Aceitou usuario com nome nulo");
    }
    
    @Test
    public void testRegisterUser5() throws Exception {
        System.out.println("registerUser6");
        User user = new User("Joao", "joao@oi.com", "senha", 'p');
        try{
            impl.registerUser(user);
            assertEquals(user, impl.getUserById(user.getIdUsuario()));
        }catch(BusinessException|PersistenceException ex){
            fail("Erro ao inserir");
        }
        
    }

    /**
     * Test of registerUser method, of class UserManagementImpl.
     */
    @Test
    public void testUpdateUser1() throws Exception {
        System.out.println("updateUser1");
        User user = null;
        try{
            impl.updateUser(new Long(0), user);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("Usuario não pode ser nulo"));
            return;
        }
        fail("Aceitou usuario nulo");
    }
    
    @Test
    public void testUpdateUser2() throws Exception {
        System.out.println("updateUser2");
        User user = new User();
        user.setLoginUsuario(null);
        user.setNomeUsuario("Joao");
        user.setSenhaUsuario("senha");
        user.setIdtUsuario('p');
        try{
            impl.updateUser(new Long(0), user);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("Login do usuario não pode ser null"));
            return;
        }
        fail("Aceitou usuario com login nulo");
    }
    
    @Test
    public void testUpdateUser3() throws Exception {
       System.out.println("updateUser3");
        User user = new User();
        user.setNomeUsuario("Joao");
        user.setLoginUsuario("joao@oi.com");
        user.setIdtUsuario('p');
        user.setSenhaUsuario(null);
        try{
            impl.updateUser(new Long(0), user);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("Senha não pode ser null"));
            return;
        }
        fail("Aceitou usuario com senha nula");
    }
    
    @Test
    public void testUpdateUser4() throws Exception {
        System.out.println("updateUser4");
        User user = new User();
        user.setLoginUsuario("joao@oi.com");
        user.setSenhaUsuario("senha");
        user.setIdtUsuario('p');
        user.setNomeUsuario(null);
        try{
            impl.updateUser(new Long(0), user);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("Nome usuario não pode ser null"));
            return;
        }
        fail("Aceitou usuario com nome nulo");
    }
    
    /**
     * Test of updateUser method, of class UserManagementImpl.
     */
    @Test
    public void testUpdateUser5() throws Exception {
        System.out.println("updateUser5");
        User user = new User();
        user.setLoginUsuario("joao@oi.com");
        user.setSenhaUsuario("senha");
        user.setIdtUsuario('p');
        user.setNomeUsuario("Joao");
        try{
            impl.updateUser(null, user);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("ID não pode ser nulo"));
            return;
        }
        fail("Aceitou ID nulo");
    }
    
    /**
     * Test of updateUser method, of class UserManagementImpl.
     */
    @Test
    public void testUpdateUser6() throws Exception {
        System.out.println("updateUser6");
        User user = new User();
        user.setLoginUsuario("joao@oi.com");
        user.setSenhaUsuario("senha");
        user.setIdtUsuario('p');
        user.setNomeUsuario("Joao");
        impl.registerUser(user);
        User user2=new User("Maria", "maria@gmail.com", "senha", 'p');
        impl.updateUser(user.getIdUsuario(), user2);
        assertTrue(impl.getUserById(user.getIdUsuario())==user2);
    }

    /**
     * Test of removeUser method, of class UserManagementImpl.
     */
    @Test
    public void testRemoveUser() throws Exception {
        System.out.println("removeUser");
        Long id = null;
        UserManagementImpl instance = null;
        instance.removeUser(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUserById method, of class UserManagementImpl.
     */
    @Test
    public void testGetUserById() throws Exception {
        System.out.println("getUserById");
        Long id = null;
        UserManagementImpl instance = null;
        User expResult = null;
        User result = instance.getUserById(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
