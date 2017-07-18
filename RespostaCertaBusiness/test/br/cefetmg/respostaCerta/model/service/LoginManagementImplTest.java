/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.model.service;

import br.cefetmg.respostaCerta.model.dao.UserDAO;
import br.cefetmg.respostaCerta.model.dao.UserDAOImpl;
import br.cefetmg.respostaCerta.model.domain.Subject;
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
 * @author umcan
 */
public class LoginManagementImplTest {
    private static UserDAO userDAO;
    private static LoginManagementImpl impl;
    
    public LoginManagementImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        userDAO = UserDAOImpl.getInstance();
        impl = new LoginManagementImpl(userDAO);
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
     * Test of loginUser method, of class LoginManagementImpl.
     */
    @Test
    public void testLoginUser1() throws Exception {
        System.out.println("loginUser1");
        String username = "";
        String password = "senha";
        try{
            impl.loginUser(username, password);
        }catch(BusinessException | PersistenceException ex){
            return;
        }
        fail("Aceitou username vazio");
    }
    
    /**
     * Test of loginUser method, of class LoginManagementImpl.
     */
    @Test
    public void testLoginUser2() throws Exception {
        System.out.println("loginUser2");
        String username = "username";
        String password = "";
        try{
            impl.loginUser(username, password);
        }catch(BusinessException | PersistenceException ex){
            return;
        }
        fail("Aceitou senha vazia");
    }
    
    /**
     * Test of loginUser method, of class LoginManagementImpl.
     */
    @Test
    public void testLoginUser3() throws Exception {
        System.out.println("loginUser3");
        String username = null;
        String password = "senha";
        try{
            impl.loginUser(username, password);
        }catch(BusinessException | PersistenceException ex){
            return;
        }
        fail("Aceitou username nulo");
    }
    
    /**
     * Test of loginUser method, of class LoginManagementImpl.
     */
    @Test
    public void testLoginUser4() throws Exception {
        System.out.println("loginUser4");
        String username = "username";
        String password = null;
        try{
            impl.loginUser(username, password);
        }catch(BusinessException | PersistenceException ex){
            return;
        }
        fail("Aceitou senha nula");
    }
    /**
     * Test of loginUser method, of class LoginManagementImpl.
     */
    @Test
    public void testLoginUser5() throws Exception {
        System.out.println("loginUser6");
        User user = new User("Joao", "joao@gmail.com", "senha", 'p');
        userDAO.insert(user);
        String username = "joao@gmail.com";
        String password = "senha";
        try{
            impl.loginUser(username, password);
        }catch(BusinessException | PersistenceException ex){
            fail("Erro ao logar");
        }
    }
    
}
